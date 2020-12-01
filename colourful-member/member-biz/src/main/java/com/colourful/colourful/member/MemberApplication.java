package com.colourful.colourful.member;

import com.colourful.colourful.common.feign.annotation.EnableColourfulFeignClients;
import com.colourful.colourful.common.security.annotation.EnableColourfulResourceServer;
import com.colourful.colourful.common.swagger.annotation.EnableColourfulSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableColourfulSwagger2
@SpringCloudApplication
@EnableColourfulFeignClients
@EnableColourfulResourceServer
@EnableAutoConfiguration
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
