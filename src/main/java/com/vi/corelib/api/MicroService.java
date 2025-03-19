package com.vi.corelib.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.corelib.constant.Values;
import com.vi.corelib.utils.JsonHelper;
import com.vi.corelib.utils.SecureKeyGen;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

//
public class MicroService {
  public MicroService() {
  }

  private static String getServiceUrl(String baseUrl, String path) {
    return baseUrl + path;
  }
  private static String getServiceUrlSigned(String baseUrl, String path) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, URISyntaxException, InvalidKeyException {
    return new SecureKeyGen().getSignedUrl(baseUrl + path);
  }
  private static String getAuthToken(String baseUrl, String path) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, URISyntaxException, InvalidKeyException {
    String url = baseUrl+path +"&timestamp="+new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date());

    return new SecureKeyGen().getAuthToken(url);
  }

  @SneakyThrows
  public static Object getData(String baseUrl, String path, String token) {
    WebClient webClient = getWebClient(baseUrl, path, token);
    Object response = webClient.get()
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }

  @SneakyThrows
  public static Object getData(String baseUrl, String path, String token,String requestType) {
    WebClient webClient = getWebClient(baseUrl, path, token,requestType);
    Object response = webClient.get()
            .retrieve()
            .bodyToMono(String.class)
            .block();
    return response;
  }

  @SneakyThrows
  public static Object invoke(RequestPatterns patterns) {
    String baseUrl = patterns.getEndPoint();
    String path = "";
    Object response = null;
    WebClient webClient = getWebClient(baseUrl, path, null);
    switch (patterns.getMethod()) {
      case Values.HTTP_GET:
        response = webClient.get()
            .uri(uriBuilder -> {
              getParameters(patterns.getRequestParam(), uriBuilder);
              return uriBuilder.build();
            }).header("Authorization",patterns.getAuthToken())
            .retrieve()
            .bodyToMono(String.class)
            .block();
        break;
      case Values.HTTP_POST:

        LinkedMultiValueMap mvmap = new LinkedMultiValueMap<>(getHeaders(patterns.getRequestHeader()));
        mvmap.add("Authorization",patterns.getAuthToken());
        Consumer<HttpHeaders> consumer = it -> it.addAll(mvmap);

         response = webClient.post()
                .uri(uriBuilder -> {
                  getParameters(patterns.getRequestParam(), uriBuilder);
                  return uriBuilder.build();
                })
                 .headers(consumer)
                .body(Mono.just(JsonHelper.toJson(patterns.getRequestBody())), JsonNode.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        break;
      default:
        break;
    }
    return response;
  }
  @SneakyThrows
  public static Object invoke2(RequestPatterns patterns) {
    String baseUrl = patterns.getEndPoint();
    String path = "";
    Object response = null;
    LinkedMultiValueMap mvmap2 = new LinkedMultiValueMap<>(getHeaders(patterns.getRequestHeader()));
    mvmap2.add("Authorization",patterns.getAuthToken());
    Consumer<HttpHeaders> consumer = it -> it.addAll(mvmap2);

    WebClient webClient = getWebClient(baseUrl, path, null);
    switch (patterns.getMethod()) {
      case Values.HTTP_GET:
        response = webClient.get()
                .uri(uriBuilder -> {
                  getParameters(patterns.getRequestParam(), uriBuilder);
                  return uriBuilder.build();
                }).headers(consumer)
                //.header("Authorization",patterns.getAuthToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        break;
      case Values.HTTP_POST:

        LinkedMultiValueMap mvmap = new LinkedMultiValueMap<>(getHeaders(patterns.getRequestHeader()));
        mvmap.add("Authorization",patterns.getAuthToken());
        Consumer<HttpHeaders> consumer2 = it -> it.addAll(mvmap);

        response = webClient.post()
                .uri(uriBuilder -> {
                  getParameters(patterns.getRequestParam(), uriBuilder);
                  return uriBuilder.build();
                })
                .headers(consumer2)
                .body(Mono.just(JsonHelper.toJson(patterns.getRequestBody())), JsonNode.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        break;
      default:
        break;
    }
    return response;
  }

  @SneakyThrows
  public  Response invokeWithResponseObject(RequestPatterns patterns) {
    String baseUrl = patterns.getEndPoint();
    String path = "";
   Object response3 = null;
    Response responseObject = new Response();
    WebClient webClient = getWebClient(baseUrl, path, null);
    switch (patterns.getMethod()) {
      case Values.HTTP_POST:

        LinkedMultiValueMap mvmap = new LinkedMultiValueMap<>(getHeaders(patterns.getRequestHeader()));
        mvmap.add("Authorization",patterns.getAuthToken());
        Consumer<HttpHeaders> consumer = it -> it.addAll(mvmap);

         Mono<IrnResponse> irnResponseMono = webClient.post()
                .uri(uriBuilder -> {
                  getParameters(patterns.getRequestParam(), uriBuilder);
                  return uriBuilder.build();
                })
                .headers(consumer)
                .body(Mono.just(JsonHelper.toJson(patterns.getRequestBody())), JsonNode.class)
                 .exchangeToMono(response -> {
		          if (response.statusCode().equals(HttpStatus.OK)) {
		              return response.bodyToMono(IrnResponse.class);
		          }
		          else {
		              return response.createException().flatMap(Mono::error);
		          }
		      });
                /*.retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                  return response.bodyToMono(Response.class).flatMap(error -> {
                    return Mono.error(new CustomRuntimeException(error.getMessage(), response.statusCode(), error.getErrorCode()));
                  });
                }).onStatus(HttpStatus::is2xxSuccessful, response -> {
                    return response.bodyToMono(Response.class).flatMap(error -> {
                      return Mono.error(new CustomRuntimeException(error.getMessage(), response.statusCode(), error.getErrorCode()));
                    });
                  }).onStatus(HttpStatus::is5xxServerError, response -> {
                   return response.bodyToMono(Response.class).flatMap(error -> {
                     return Mono.error(new CustomRuntimeException(error.getMessage(), response.statusCode(), error.getErrorCode()));
                   });
                 }).onStatus(HttpStatus::is1xxInformational, response -> {
                   return response.bodyToMono(Response.class).flatMap(error -> {
                     return Mono.error(new CustomRuntimeException(error.getMessage(), response.statusCode(), error.getErrorCode()));
                   });
                 })
                 .onStatus(HttpStatus::is3xxRedirection, response -> {
                   return response.bodyToMono(Response.class).flatMap(error -> {
                     return Mono.error(new CustomRuntimeException(error.getMessage(), response.statusCode(), error.getErrorCode()));
                   });
                 })
                 .onStatus(HttpStatus::isError, response -> {
                   return response.bodyToMono(Response.class).flatMap(error -> {
                     return Mono.error(new CustomRuntimeException(error.getMessage(), response.statusCode(), error.getErrorCode()));
                   });
                 })
                .bodyToMono(String.class);*/
      default:
        break;
    }
    return responseObject;
  }
  private static HttpHeaders getHeaders(List<RequestBody> requestHeaders) {
    HttpHeaders httpHeaders = new HttpHeaders();
    var params = new LinkedMultiValueMap<String, String>();
    if (requestHeaders != null) {
      for (RequestBody var : requestHeaders) {
        httpHeaders.add(var.getName(),var.getValue());
      }
    }
    return httpHeaders;
  }
  private static void getParameters(List<RequestBody> requestParam, UriBuilder uriBuilder) {
    var params = new LinkedMultiValueMap<String, String>();
    if (requestParam != null) {
      for (RequestBody var : requestParam) {
        uriBuilder.queryParam(var.getName(), var.getValue());
      }
    }
  }

  @SneakyThrows
  public static String postData(String baseUrl, String path, String token, JsonNode data) {
    WebClient webClient = getWebClient(baseUrl, path, token);
    //  BodyInserter body = BodyInserters.fromPublisher(Mono.just(data),JsonNode.class);
    String response = webClient.post()
        .body(Mono.just(data), JsonNode.class)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }

  @SneakyThrows
  public static String putData(String baseUrl, String path, String token, JsonNode data) {
    WebClient webClient = getWebClient(baseUrl, path, token);
    String response = webClient.put()
        .body(Mono.just(data), JsonNode.class)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }
  @SneakyThrows
  public static String deleteData(String baseUrl, String path, String token) {
    WebClient webClient = getWebClient(baseUrl, path, token);
    String response = webClient.delete()
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return response;
  }

  @SneakyThrows
  private static WebClient getWebClient(String baseUrl, String path, String token) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, URISyntaxException, InvalidKeyException {
    if (token != null) {
      return getWebClientWithToken(baseUrl, path, token);
    }

    return getWebClientWithoutToken(baseUrl, path);
  }

  @SneakyThrows
  private static WebClient getWebClient(String baseUrl, String path, String token,String requestType) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, URISyntaxException, InvalidKeyException {
    if (token != null) {
      return getWebClientWithToken(baseUrl, path, token);
    } else if(requestType=="internal") {
        return getWebClientWithAuthToken(baseUrl, path);
    }
    return getWebClientWithoutToken(baseUrl, path);
  }

  private static WebClient getWebClientWithToken(String baseUrl, String path, String token) {
    return WebClient.builder()
        .baseUrl(getServiceUrl(baseUrl, path))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(Values.AUTH_HEADER, token)
        .build();
  }
  private static WebClient getWebClientWithAuthToken(String baseUrl, String path) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, URISyntaxException, InvalidKeyException {

    String url = baseUrl+path +"&timestamp="+new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date());
    SecureKeyGen secureKeyGen = new SecureKeyGen();
    String authToken = secureKeyGen.getAuthToken(url);

    return WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("AuthToken", authToken)
            .build();
  }
  private static WebClient getWebClientWithoutToken(String baseUrl, String path) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, URISyntaxException, InvalidKeyException {
    return WebClient.builder()
        .baseUrl(getServiceUrl(baseUrl, path))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
