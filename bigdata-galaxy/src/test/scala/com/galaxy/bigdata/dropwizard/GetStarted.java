package com.galaxy.bigdata.dropwizard;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

/**
 * @author pengwang
 * @date 2019/06/04
 */
public class GetStarted {

    private static final MetricRegistry registry = new MetricRegistry();

    static {
        registry.register("gc", new GarbageCollectorMetricSet());
        registry.register("buffers", new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));
        registry.register("memory", new MemoryUsageGaugeSet());
        registry.register("threads", new ThreadStatesGaugeSet());
        registry.register("classLoading", new ClassLoadingGaugeSet());
    }

    /**
     * 测试JVM Metric
     */
    @Test
    public void testJson() {
        try {
            ObjectMapper jsonMapper = new ObjectMapper().registerModule(new MetricsModuleTest(TimeUnit.MILLISECONDS, TimeUnit.MILLISECONDS, false, MetricFilter.ALL));
            String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(registry);

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("hostname", InetAddress.getLocalHost().getHostName());
            dataMap.put("ip", InetAddress.getLocalHost().getHostAddress());
            dataMap.put("metric", json);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("code", 200);
            resultMap.put("data", dataMap);

            System.out.println(jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap));
        } catch (JsonProcessingException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetGaugesJson() {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            SortedMap<String, Gauge> gaugeSortedMap = registry.getGauges();
            Map map = new LinkedHashMap();
            for (Map.Entry<String, Gauge> entry : gaugeSortedMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue().getValue());
            }
            String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConsole() {
        try {
            ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).convertRatesTo(TimeUnit.SECONDS)
                    .convertDurationsTo(TimeUnit.MILLISECONDS).build();
            reporter.start(30, TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}