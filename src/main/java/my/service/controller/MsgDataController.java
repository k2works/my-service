package my.service.controller;

import my.service.model.MsgData;
import my.service.model.MsgDataDaoImpl;
import my.service.repository.MsgDataRepository;
import my.service.utils.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MsgDataController {

    @Autowired
    MsgDataRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    MsgDataDaoImpl dao;

    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public ModelAndView msg(ModelAndView mav) {
        mav.setViewName("showMsgData");
        mav.addObject("title", "Sample");
        mav.addObject("msg","MsgDataのサンプルです。");
        MsgData msgdata = new MsgData();
        mav.addObject("formModel", msgdata);
        List<MsgData> list = (List<MsgData>)dao.getAll();
        mav.addObject("datalist", list);
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public ModelAndView msgform(
            @Valid @ModelAttribute MsgData msgdata,
            Errors result,
            ModelAndView mav){
       if(result.hasErrors()) {
           mav.setViewName("showMsgData");
           mav.addObject("title", "Sample [ERROR]");
           mav.addObject("msg", "値を再チェックして下さい！");
           return mav;
       } else {
           repository.saveAndFlush(msgdata);
           return new ModelAndView("redirect:/msg");
       }
    }

    @PostConstruct
    public void init(){
        System.out.println("ok");
        dao = new MsgDataDaoImpl(entityManager);
    }

}
