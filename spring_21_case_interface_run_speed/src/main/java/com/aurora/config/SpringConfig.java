package com.aurora.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.aurora")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class,MybatisConfig.class})
@EnableAspectJAutoProxy
public class SpringConfig {
}
