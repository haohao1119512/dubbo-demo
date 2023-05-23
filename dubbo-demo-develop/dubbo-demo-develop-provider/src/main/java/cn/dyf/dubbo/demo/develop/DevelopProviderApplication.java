package cn.dyf.dubbo.demo.develop;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dyf777
 * @since 2023/5/14 09:15
 */
@EnableDubbo
@SpringBootApplication
public class DevelopProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevelopProviderApplication.class, args);
    }
}
