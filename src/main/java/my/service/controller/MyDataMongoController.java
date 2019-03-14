package my.service.controller;

import my.service.model.MyDataMongo;
import my.service.repository.MyDataMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyDataMongoController {

    @Autowired
    MyDataMongoRepository repository;

    @RequestMapping(value = "/mongo", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("mongo");
        mav.addObject("title","Find Page");
        mav.addObject("msg", "MyDataMongoのサンプルです。");
        Iterable<MyDataMongo> list = repository.findAll();
        mav.addObject("datalist",list);
        return mav;
    }

    @RequestMapping(value = "/mongo", method = RequestMethod.POST)
    public ModelAndView form(
            @RequestParam("name") String name,
            @RequestParam("memo") String memo,
            ModelAndView mov){
        MyDataMongo mydata = new MyDataMongo(name, memo);
        repository.save(mydata);
        return new ModelAndView("redirect:/mongo");
    }
}
