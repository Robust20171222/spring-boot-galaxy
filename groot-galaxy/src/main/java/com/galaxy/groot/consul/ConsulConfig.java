package com.galaxy.groot.consul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cfg4j.source.context.propertiesprovider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 提供加载配置到consul中的操作
 * <p>
 * Created by wangpeng
 * Date: 2018/6/4
 * Time: 14:42
 */
@Component
public class ConsulConfig {

    private static final Logger log = LogManager.getLogger(ConsulConfig.class);

    @Autowired
    private ConsulService consulServiceImpl;

    @PostConstruct
    private void init() {
        PropertiesProviderSelector propertiesProviderSelector = new PropertiesProviderSelector(
                new PropertyBasedPropertiesProvider(), new YamlBasedPropertiesProvider(), new JsonBasedPropertiesProvider()
        );

        ClassPathResource resource = new ClassPathResource("application.yml");

        String fileName = resource.getFilename();

        if (fileName.endsWith(".yml") || fileName.endsWith(".properties")) {
            String path = null;
            try (InputStream input = resource.getInputStream()) {
                log.info("Found config file: " + resource.getFilename() + " in context " + resource.getURL().getPath());
                path = resource.getURL().getPath();

                PropertiesProvider provider = propertiesProviderSelector.getProvider(fileName);
                Properties ymlProperties = provider.getProperties(input);

                List<String> keyList = this.consulServiceImpl.getKVKeysOnly();
                log.info("Found keys : {}", keyList);

                if (keyList != null) {// 如果consul中已存在键值，则不重新设置
                    for (Map.Entry<Object, Object> prop : ymlProperties.entrySet()) {
                        String key = prop.getKey().toString();
                        String value = prop.getValue().toString();

                        if (!keyList.contains(key)) {
                            this.consulServiceImpl.setKVValue(key, value);
                        }
                    }
                } else {
                    for (Map.Entry<Object, Object> prop : ymlProperties.entrySet()) {
                        String key = prop.getKey().toString();
                        String value = prop.getValue().toString();
                        this.consulServiceImpl.setKVValue(key, value);
                    }
                }
            } catch (IOException e) {
                log.error("Unable to load properties from file: {},exception: {} ", path, e.getMessage());
            }
        }
    }
}
