/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.assault.messageservice.biz.mq.produce;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.opengoofy.assault.messageservice.biz.common.MessageRocketMQConstants;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.opengoofy.assault.messageservice.biz.common.MessageRocketMQConstants.MESSAGE_COMMON_TOPIC;

/**
 * 消息发送生产者
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@Component
@AllArgsConstructor
public class MessageCommonSendProduce {
    
    private final RocketMQTemplate rocketMQTemplate;
    
    /**
     * 消息发送，默认 Topic {@link MessageRocketMQConstants#MESSAGE_COMMON_TOPIC}
     */
    public void send(Object messageSendEvent, String keys, String tag) {
        send(messageSendEvent, MESSAGE_COMMON_TOPIC, keys, tag);
    }
    
    /**
     * 消息发送，自定义 Topic
     */
    public void send(Object messageSendEvent, String topic, String keys, String tag) {
        keys = StrUtil.isEmpty(keys) ? UUID.randomUUID().toString() : keys;
        Message<?> message = MessageBuilder
                .withPayload(messageSendEvent)
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .build();
        try {
            String topicAndTag = StrUtil.builder()
                    .append(topic)
                    .append(":")
                    .append(tag)
                    .toString();
            SendResult sendResult = rocketMQTemplate.syncSend(topicAndTag, message, 2000);
            log.info("消息发送结果：{}，消息ID：{}，消息Keys：{}", sendResult.getSendStatus(), sendResult.getMsgId(), keys);
        } catch (Throwable ex) {
            log.error("消息发送失败，消息体：{}", JSON.toJSONString(messageSendEvent), ex);
        }
    }
}
