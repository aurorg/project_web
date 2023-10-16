package com.nageoffer.hippo4j.hippo4jconfignacosdemo;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadPoolExecutor;

@RequiredArgsConstructor
@EnableDynamicThreadPool
@SpringBootApplication
public class Hippo4jConfigNacosDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Hippo4jConfigNacosDemoApplication.class, args);
    }

    private final ThreadPoolExecutor messageCoumerThreadPoolExecutor;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(messageCoumerThreadPoolExecutor.getCorePoolSize());
        System.out.println(messageCoumerThreadPoolExecutor.getMaximumPoolSize());
    }
}
