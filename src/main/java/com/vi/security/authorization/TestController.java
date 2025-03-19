package com.vi.security.authorization;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/security")
public class TestController {

    @SneakyThrows
    @GetMapping("/test")
    public ResponseEntity<?> testSecurity() {
        throw new AuthenticationException("Not Authorized");
        //return ResponseEntity.ok().body("All are good");
    }
}
