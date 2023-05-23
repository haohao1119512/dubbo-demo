package cn.dyf.dubbo.demo.develop.provider;

import cn.dyf.dubbo.demo.service.AsyncService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dyf777
 * @since 2023/5/14 09:47
 */
@DubboService
public class AsyncServiceImpl implements AsyncService {
    @Override
    public String invoke(String param) {
        try {
            long time = ThreadLocalRandom.current().nextLong(1000);
            Thread.sleep(time);
            return "AsyncService invoke param:" + param + ",sleep:" + time;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Override
    public CompletableFuture<String> asyncInvoke(String param) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long time = ThreadLocalRandom.current().nextLong(1000);
                Thread.sleep(time);
                return "AsyncService asyncInvoke param:" + param + ",sleep:" + time;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        });
    }

    @Override
    public String asyncContextInvoke(String param) {
        final AsyncContext asyncContext = RpcContext.startAsync();
        Executors.newSingleThreadExecutor().execute(() -> {
            // 如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            long time = ThreadLocalRandom.current().nextLong(1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 写回响应
            asyncContext.write("AsyncService asyncInvoke param:" + param + ",sleep:" + time);
        });
        return null;
    }
}
