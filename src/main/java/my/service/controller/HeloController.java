package my.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeloController {

    @RequestMapping(value = "/helo", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "お名前を書いて送信しください");
        return mav;
    }

    @RequestMapping(value = "/helo", method = RequestMethod.POST)
    public ModelAndView send(@RequestParam("text1")String str, ModelAndView mav) {
        mav.addObject("msg", "こんにちは、" + str + "さん!");
        mav.addObject("value", str);
        mav.setViewName("index");
        return mav;
    }
}
