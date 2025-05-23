package com.vi.auth;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthTokenFilter extends OncePerRequestFilter {
	
	@Value("${auth.token.validation.url}")
	private String tokenValidationBaseUrl;

    private final RestTemplate restTemplate;

    public CustomAuthTokenFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
        

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
    	
    	  String path = request.getRequestURI();

          if ("/User/updatePassword".equalsIgnoreCase(path)) {
              filterChain.doFilter(request, response);
              return;
          }

        String token = request.getHeader("X-AUTH-TOKEN");

        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Token missing");
            return;
        }

        boolean isValid = validateWithPhoenix(token);

        if (isValid) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid or expired token");
        }
    }

    private boolean validateWithPhoenix(String token) {
    	
    	
//        String validateUrl = tokenValidationBaseUrl + "?token=" + token;
        String phoenixUrl = "http://192.168.1.179:8086/authentication/validateToken";

//        String phoenixUrl = tokenValidationBaseUrl;

        try {
            String urlWithParams = phoenixUrl + "?token=" + token;
//		  System.out.println(token);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(urlWithParams, requestEntity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> body = response.getBody();
                if (body != null && body.containsKey("validateToken")) {
                    Object result = body.get("validateToken");
                    return result instanceof Boolean && (Boolean) result;
                }
            }
        } catch (Exception e) {
            System.err.println("Token validation failed: " + e.getMessage());
        }

        return false;
    }
}
