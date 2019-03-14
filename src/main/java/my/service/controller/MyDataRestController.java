package my.service.controller;

import my.service.model.MyData;
import my.service.service.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyDataRestController {

    @Autowired
    private MyDataService service;

    @RequestMapping("/rest")
    public List<MyData> restAll() {
        return service.getAll();
    }

    @RequestMapping("/rest/{num}")
    public MyData restBy(@PathVariable int num) {
        return service.get(num);
    }
}
