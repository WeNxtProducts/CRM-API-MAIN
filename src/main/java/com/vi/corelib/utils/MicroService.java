package com.vi.corelib.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class MicroService {


  @Autowired
  public static Environment env;

  public static String getServiceUrl(String service, String path, Boolean whatsapp) {
    if (whatsapp) {
      return service;
    }
    return env.getProperty(service) + path;
  }

  public Object getData(String service, String path, String token, JsonNode data) {
    WebClient webClient = getWebClient(service, path, token, false);
    Object response = webClient.get()
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }


  public static String postData(String service, String path, String token, JsonNode data, Boolean whatsapp) {

    WebClient webClient = getWebClient2(service, path, token, whatsapp);
    //  BodyInserter body = BodyInserters.fromPublisher(Mono.just(data),JsonNode.class);
    System.out.println(service);
    System.out.println(data);
    System.out.println(token);
    System.out.println("get Details");
    String response = webClient.post()
        .body(Mono.just(data), JsonNode.class)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }

  public String putData(String service, String path, String token, JsonNode data) {
    WebClient webClient = getWebClient(service, path, token, false);

    String response = webClient.put()
        .body(Mono.just(data), JsonNode.class)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }

  public String deleteData(String service, String path, String token, JsonNode data) {
    WebClient webClient = getWebClient(service, path, token, false);
    String response = webClient.delete()
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }

  static WebClient getWebClient(String service, String path, String token, Boolean whatsapp) {
    WebClient webClient = WebClient.create();
    if (token != null) {
      return getWebClientWithToken(service, path, token, false);
    } else {
      return getWebClientWithoutToken(service, path, false);
    }
  }

  static WebClient getWebClient2(String service, String path, String token, Boolean whatsapp) {
    if (whatsapp && token != null) {
      return getWebClientWithToken(service, path, token, true);
    } else {
      return getWebClientWithoutToken(service, path, whatsapp);
    }
  }

  static WebClient getWebClientWithToken(String service, String path, String token, Boolean whatsapp) {
    return WebClient.builder()
        .baseUrl(getServiceUrl(service, path, whatsapp))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("Authorization", token)
        .build();
  }

  static WebClient getWebClientWithoutToken(String service, String path, Boolean whatsapp) {
    return WebClient.builder()
        .baseUrl(getServiceUrl(service, path, whatsapp))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  public static String get3plService(JSONObject options, JSONObject data) {
    WebClient webClient = WebClient.builder()
        .baseUrl(options.get("baseUrl").toString())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, options.get("responseType").toString())
        .build();
    String response = webClient.get()
        //.body(Mono.just(data),JsonNode .class)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }

    /*public JsonNode get() {
        if(options.token) {
        }
        JsonNode response  = restTemplate.getForObject(env.getProperty(service)+url,JsonNode.class);
        if (!userInfo.has("data")){
            return userInfo;
        }
        if(userInfo.get("data").size()>0){
            JsonNode data = userInfo.get("data").get(0);
            return  data;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue("", JsonNode.class);
    }*/
}
