package org.opengoofy.assault.messageservice.biz.base.message;

import cn.hutool.core.util.IdUtil;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 消息体包装
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Data
@RequiredArgsConstructor
public class MessageWrapper<T> implements Serializable {
    
    private String uuid = IdUtil.randomUUID();
    
    private String traceId;
    
    private Long timestamp = System.currentTimeMillis();
    
    @NonNull
    private String keys;
    
    @NonNull
    private T data;
}
