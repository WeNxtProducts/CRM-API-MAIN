package com.vi.corelib.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.corelib.api.RequestBody;
import com.vi.corelib.api.RequestPatterns;
import lombok.SneakyThrows;

import java.util.*;

public class JsonHelper {
  public JsonHelper() {

  }

  public static String getText(JsonNode object, String key) {
    if (hasNode(object, key)) {
      return object.get(key).asText("");
    }
    return "";
  }

  public static JsonNode get(JsonNode object, String key) {
    if (object.has(key) && !object.get(key).isNull()) {
      return object.get(key);
    }
    return null;
  }

  @SneakyThrows
  public static JsonNode getNode(String json) {
    return new ObjectMapper().readTree(json);
  }

  public static boolean hasNode(JsonNode object, String key) {
    return object.has(key) && !object.get(key).isNull();
  }

  @SneakyThrows
  public JsonNode toJson(Class<?> object) {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.valueToTree(object);
  }

  @SneakyThrows
  public JsonNode toJson(String str) {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.valueToTree(str);
  }

  @SneakyThrows
  public static JsonNode stringToJson(String str) {
    return new ObjectMapper().readTree(str);
  }

  @SneakyThrows
  public Class<?> toObject(JsonNode json,Class<?> object) {
    ObjectMapper mapper = new ObjectMapper();
    return (Class<?>) mapper.treeToValue(json,Object.class);
  }
  @SneakyThrows
  public static Map<String,Object> toMap(JsonNode json) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> result = mapper.convertValue(json, new TypeReference<Map<String, Object>>(){});
    return result;
  }

  @SneakyThrows
  public static Map<String,String> toMapString(JsonNode json) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> result = mapper.convertValue(json, new TypeReference<Map<String, String>>(){});
    return result;
  }

  @SneakyThrows
  public static JsonNode toJson(HashMap<String,String> map) {
      ObjectMapper mapper= new ObjectMapper();
      return mapper.convertValue(map,JsonNode.class);
  }

  @SneakyThrows
  public static JsonNode toJson2(HashMap<String,Object> map) {
    ObjectMapper mapper= new ObjectMapper();
    return mapper.convertValue(map,JsonNode.class);
  }
  @SneakyThrows
  public static JsonNode toJson(List<RequestBody> bodyList) {
    var smsContent = new HashMap<String, Object>();
    for(RequestBody body : bodyList) {
      if(body.getDataType().equals("object")) {
        smsContent.put(body.getName(),toJson(body.getObjectList()));
      } else {
        smsContent.put(body.getName(), body.getValue());
      }
    }
    return toJson2(smsContent);
  }
  @SneakyThrows
  public static List<String> getKeys(JsonNode json) {
    List<String> keys = new ArrayList<>();
    Iterator<String> iterator = json.fieldNames();
    iterator.forEachRemaining(e -> keys.add(e));
    return keys;
  }
}
