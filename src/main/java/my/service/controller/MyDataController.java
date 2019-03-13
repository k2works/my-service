package my.service.controller;

import my.service.model.MyData;
import my.service.repository.MyDataRepository;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
        d1.setMemo("090999999");
        repository.saveAndFlush(d1);
        // 2つめのダミーデータ作成
        MyData d2 = new MyData();
        d2.setName("hanako");
        d2.setAge(15);
        d2.setMail("hanako@flower");
        d2.setMemo("080888888");
        repository.saveAndFlush(d2);
        // 3つめのダミーデータ作成
        MyData d3 = new MyData();
        d3.setName("sachiko");
        d3.setAge(37);
        d3.setMail("sachico@happy");
        d3.setMemo("070777777");
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
            @ModelAttribute("formModel")
            @Validated MyData myData,
            BindingResult result,
            ModelAndView mav) {
        ModelAndView res = null;
        if (!result.hasErrors()) {
            repository.saveAndFlush(myData);
            res = new ModelAndView("redirect:/");
        } else {
            mav.setViewName("index");
            mav.addObject("msg","sorry, error is occured...");
            Iterable<MyData> list = repository.findAll();
            mav.addObject("datalist", list);
            res = mav;
        }
        return res;
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, ModelAndView mav) {
        mav.setViewName("delete");
        mav.addObject("title", "delete mydata.");
        Optional<MyData> data = repository.findById((long)id);
        mav.addObject("formModel",data.get());
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }
}
