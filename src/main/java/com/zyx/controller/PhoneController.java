package com.zyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * Created by 文楷 on 2016/11/23.
 */
@Controller
@RequestMapping("/phone")
public class PhoneController {
    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public ModelAndView redirectVenues(@RequestParam int type, @RequestParam String id, @RequestParam String appType) {
        switch (type) {
            case 0:
                return new ModelAndView("/phone/share/s_course");
            case 1:
                return new ModelAndView("/phone/share/s_history");
            case 2:
                return new ModelAndView("/phone/share/s_equipment");
            case 3:
                return new ModelAndView("/phone/share/s_activity");
            case 4:
                return new ModelAndView("/phone/share/s_venues");
            case 5:
                return new ModelAndView("/phone/share/s_rankingList");
            case 6:
                return new ModelAndView("/phone/agreement/s_agreement");
            default:
                return new ModelAndView("/error/404");
        }
    }
//    return new ModelAndView("/phone/share/s_index");
    @RequestMapping(value = "agreement/permit", method = RequestMethod.GET)
    public ModelAndView redirectPermit() {return new ModelAndView("/phone/Agreement/permit");}
    @RequestMapping(value = "agreement/release", method = RequestMethod.GET)
    public ModelAndView redirectRelease() {
        return new ModelAndView("/phone/Agreement/release");
    }
    @RequestMapping(value = "rule/climbingCoins", method = RequestMethod.GET)
    public ModelAndView redirectClimbingCoins() {
        return new ModelAndView("/phone/rule/climbingCoins");
    }
    @RequestMapping(value = "/share/index", method = RequestMethod.GET)
    public ModelAndView redirectIndex() {
        return new ModelAndView("/phone/share/s_index");
    }
}

