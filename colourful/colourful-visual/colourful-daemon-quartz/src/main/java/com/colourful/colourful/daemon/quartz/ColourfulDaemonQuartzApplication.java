package com.colourful.colourful.daemon.quartz;

import com.colourful.colourful.common.feign.annotation.EnableColourfulFeignClients;
import com.colourful.colourful.common.security.annotation.EnableColourfulResourceServer;
import com.colourful.colourful.common.swagger.annotation.EnableColourfulSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author frwcloud
 * @date 2019/01/23 定时任务模块
 */
@EnableColourfulSwagger2
@EnableColourfulFeignClients
@SpringCloudApplication
@EnableColourfulResourceServer
public class ColourfulDaemonQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColourfulDaemonQuartzApplication.class, args);
	}

}
