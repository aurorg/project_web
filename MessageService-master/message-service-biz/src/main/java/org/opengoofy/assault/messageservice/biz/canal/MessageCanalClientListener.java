package org.opengoofy.assault.messageservice.biz.canal;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import jodd.util.ThreadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.assault.framework.starter.cache.DistributedCache;
import org.opengoofy.assault.messageservice.biz.common.EnableStatusEnum;
import org.opengoofy.assault.messageservice.biz.dao.entity.TemplateConfigDO;
import org.opengoofy.assault.messageservice.biz.dao.mapper.TemplateConfigMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.opengoofy.assault.messageservice.biz.common.MessageCacheConstants.MESSAGE_TEMPLATE_CACHE_PREFIX_KEY;

/**
 * 消息监听
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageCanalClientListener implements CommandLineRunner {
    
    private final DistributedCache distributedCache;
    private final TemplateConfigMapper templateConfigMapper;
    
    private static final int BATCH_SIZE = 1000;
    private static final String SUBSCRIBE = "message_manager\\.template_config";
    private static final String LUA_CANAL_SCRIPT_SOURCE_PATH = "lua/cacheUpdateByCanal.lua";
    
    @Override
    public void run(String... args) throws Exception {
        Thread linkCanalClientListenerThread = new Thread(() -> {
            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(), 11111), "example", "", "");
            try {
                connector.connect();
                connector.subscribe(SUBSCRIBE);
                while (true) {
                    Message message = connector.getWithoutAck(BATCH_SIZE);
                    long batchId = message.getId();
                    if (batchId == -1 || message.getEntries().size() == 0) {
                        ThreadUtil.sleep(1000);
                        continue;
                    }
                    printEntry(message.getEntries());
                    connector.ack(batchId);
                }
            } finally {
                connector.disconnect();
            }
        });
        linkCanalClientListenerThread.setName("LinkCanalClientListener");
        linkCanalClientListenerThread.start();
    }
    
    private void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }
            CanalEntry.RowChange rowChange;
            try {
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of error change-event has an error, data: " + entry, e);
            }
            CanalEntry.EventType eventType = rowChange.getEventType();
            rowChange.getRowDatasList().forEach(each -> cacheDelAndUpdate(eventType, each));
        }
    }
    
    /**
     * 删除缓存并更新缓存
     */
    private void cacheDelAndUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        if (eventType != CanalEntry.EventType.UPDATE) {
            return;
        }
        Optional<CanalEntry.Column> templateIdOptional = rowData.getBeforeColumnsList().stream().filter(each -> Objects.equals("template_id", each.getName())).findFirst();
        if (!templateIdOptional.isPresent()) {
            return;
        }
        String templateId = templateIdOptional.get().getValue();
        String templateConfigCacheKey = MESSAGE_TEMPLATE_CACHE_PREFIX_KEY + templateId;
        Optional<CanalEntry.Column> enableStatusOptional = rowData.getAfterColumnsList().stream()
                .filter(each -> Objects.equals("enable_status", each.getName()))
                .filter(each -> Objects.equals(EnableStatusEnum.NOT_ENABLED.getStatus(), each.getValue()))
                .findFirst();
        // 如果 enable_status == EnableStatusEnum.NOT_ENABLED 代表模板未启用，仅删除缓存即可
        if (enableStatusOptional.isPresent()) {
            distributedCache.delete(templateConfigCacheKey);
            log.info("Canal监听成功删除消息模板缓存：{}", templateConfigCacheKey);
            return;
        }
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(LUA_CANAL_SCRIPT_SOURCE_PATH)));
        // 如果模板为启用状态，需要删除旧缓存的同时将新的缓存更新
        TemplateConfigDO templateConfigDO = templateConfigMapper.selectOne(Wrappers.lambdaQuery(TemplateConfigDO.class).eq(TemplateConfigDO::getTemplateId, templateId));
        ((RedisTemplate) distributedCache.getInstance()).execute(redisScript, Lists.newArrayList(templateConfigCacheKey), JSON.toJSONString(templateConfigDO));
        log.info("Canal监听成功更新消息模板缓存：{}", templateConfigCacheKey);
    }
}
