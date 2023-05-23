package cn.dyf.dubbo.demo.develop.provider;

import cn.dyf.dubbo.demo.service.DevelopService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author dyf777
 * @since 2023/5/14 08:57
 */
@DubboService(group = "group1", version = "1.0")
public class DevelopServiceV1Impl implements DevelopService {
    @Override
    public String invoke(String param) {
        return "ServiceV1 param:" + param;
    }
}
