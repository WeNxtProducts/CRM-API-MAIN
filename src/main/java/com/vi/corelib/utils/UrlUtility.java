package com.vi.corelib.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.corelib.api.RequestPatterns;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

public abstract class UrlUtility {

    public static HashMap<String, String> getQueryParamsFromUrl(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String query = uri.getRawQuery();
        HashMap<String, String> queryParams = new HashMap<>();

        if (query == null || query.isEmpty()) {
            return queryParams;
        }

        try {
            for (String param : query.split("&")) {
                String[] parts = param.split("=", 2);
                if (parts.length == 2) {
                    queryParams.put(parts[0], decode(parts[1]));
                } else {
                    queryParams.put(parts[0], ""); // Handle case where there is no value
                }
            }
        } catch (Exception e) {
            return null;
        }
        return queryParams;
    }

    public static String queryStringify(String baseUrl, JsonNode queryString) {
        List<String> params = JsonHelper.getKeys(queryString);
        StringJoiner joiner = new StringJoiner("&");

        for (String str : params) {
            joiner.add(str + "=" + queryString.get(str).asText());
        }

        return baseUrl + "?" + joiner;
    }

    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        return (queryString == null) ? requestURL.toString() : requestURL.append('?').append(queryString).toString();
    }

    private static String decode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
    }

    public static void setPolicy(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource())) 
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
    }

    private static CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // Use * carefully in production
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public static String allowedOrigins() {
        return "*";
    }

    public static String allowedMethods() {
        return "*";
    }

    public static String allowedHeaders() {
        return "*";
    }
}
