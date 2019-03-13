package my.service.controller;

import my.service.model.MyData;
import my.service.repository.MyDataRepository;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyDataController {

    @Autowired
    MyDataRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @ModelAttribute("formModel") MyData myData,
            ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg","this is sample content.");
        Iterable<MyData> list = repository.findAll();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("formModel") MyData myData,
            ModelAndView mav) {
        repository.saveAndFlush(myData);
        return new ModelAndView("redirect:/");
    }
}
