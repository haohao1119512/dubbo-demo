package cn.dyf.dubbo.demo.develop.consumer;

import com.alibaba.fastjson2.JSON;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author dyf777
 * @since 2023/5/14 14:12
 */
@Component
public class GenericTask implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        GenericService genericService = buildGenericService("cn.dyf.dubbo.demo.service.DevelopService", "group2", "2.0");
        Object result = genericService.$invoke("invoke", new String[]{"java.lang.String"}, new Object[]{"g1"});
        System.out.println("GenericTask Response: " + JSON.toJSONString(result));
    }

    private GenericService buildGenericService(String interfaceClass, String group, String version) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(interfaceClass);
        referenceConfig.setVersion(version);
        // 开启泛化调用
        referenceConfig.setGeneric("true");
        referenceConfig.setTimeout(3000);
        referenceConfig.setGroup(group);
        SimpleReferenceCache cache = SimpleReferenceCache.getCache();
        try {
            return cache.get(referenceConfig);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
