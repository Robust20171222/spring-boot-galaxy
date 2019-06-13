package com.galaxy.bigdata.dropwizard;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengwang
 * @date 2019/06/10
 */
public class JacksonTest {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, false);
    }

    public static void main(String[] args) throws Exception{
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "mkyong");
        map1.put("age", "37");

        Map<String, String> map = new HashMap<>();
        map.putAll(map1);
        map.put("age2","33");

        try {

            // convert map to JSON string
            String json = objectMapper.writeValueAsString(map);

            System.out.println(json);   // compact-print

            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

            System.out.println(json);   // pretty-print

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}