package my.service.controller;

import my.service.model.MyData;
import my.service.repository.MyDataRepository;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Controller
public class MyDataController {

    @Autowired
    MyDataRepository repository;

    @PostConstruct
    public void init() {
        // 1つめのダミーデータ作成
        MyData d1 = new MyData();
        d1.setName("tuyano");
        d1.setAge(123);
        d1.setMail("syoda@tuyano.com");
        d1.setMemo("this is my data!");
        repository.saveAndFlush(d1);
        // 2つめのダミーデータ作成
        MyData d2 = new MyData();
        d2.setName("hanako");
        d2.setAge(15);
        d2.setMail("hanako@flower");
        d2.setMemo("my girl friend.");
        repository.saveAndFlush(d2);
        // 3つめのダミーデータ作成
        MyData d3 = new MyData();
        d3.setName("sachiko");
        d3.setAge(37);
        d3.setMail("sachico@happy");
        d3.setMemo("my work friend...");
        repository.saveAndFlush(d3);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @ModelAttribute("formModel") MyData myData,
            ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg","this is sample content.");
        Iterable<MyData> list = repository.findAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("formModel") MyData myData,
            ModelAndView mav) {
        repository.saveAndFlush(myData);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData myData,
                             @PathVariable int id,
                             ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title","edit mydata.");
        Optional<MyData> data = repository.findById((long)id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView update(@ModelAttribute MyData myData,ModelAndView mav) {
        repository.saveAndFlush(myData);
        return new ModelAndView("redirect:/");
    }
}
