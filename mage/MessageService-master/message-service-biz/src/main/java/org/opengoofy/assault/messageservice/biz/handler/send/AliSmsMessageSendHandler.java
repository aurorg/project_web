package org.opengoofy.assault.messageservice.biz.handler.send;

import com.alibaba.fastjson2.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.assault.messageservice.api.dto.MessageSendRequestDTO;
import org.opengoofy.assault.messageservice.biz.dto.MessagePlatformSendResponseDTO;
import org.opengoofy.assault.messageservice.biz.handler.send.base.AbstractMessageSendService;
import org.opengoofy.assault.messageservice.biz.handler.send.base.MessageSendService;
import org.opengoofy.assault.messageservice.biz.mq.event.MessageSendEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.opengoofy.assault.messageservice.biz.common.MessageConstants.SMS_MESSAGE_KEY;

/**
 * 阿里云短信发送组件
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@Component
public class AliSmsMessageSendHandler extends AbstractMessageSendService implements MessageSendService {
    
    /**
     * 调用阿里云发送短信成功返回标识
     */
    private final static String RETURN_SUCCESS_FLAG = "OK";
    
    @Override
    public String mark() {
        return SMS_MESSAGE_KEY + "ALI";
    }
    
    @SneakyThrows
    public MessagePlatformSendResponseDTO executeResp(MessageSendEvent messageSendEvent) {
        MessageSendRequestDTO messageSendRequest = messageSendEvent.getMessageSendRequest();
        Config config = new Config()
                .setAccessKeyId("xxx")
                .setAccessKeySecret("xxx")
                .setEndpoint("");
        Client client = new Client(config);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setOutId(messageSendEvent.getMsgId())
                .setPhoneNumbers(messageSendRequest.getReceiver())
                .setTemplateCode("xxx")
                .setTemplateParam("xxx")
                .setSignName("xxx");
        SendSmsResponse sendSmsResponse;
        MessagePlatformSendResponseDTO responseDTO = null;
        try {
            sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, new RuntimeOptions());
            SendSmsResponseBody body = sendSmsResponse.getBody();
            if (!Objects.equals(body.getCode(), RETURN_SUCCESS_FLAG)) {
                responseDTO = MessagePlatformSendResponseDTO.builder().code(body.getCode()).errMsg(body.getMessage()).success(false).build();
            }
        } catch (Throwable ex) {
            log.error("阿里云短信调用失败，入参：{}，错误信息：{}", JSON.toJSONString(messageSendEvent), ex.getMessage());
            responseDTO = MessagePlatformSendResponseDTO.builder().code("-1").errMsg(ex.getMessage()).success(false).build();
        }
        return responseDTO;
    }
}
