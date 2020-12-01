package ${package};

import com.colourful.colourful.common.feign.annotation.EnableColourfulFeignClients;
import com.colourful.colourful.common.security.annotation.EnableColourfulResourceServer;
import com.colourful.colourful.common.swagger.annotation.EnableColourfulSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author colourful archetype
 * <p>
 * 项目启动类
 */
@EnableColourfulSwagger2
@SpringCloudApplication
@EnableColourfulFeignClients
@EnableColourfulResourceServer
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
