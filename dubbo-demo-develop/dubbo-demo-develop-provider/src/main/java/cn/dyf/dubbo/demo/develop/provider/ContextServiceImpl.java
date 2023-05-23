package cn.dyf.dubbo.demo.develop.provider;

import cn.dyf.dubbo.demo.service.ContextService;
import com.alibaba.fastjson2.JSON;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Map;

/**
 * @author dyf777
 * @since 2023/5/14 11:05
 */
@DubboService
public class ContextServiceImpl implements ContextService {
    @Override
    public String invoke(String param) {
        //ServerAttachment接收客户端传递过来的参数
        Map<String, Object> serverAttachment = RpcContext.getServerContext().getObjectAttachments();
        System.out.println("ContextService serverAttachments:" + JSON.toJSONString(serverAttachment));
        //往客户端传递参数
        RpcContext.getServerContext().setAttachment("server-key1", "server-value1");
        return "ContextService param:" + param;
    }
}
