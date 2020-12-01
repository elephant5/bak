package com.colourful.colourful.order;

import com.colourful.colourful.common.feign.annotation.EnableColourfulFeignClients;
import com.colourful.colourful.common.security.annotation.EnableColourfulResourceServer;
import com.colourful.colourful.common.swagger.annotation.EnableColourfulSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;

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
public class OrderApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}

