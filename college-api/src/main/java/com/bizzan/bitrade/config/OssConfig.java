package com.bizzan.bitrade.config;

import com.bizzan.bitrade.component.OssTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class OssConfig {

    @ConfigurationProperties(prefix = "aliyun.oss")
    @Bean
    public OssTemplate ossTemplate(){
        return new OssTemplate();
    }

}
