package com.huantek.veinserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("protocol")
public class protocolController {


    @RequestMapping(method = RequestMethod.GET,value = "/privacy_agreement")
    public String privacy_agreement() {
        return "privacy_agreement";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/service_agreement")
    public String service_agreement() {
        return "service_agreement";
    }
}
