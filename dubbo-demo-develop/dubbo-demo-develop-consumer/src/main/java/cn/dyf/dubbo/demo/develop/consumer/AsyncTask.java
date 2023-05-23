package cn.dyf.dubbo.demo.develop.consumer;

import cn.dyf.dubbo.demo.service.AsyncService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author dyf777
 * @since 2023/5/14 10:17
 */
@Component
public class AsyncTask implements CommandLineRunner {
    @DubboReference
    private AsyncService asyncService;
    @Override
    public void run(String... args) throws Exception {
        // 调用异步接口
        CompletableFuture<String> future1 = asyncService.asyncInvoke("async call request1");
        future1.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("AsyncTask Response-1: " + v);
            }
        });
        // 两次调用并非顺序返回
        System.out.println("Response-2-Sync: " + asyncService.asyncContextInvoke("async call request2"));
        RpcContext.getServiceContext().getCompletableFuture().whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("AsyncTask Response-2: " + v);
            }
        });
        //consumer异步调用
        CompletableFuture.supplyAsync(() -> asyncService.invoke("invoke call request3")).whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("AsyncTask Response-3: " + v);
            }
        });
    }
}
