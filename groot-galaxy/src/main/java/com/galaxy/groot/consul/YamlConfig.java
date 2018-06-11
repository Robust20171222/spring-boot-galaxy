package com.galaxy.groot.consul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wangpeng
 * Date: 2018/6/8
 * Time: 22:46
 */
//@Component
public class YamlConfig {

    private static final Logger log = LogManager.getLogger(YamlConfig.class);

    @Autowired
    private ConsulService consulServiceImpl;

    @PostConstruct
    public void init() {
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

        ClassPathResource resource = new ClassPathResource("application.yml");
        try {
            List<PropertySource<?>> propertySourceList = yamlPropertySourceLoader.load("applicationConfig: [classpath:/bootstrap.yml]", resource);

            List<String> keyList = this.consulServiceImpl.getKVKeysOnly();
            log.info("Found keys : {}", keyList);

            for (PropertySource propertySource : propertySourceList) {
                Map<String, Object> ymlProperties = (Map<String, Object>) propertySource.getSource();

                if (keyList != null) {// 如果consul中已存在键值，则不重新设置
                    for (Map.Entry<String, Object> prop : ymlProperties.entrySet()) {
                        String key = prop.getKey();
                        String value = prop.getValue().toString();

                        if (!keyList.contains(key)) {
                            this.consulServiceImpl.setKVValue(key, value);
                        }
                    }
                } else {
                    for (Map.Entry<String, Object> prop : ymlProperties.entrySet()) {

                        String key = prop.getKey();
                        String value = prop.getValue().toString();
                        this.consulServiceImpl.setKVValue(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
