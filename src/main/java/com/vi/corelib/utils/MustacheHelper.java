package com.vi.corelib.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public abstract class MustacheHelper {

    @SneakyThrows
    public static String convert(String template, JsonNode variables) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache;

        mustache = mf.compile(new StringReader(template),"template");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.convertValue(variables, new TypeReference<Map<String, Object>>(){});

        StringWriter writer = new StringWriter();
        mustache.execute(writer, result).flush();
        return writer.toString();
    }

    @SneakyThrows
    public static String convert(String template, Map<String,Object> variables) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache;

        mustache = mf.compile(new StringReader(template),"template");

        ObjectMapper mapper = new ObjectMapper();
        //Map<String, Object> result = mapper.convertValue(variables, new TypeReference<Map<String, Object>>(){});

        StringWriter writer = new StringWriter();
        mustache.execute(writer, variables).flush();
        return writer.toString();
    }
}
