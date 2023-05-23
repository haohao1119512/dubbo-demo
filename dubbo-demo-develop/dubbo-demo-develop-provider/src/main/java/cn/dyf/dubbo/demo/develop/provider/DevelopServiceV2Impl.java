package cn.dyf.dubbo.demo.develop.provider;

import cn.dyf.dubbo.demo.service.DevelopService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author dyf777
 * @since 2023/5/14 08:57
 */
@DubboService(group = "group2", version = "2.0")
public class DevelopServiceV2Impl implements DevelopService {
    @Override
    public String invoke(String param) {
        return "ServiceV2 param:" + param;
    }
}
