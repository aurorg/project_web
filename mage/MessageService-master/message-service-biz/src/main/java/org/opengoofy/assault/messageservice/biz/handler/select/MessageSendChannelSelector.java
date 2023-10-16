package org.opengoofy.assault.messageservice.biz.handler.select;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.assault.messageservice.api.dto.MessageSendRequestDTO;
import org.opengoofy.assault.messageservice.biz.base.strategy.AbstractStrategyChoose;
import org.opengoofy.assault.messageservice.biz.handler.select.weight.SmsChannelWeightRule;
import org.opengoofy.assault.messageservice.biz.handler.send.base.MessageSendService;
import org.opengoofy.assault.messageservice.biz.mq.event.MessageSendEvent;
import org.springframework.stereotype.Component;

import static org.opengoofy.assault.messageservice.biz.common.MessageConstants.SMS_MESSAGE_CHANNELS;
import static org.opengoofy.assault.messageservice.biz.common.MessageConstants.SMS_MESSAGE_KEY;
import static org.opengoofy.assault.messageservice.biz.common.MessageTypeEnum.getPlatformByType;

/**
 * 消息发送渠道选择器
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSendChannelSelector {
    
    private final SmsChannelWeightRule smsChannelWeightRule;
    private final AbstractStrategyChoose abstractStrategyChoose;
    
    /**
     * 根据消息发送入参选择对应消息发送服务
     * 因为短信会存在多个供应商，所以短信发送底层会加入权重的概念，根据不同权重选择发送服务
     */
    public MessageSendService select(MessageSendEvent messageSendEvent) {
        MessageSendRequestDTO messageSendRequest = messageSendEvent.getMessageSendRequest();
        // 短信消息处理
        if (SMS_MESSAGE_CHANNELS.contains(messageSendRequest.getMsgType())) {
            String selectChannel = smsChannelWeightRule.choose(messageSendRequest.getMsgType(), messageSendEvent.getSmsOptionalChannels());
            // 删除短信可选择的渠道并设置为当前发送渠道
            messageSendEvent.removeAndSetSmsChannel(selectChannel);
            MessageSendService messageSendService = (MessageSendService) abstractStrategyChoose.choose(SMS_MESSAGE_KEY + StrUtil.split(selectChannel, "_").get(0));
            return messageSendService;
        }
        // 非短信消息外其它消息
        return (MessageSendService) abstractStrategyChoose.choose(getPlatformByType(messageSendRequest.getMsgType()));
    }
}
