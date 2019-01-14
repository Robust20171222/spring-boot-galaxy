package com.galaxy.bigdata.spark.livy;

import org.apache.livy.Job;
import org.apache.livy.JobContext;
import org.apache.livy.LivyClient;
import org.apache.livy.LivyClientBuilder;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng
 * Date: 2019-01-10
 * Time: 17:42
 */
public class PiJob implements Job<Double>, Function<Integer, Integer>,
        Function2<Integer, Integer, Integer> {

    private final int samples;

    public PiJob(int samples) {
        this.samples = samples;
    }

    @Override
    public Double call(JobContext ctx) throws Exception {
        List<Integer> sampleList = new ArrayList<Integer>();
        for (int i = 0; i < samples; i++) {
            sampleList.add(i + 1);
        }

        return 4.0d * ctx.sc().parallelize(sampleList).map(this).reduce(this) / samples;
    }

    @Override
    public Integer call(Integer v1) {
        double x = Math.random();
        double y = Math.random();
        return (x * x + y * y < 1) ? 1 : 0;
    }

    @Override
    public Integer call(Integer v1, Integer v2) {
        return v1 + v2;
    }

    public static void main(String[] args) throws Exception {
        String livyUrl = "http://quickstart.cloudera:8998";

        LivyClient client = new LivyClientBuilder()
                .setURI(new URI(livyUrl))
                .build();
        String piJar = "/Users/pengwang/Documents/TestProject/ucar/spark/spark-examples_2.11-2.1.1.jar";
        try {
            System.err.printf("Uploading %s to the Spark context...\n", piJar);
            client.uploadJar(new File(piJar)).get();

            int samples = 200;

            System.err.printf("Running PiJob with %d samples...\n", samples);
            double pi = client.submit(new PiJob(samples)).get();

            System.out.println("Pi is roughly: " + pi);
        } finally {
            client.stop(true);
        }
    }
}
