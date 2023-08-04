package com.aurora.order.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class MyRequestOriginParser implements RequestOriginParser {
    /**
     * 通过request获取来源标识，交给授权规则进行匹配
     * @param request
     * @return
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 标识字段名称可以自定义
        String origin = request.getParameter("serviceName");
//        if (StringUtil.isBlank(origin)){
//            throw new IllegalArgumentException("serviceName参数未指定");
//        }
        return origin;
    }
}