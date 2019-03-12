package my.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeloController {
    @RequestMapping("/")
    public String index() {
        return "Hello Spring-Boot World!!";
    }
}
