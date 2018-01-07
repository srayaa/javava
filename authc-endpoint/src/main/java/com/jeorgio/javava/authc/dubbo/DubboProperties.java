package com.jeorgio.javava.authc.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.dubbo")
@lombok.Data
@Component
public class DubboProperties {

    private String scan;

    private ApplicationConfig application;

    private RegistryConfig registry;

    private ProtocolConfig protocol;

    private ProviderConfig provider;

    //private ConsumerConfig consumer;
}