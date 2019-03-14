package my.service.controller;

import my.service.model.MyDataMongo;
import my.service.repository.MyDataMongoRepository;
import my.service.utils.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        EnvUtil.setEnv(mav);
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

    @RequestMapping(value = "/mongo/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav) {
        mav.setViewName("mongo_find");
        mav.addObject("title","Find Page");
        mav.addObject("msg","MyDataMongoのサンプルです。");
        mav.addObject("value","");
        List<MyDataMongo> list = repository.findAll();
        mav.addObject("datalist",list);
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping(value = "/mongo/find", method = RequestMethod.POST)
    public ModelAndView search(
            @RequestParam("find") String param,
            ModelAndView mav) {
        mav.setViewName("mongo_find");
        if (param == ""){
            mav = new ModelAndView("redirect:/mongo/find");
        } else {
            mav.addObject("title", "Find result");
            mav.addObject("msg","「" + param + "」の検索結果");
            mav.addObject("value",param);
            List<MyDataMongo> list = repository.findByName(param);
            mav.addObject("datalist", list);
            EnvUtil.setEnv(mav);
        }
        return mav;
    }
}
