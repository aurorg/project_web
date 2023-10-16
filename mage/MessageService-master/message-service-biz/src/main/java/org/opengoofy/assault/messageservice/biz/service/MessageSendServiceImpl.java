package org.opengoofy.assault.messageservice.biz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.assault.framework.starter.distributedid.SnowflakeIdUtil;
import org.opengoofy.assault.messageservice.api.dto.MessageSendRequestDTO;
import org.opengoofy.assault.messageservice.api.dto.MessageSendResponseDTO;
import org.opengoofy.assault.messageservice.biz.common.MessageChainMarkEnum;
import org.opengoofy.assault.messageservice.biz.common.MessageRocketMQConstants;
import org.opengoofy.assault.messageservice.biz.common.MessageTypeEnum;
import org.opengoofy.assault.messageservice.biz.handler.filter.base.AbstractChainContext;
import org.opengoofy.assault.messageservice.biz.mq.consumer.OtherMessageSendConsumer;
import org.opengoofy.assault.messageservice.biz.mq.consumer.SmsVerificationMessageSendConsumer;
import org.opengoofy.assault.messageservice.biz.mq.event.MessageSendEvent;
import org.opengoofy.assault.messageservice.biz.mq.produce.MessageCommonSendProduce;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 消息发送接口实现
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageSendServiceImpl implements MessageSendService {
    
    private final AbstractChainContext abstractChainContext;
    private final MessageCommonSendProduce messageCommonSendProduce;
    private final OtherMessageSendConsumer otherMessageSendConsumer;
    private final SmsVerificationMessageSendConsumer smsVerificationMessageSendConsumer;
    
    @Override
    public MessageSendResponseDTO messageSend(MessageSendRequestDTO requestParam) {
        // 责任链模式验证消息发送入参是否合理
        abstractChainContext.handler(MessageChainMarkEnum.MESSAGE_SEND_FILTER.name(), requestParam);
        MessageSendEvent messageSendEvent = buildMessageSendEvent(requestParam);
        // 通过 RocketMQ 削峰消息发送流程，避免应用负载过大
        messageCommonSendProduce.send(messageSendEvent, messageSendEvent.getMsgId(), getTagByMsgType(requestParam));
        return new MessageSendResponseDTO(messageSendEvent.getMsgId());
    }
    
    @Override
    public MessageSendResponseDTO syncMessageSend(MessageSendRequestDTO requestParam) {
        // 责任链模式验证消息发送入参是否合理
        abstractChainContext.handler(MessageChainMarkEnum.MESSAGE_SEND_FILTER.name(), requestParam);
        MessageSendEvent messageSendEvent = buildMessageSendEvent(requestParam);
        // 判断是验证码消息还是其它消息
        if (Objects.equals(requestParam.getMsgType(), MessageTypeEnum.SMS_VERIFICATION_MESSAGE.getType())) {
            smsVerificationMessageSendConsumer.onMessage(messageSendEvent);
        } else {
            otherMessageSendConsumer.onMessage(messageSendEvent);
        }
        return new MessageSendResponseDTO(messageSendEvent.getMsgId());
    }
    
    /**
     * 构建消息发送异步事件
     */
    private MessageSendEvent buildMessageSendEvent(MessageSendRequestDTO requestParam) {
        // 通过雪花算法生成唯一的分布式消息ID
        String msgId = SnowflakeIdUtil.nextIdStr();
        // 创建消息发送事件
        return MessageSendEvent.builder().messageSendRequest(requestParam).msgId(msgId).build();
    }
    
    /**
     * 根据消息类型获取 Tag
     */
    private String getTagByMsgType(MessageSendRequestDTO requestParam) {
        Integer msgType = requestParam.getMsgType();
        if (msgType == MessageTypeEnum.SMS_VERIFICATION_MESSAGE.getType()) {
            return MessageRocketMQConstants.SMS_MESSAGE_VERIFICATION_SEND_TAG;
        }
        return MessageRocketMQConstants.OTHER_MESSAGE_SEND_TAG;
    }
}
