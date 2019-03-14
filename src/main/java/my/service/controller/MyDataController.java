package my.service.controller;

import my.service.component.MyDataBean;
import my.service.model.MyData;
import my.service.model.MyDataDaoImpl;
import my.service.repository.MyDataMongoRepository;
import my.service.repository.MyDataRepository;
import my.service.service.MyDataService;
import my.service.utils.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class MyDataController {

    @Autowired
    MyDataRepository repository;

    @Autowired
    private MyDataService service;

    @PersistenceContext
    EntityManager entityManager;

    MyDataDaoImpl dao;

    @Autowired
    MyDataBean myDataBean;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView indexById(@PathVariable long id, ModelAndView mav) {
        mav.setViewName("pickup");
        mav.addObject("title","Pickup Page");
        String table = "<table>"
                + myDataBean.getTableTagById(id)
                + "</table>";
        mav.addObject("msg","pickup Data id = " + id);
        mav.addObject("data", table);
        EnvUtil.setEnv(mav);
        return mav;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @ModelAttribute("formModel") MyData myData,
            ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg","MyDataのサンプルです。");
        List<MyData> list = service.getAll();
        mav.addObject("datalist", list);
        EnvUtil.setEnv(mav);
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
            EnvUtil.setEnv(mav);
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
        EnvUtil.setEnv(mav);
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
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav) {
        mav.setViewName("find");
        mav.addObject("title", "Find Page");
        mav.addObject("msg","MyDataのサンプルです。");
        mav.addObject("value","");
        List<MyData> list = service.getAll();
        mav.addObject("datalist", list);
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
        mav.setViewName("find");
        String param = request.getParameter("fstr");
        if(param == ""){
            mav = new ModelAndView("redirect:/find");
        } else {
            mav.addObject("title","Find result");
            mav.addObject("msg","「" + param + "」の検索結果");
            mav.addObject("value", param);
            List<MyData> list = service.find(param);
            mav.addObject("datalist", list);
        }
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("ajax");
        mav.addObject("msg","MyDataのサンプルです。");
        List<MyData> list = service.getAll();
        mav.addObject("datalist", list);
        EnvUtil.setEnv(mav);
        return mav;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav, Pageable pageable) {
        mav.setViewName("page");
        mav.addObject("title", "Find Page");
        mav.addObject("msg", "MyDataのサンプルです。");
        Page<MyData> list = repository.findAll(pageable);
        mav.addObject("datalist", list);
        EnvUtil.setEnv(mav);
        return mav;
    }

    @PostConstruct
    public void init() {
        dao = new MyDataDaoImpl(entityManager);
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
}
