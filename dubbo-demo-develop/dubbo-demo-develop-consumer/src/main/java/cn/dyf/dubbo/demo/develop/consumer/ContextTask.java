package cn.dyf.dubbo.demo.develop.consumer;

import cn.dyf.dubbo.demo.service.ContextService;
import com.alibaba.fastjson2.JSON;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author dyf777
 * @since 2023/5/14 11:07
 */
@Component
public class ContextTask implements CommandLineRunner {
    @DubboReference
    private ContextService contextService;
    @Override
    public void run(String... args) throws Exception {
        RpcContext.getClientAttachment().setAttachment("clientKey1", "clientValue1");
        String res = contextService.invoke("context1");
        //接收传递回来参数
        Map<String, Object> clientAttachment = RpcContext.getServerContext().getObjectAttachments();
        System.out.println("ContextTask clientAttachment:" + JSON.toJSONString(clientAttachment));
        System.out.println("ContextService Return : " + res);
    }
}
