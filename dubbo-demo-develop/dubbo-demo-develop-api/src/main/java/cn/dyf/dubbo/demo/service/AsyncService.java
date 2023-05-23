package cn.dyf.dubbo.demo.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author dyf777
 * @since 2023/5/14 09:44
 */
public interface AsyncService {
    /**
     * 同步调用方法
     * @param param 参数
     * @return 返回值
     */
    String invoke(String param);

    /**
     * 异步调用方法
     * @param param 参数
     * @return 返回值
     */
    CompletableFuture<String> asyncInvoke(String param);

    /**
     * 异步调用方法 使用AsyncContext实现异步
     * @param param 参数
     * @return 返回值
     */
    String asyncContextInvoke(String param);
}
