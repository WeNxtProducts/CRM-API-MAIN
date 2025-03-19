package com.vi.corelib.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.corelib.api.MicroService;
import com.vi.corelib.api.RequestPatterns;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.*;
import java.util.Properties;

public class Config {

  @Autowired
  public static Environment env;

  public Config() {

  }

  public static String getValue(String key) {
    if (env.getProperty(key) != null) {
      return env.getProperty(key);
    }
    return "";
  }
  
  public  String getProperty(String name) throws IOException {
    InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
//    FileReader reader=new FileReader("/resources/application.properties");
    Properties properties=new Properties();
    properties.load(input);
//    final Properties properties = new Properties();
//    properties.load(new FileInputStream("application.properties"));
//    System.out.println(properties.getProperty(name));
//    System.out.print(properties);
    return properties.getProperty(name);
  }

  public String getUrl(String server, String key) throws IOException, ParseException {
    var data = getProperty(server);
    JSONParser parser = new JSONParser();
    JSONObject obj = (JSONObject)parser.parse(new FileReader(data));
    String url = (String) obj.get(key);
    return url;
  }

  public String getMongoUrl() throws IOException, ParseException {
    var data = "/config/urlAddress.json";
    JSONParser parser = new JSONParser();
    JSONObject obj = (JSONObject)parser.parse(new FileReader(data));
    String url = (String) obj.get("mongodb");
    return url;
  }

  public String getFileRepositoryPath() throws IOException {
    return getProperty("server.uploadPath");
  }
  public String getDataMappingFolderPath() throws IOException {
    return getProperty("server.mappingFolderPath");
  }

  public String getFilePath(Long documentId) throws IOException, ParseException {
    RequestPatterns requestPatterns = new RequestPatterns();
    StringBuilder baseUrl = (new StringBuilder(this.getUrl("server.kafkaFilePath", "services") + "documents/filter2")).append("?").append("id=").append(documentId);
    requestPatterns.setEndPoint(baseUrl.toString());
    requestPatterns.setMethod("get");
    requestPatterns.setAuthToken("4ZCnxvlXGmcUxFlU2+f0R6hkraGOs4dV3Q6iM0WD5D7fU01ij5RZujgsYYVkvSQWA6i73dowejtU66DS/sGCPbBD905KbCYBdm8lx0+YYsakIqtL71Nk+eSg0jba4UvizAnT6a1OVtRpawMWks09viOnh9QSunuj+LhHv1J9VW40syLGbvcBsxbJQ0qq1fRd53oK7v+FDuSycgivogynyy4i5YzOXjfQEsL7wCkGY/QNkS/nHAWeYrhItnng31tl");
    Object response = MicroService.invoke(requestPatterns);
    JsonNode documentDTO = (new ObjectMapper()).readTree((String)response);
    String path = this.getUrl("server.kafkaFilePath", "repository") + documentId + documentDTO.get("data").get(0).get("fileName").asText().substring(documentDTO.get("data").get(0).get("fileName").asText().lastIndexOf("."));
    return path;
  }
}
