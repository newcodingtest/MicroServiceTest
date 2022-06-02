package com.example.firstservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// http://localhost:8081/welcome
// http://localhost:8081/first-service/welcome
@RestController
@RequestMapping("/first-service")
@Slf4j
@RequiredArgsConstructor
public class FirstServiceController {

    private final Environment env;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request")String header){
        log.info(header);

        return "Hello World in First Service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}", request.getServerPort());
        
        // env.getProperty("local.server.port") 또는 request.getServerPort() 가능
        return String.format("Hi, there. This is a message from First Service.", env.getProperty("local.server.port"));
    }
}
