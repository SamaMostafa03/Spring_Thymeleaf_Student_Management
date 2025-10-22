package com.sama.SpringBootWeb1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalcController {

    @RequestMapping("/calc")
    public String calculator() {
        return "calculator";
    }

    @RequestMapping("/addition")
    public ModelAndView add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, ModelAndView mv) {
        int output = num1 + num2;
        mv.addObject("output",output);
        mv.setViewName("result");
        return mv;
    }
}
