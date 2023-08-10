package org.opengoofy.assault.messageservice.biz.handler.send.base;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.assault.messageservice.api.dto.MessageSendRequestDTO;
import org.opengoofy.assault.messageservice.biz.base.strategy.AbstractExecuteStrategy;
import org.opengoofy.assault.messageservice.biz.dto.MessagePlatformSendResponseDTO;
import org.opengoofy.assault.messageservice.biz.handler.select.MessageSendChannelSelector;
import org.opengoofy.assault.messageservice.biz.mq.event.MessageSendEvent;

import javax.annotation.Resource;

import static org.opengoofy.assault.messageservice.biz.common.MessageConstants.SMS_MESSAGE_CHANNELS;

/**
 * 抽象消息发送服务
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
public abstract class AbstractMessageSendService implements MessageSendService, AbstractExecuteStrategy<MessageSendEvent, MessagePlatformSendResponseDTO> {
    
    @Resource
    private MessageSendChannelSelector messageSendChannelSelector;
    
    @Override
    public MessagePlatformSendResponseDTO send(MessageSendEvent messageSendEvent) throws Exception {
        MessageSendRequestDTO messageSendRequest = messageSendEvent.getMessageSendRequest();
        if (SMS_MESSAGE_CHANNELS.contains(messageSendRequest.getMsgType())) {
            MessagePlatformSendResponseDTO executeResp = executeResp(messageSendEvent);
            if (!executeResp.getSuccess() && CollUtil.isNotEmpty(messageSendEvent.getSmsOptionalChannels())) {
                // 选择发送消息具体实现
                MessageSendService messageSendService = messageSendChannelSelector.select(messageSendEvent);
                executeResp = messageSendService.send(messageSendEvent);
            }
            return executeResp;
        }
        return executeResp(messageSendEvent);
    }
}
