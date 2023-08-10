package org.opengoofy.assault.messageservice.biz.mq.consumer;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.opengoofy.assault.framework.starter.common.toolkit.BeanUtil;
import org.opengoofy.assault.framework.starter.idempotent.annotation.Idempotent;
import org.opengoofy.assault.framework.starter.idempotent.enums.IdempotentSceneEnum;
import org.opengoofy.assault.framework.starter.idempotent.enums.IdempotentTypeEnum;
import org.opengoofy.assault.messageservice.api.dto.MessageSendRequestDTO;
import org.opengoofy.assault.messageservice.biz.common.MessageRocketMQConstants;
import org.opengoofy.assault.messageservice.biz.common.MessageSendStatusEnum;
import org.opengoofy.assault.messageservice.biz.dao.entity.SendRecordDO;
import org.opengoofy.assault.messageservice.biz.dao.entity.SendRecordExtendDO;
import org.opengoofy.assault.messageservice.biz.dao.mapper.SendRecordExtendMapper;
import org.opengoofy.assault.messageservice.biz.dao.mapper.SendRecordMapper;
import org.opengoofy.assault.messageservice.biz.dto.MessagePlatformSendResponseDTO;
import org.opengoofy.assault.messageservice.biz.mq.event.MessageSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.opengoofy.assault.messageservice.biz.common.MessageConstants.SMS_MESSAGE_CHANNELS;

/**
 * 公共消息保存消费者，包括：短信、微信、企业微信、邮箱等
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = MessageRocketMQConstants.MESSAGE_COMMON_TOPIC,
        selectorExpression = MessageRocketMQConstants.COMMON_MESSAGE_SAVE_TAG,
        consumerGroup = MessageRocketMQConstants.COMMON_MESSAGE_SAVE_CG
)
public class CommonMessageSaveConsumer implements RocketMQListener<MessageSaveEvent> {
    
    private final SendRecordMapper sendRecordMapper;
    private final SendRecordExtendMapper sendRecordExtendMapper;
    
    @Idempotent(
            uniqueKeyPrefix = "common_message_save:",
            key = "#messageSaveEvent.msgId+'_'+#messageSaveEvent.hashCode()",
            type = IdempotentTypeEnum.SPEL,
            scene = IdempotentSceneEnum.MQ,
            keyTimeout = 7200L
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onMessage(MessageSaveEvent messageSaveEvent) {
        try {
            MessageSendRequestDTO messageSendRequest = messageSaveEvent.getMessageSendRequest();
            MessagePlatformSendResponseDTO platformSendResponse = messageSaveEvent.getMessagePlatformSendResponse();
            // 组装短信发送记录持久层实体 & 短信参数持久层实体
            SendRecordDO sendRecordDO = BeanUtil.convert(messageSendRequest, SendRecordDO.class);
            sendRecordDO.setSender(messageSaveEvent.getCurrentSendChannel());
            sendRecordDO.setMsgId(messageSaveEvent.getMsgId());
            SendRecordExtendDO sendRecordExtendDO = SendRecordExtendDO.builder()
                    .msgId(messageSaveEvent.getMsgId())
                    .msgParam(messageSendRequest.getParamList().toString())
                    .build();
            // 调用失败，记录调用三方平台失败信息
            if (platformSendResponse != null && !platformSendResponse.getSuccess()) {
                int failStatus = SMS_MESSAGE_CHANNELS.contains(messageSendRequest.getMsgType()) ? MessageSendStatusEnum.SUBMIT_FAIL.getCode() : MessageSendStatusEnum.SEND_FAIL.getCode();
                sendRecordDO.setStatus(failStatus);
                sendRecordDO.setFailInfo(JSON.toJSONString(platformSendResponse));
            } else {
                int successStatus = SMS_MESSAGE_CHANNELS.contains(messageSendRequest.getMsgType()) ? MessageSendStatusEnum.SEND_PROGRESS.getCode() : MessageSendStatusEnum.SEND_SUCCESS.getCode();
                sendRecordDO.setStatus(successStatus);
            }
            sendRecordDO.setSendTime(new Date());
            try {
                sendRecordMapper.insert(sendRecordDO);
                sendRecordExtendMapper.insert(sendRecordExtendDO);
            } catch (Exception ex) {
                log.error("保存消息发送&参数记录错误，错误信息：{}", ex.getMessage());
            }
        } catch (Throwable ex) {
            log.error("消息入库流程执行失败", ex);
        }
    }
}
