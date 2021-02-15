package com.atguigu.serviceedu.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFeignConfiguration {

    @Value("${feign.client.config.springApplicationName.connect-timeout}")
    private int connectTimeout;

    @Value("${feign.client.config.springApplicationName.read-timeout}")
    private int readTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout);
    }
}
