package com.zxl.test.myproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AppController {

  private static Logger logger = LoggerFactory.getLogger(AppController.class);
  
  /**
   * 测试
   * @param name
   * @return
   */
  @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
  public String hello(@RequestParam String name) {
    logger.debug(String .format("/hello 's param name: >>>>> %s", name));
    return String.format("hello %s", name);
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }

  @RequestMapping(value = "/404", method = RequestMethod.GET)
  public String notFound() {
    return "404";
  }

  @RequestMapping(value = "/index")
  public ModelAndView index(Principal user) {
    ModelAndView model = new ModelAndView("app/index");
    model.addObject("userName", user.getName());
    return model;
  }
}
