package com.vi.security.authorization;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//import javax.security.sasl.AuthenticationException;

//import jakarta.persistence.EntityNotFoundException;
//import javax.security.sasl.AuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.stream.Stream;
import org.json.JSONException;
import org.json.JSONObject;
//import org.springframework.web.servlet.HandlerExceptionResolver;


@Configuration
@EnableAutoConfiguration
@Component
@Slf4j
public class AuthFilter extends OncePerRequestFilter implements EnvironmentAware {

    @Autowired
    private Environment env;

    @Autowired
    private ResponseWriter responseWriter;

    protected Boolean isPublicPath(String path) {
        System.out.println(env.getProperty("server.publicPath"));
        return Stream.of(env.getProperty("server.publicPath",String[].class)).anyMatch(path::equals);
    }
    public String decodeToken(String authorization) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        SecureKeyGen secureKeyGen = new SecureKeyGen();
        String token2 = secureKeyGen.decrypt(authorization);
        System.out.println(token2);
        return token2;
    }
    private String sendInvalidToken(Long requestId) {
        return responseWriter.getResponse(403,"{\"message\":\"Missing or Invalid Token\"}",requestId);
    }
    private String sendExpiredToken(Long requestId) {
        return responseWriter.getResponse(400,"{\"message\":\"Token Expired\"}",requestId);
    }
    private String sendInternalError(Long requestId) {
        return responseWriter.getResponse(400,"{\"message\":\"Internal Error\"}",requestId);
    }
    private Boolean isTokenExpired(String token) throws JSONException {
        JSONObject decodedToken = new JSONObject(token);
        Date t2 = new Date(decodedToken.getLong("expiryDate"));
        Date t3 = new Date();

        if (t3.compareTo(t2) <= 0) {
            return false;
        }
        return true;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (!isPublicPath(request.getServletPath())) {
            String token2 = request.getHeader("authorization");
            if (token2 == null) {
                response.getWriter().write(sendInvalidToken(2L));
                return;
            }
            // if (isTokenExpired(token2)) {
            //     response.getWriter().write(sendExpiredToken(2L));
            //      return;
            // }
        }
        try {
            CustomResponseWrapper capturingResponseWrapper = new CustomResponseWrapper(
                    (HttpServletResponse) response);
            filterChain.doFilter(request, capturingResponseWrapper);
            System.out.println(response.getContentType());
            if (response.getContentType() != null) {
                String content = capturingResponseWrapper.getCaptureAsString();
                System.out.println(content);
                response.getWriter().write(responseWriter.getResponse(response.getStatus(),content,1L));
            }
        } catch (IOException e) {
            response.getWriter().write(sendInternalError(2L));
            e.printStackTrace();
        }
    }
}