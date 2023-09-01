package com.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author DongXin
 * @date 2022/8/16 10:43 上午
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
@Component
public class XxlJobProperties {

    private String adminAddresses;

    private String accessToken;

    private Exexutor exexutor;

}

@Data
@ConfigurationProperties(prefix = "xxl.job.exexutor")
@Configuration
class Exexutor {

    private String appName;

    private String address;

    private String ip;

    private int port;

    private String logPath;

    private int logRetentionDays;
}