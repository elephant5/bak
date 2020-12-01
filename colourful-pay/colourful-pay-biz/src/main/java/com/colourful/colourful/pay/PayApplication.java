package com.colourful.colourful.pay;

import com.colourful.colourful.common.feign.annotation.EnableColourfulFeignClients;
import com.colourful.colourful.common.security.annotation.EnableColourfulResourceServer;
import com.colourful.colourful.common.swagger.annotation.EnableColourfulSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pigx archetype
 * <p>
 * 项目启动类
 */
@EnableColourfulSwagger2
@SpringCloudApplication
@EnableColourfulFeignClients
@EnableColourfulResourceServer
@EnableAutoConfiguration
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
	
}
