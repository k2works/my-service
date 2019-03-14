package my.service.controller;

import my.service.utils.EnvUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HeloController {

    @RequestMapping(value = "/helo", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("helo");
        mav.addObject("msg", "フォームを送信して下さい。");
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping(value = "/helo", method = RequestMethod.POST)
    public ModelAndView send(
            @RequestParam(value="check1",required = false)boolean check1,
            @RequestParam(value = "radio1", required = false)String radio1,
            @RequestParam(value = "select1", required = false)String select1,
            @RequestParam(value = "select2", required = false)String[] select2,
            ModelAndView mav) {

        String res = "";
        try {
            res = "check:" + check1 +
                    " radio:" + radio1 +
                    " select:" + select1 +
                    "\nselect2:";
        } catch (NullPointerException e) {}
        try {
            res += select2[0];
            for(int i = 1; i < select2.length; i++)
                res += ", " + select2[i];
        } catch (NullPointerException e) {
            res += "null";
        }
        mav.addObject("msg", res);
        mav.setViewName("helo");
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping("/other")
    public String other() {
        String ret;
        if (EnvUtil.getEnv().equals("production")) {
          ret = "redirect:/Prod/helo";
        } else {
          ret = "redirect:/helo";
        }
        return ret;
    }

    @RequestMapping("/home")
    public String home() {
        return "forward:/helo";
    }
}
