package cn.dyf.dubbo.demo.develop.consumer;

import cn.dyf.dubbo.demo.service.DevelopService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author dyf777
 * @since 2023/5/14 09:31
 */
@Component
public class DemoTask implements CommandLineRunner {
    @DubboReference(group = "group1",version = "1.0")
    private DevelopService developService;

    @DubboReference(group = "group2",version = "2.0")
    private DevelopService developServiceV2;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Dubbo Remote Return ======> " + developService.invoke("1"));
        //调用DevelopService的另一个实现
        System.out.println("Dubbo Remote Return ======> " + developServiceV2.invoke("2"));
    }
}
