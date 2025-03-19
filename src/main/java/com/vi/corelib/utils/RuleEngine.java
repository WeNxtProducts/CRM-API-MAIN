package com.vi.corelib.utils;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.List;

public abstract class RuleEngine {

    //public static  ScriptEngine engine = manager.getEngineByName("graal.js");

    @SneakyThrows
    public static Object executeRule(String expression,JsonNode variables) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("graal.js");
        expression =  MustacheHelper.convert(expression,variables);
        Object obj = engine.eval(expression);
        return obj;
    }

    @SneakyThrows
    public static Boolean executeRule(List<String> conditions, JsonNode variables) {
        for (String expression : conditions) {
            try {
                Boolean result = (Boolean) executeRule(expression, variables);
                if(result) {
                    return true;
                }
            } catch(Exception e) {
                return false;
            }
        }
        return false;
    }

    @SneakyThrows
    public static Object executeRule(String expression, HashMap<String,String> variablesHasMap) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("graal.js");

        JsonNode variables = JsonHelper.toJson(variablesHasMap);
        return executeRule(expression,variables);
    }


}
