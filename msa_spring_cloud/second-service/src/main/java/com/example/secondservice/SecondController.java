package com.example.secondservice;

import com.netflix.discovery.converters.Auto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/second-service")
public class SecondController {
    Environment env;

    @Autowired
    public SecondController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome second service";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header){
        log.info(header);
        return "Hello World in Second Service.";
    }


    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}", request.getServerPort());

        return String.format("Hi, there. This is a message from Second Service on PORT %s",
                env.getProperty("local.server.port"));
    }
}
