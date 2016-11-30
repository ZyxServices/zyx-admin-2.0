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
    /*分享*/
    @RequestMapping(value = "/share/venues", method = RequestMethod.GET)
    public ModelAndView redirectVenues(@RequestParam String id ) {
        return new ModelAndView("/phone/share/s_venues");
    }

    @RequestMapping(value = "/share/article", method = RequestMethod.GET)
    public ModelAndView redirectArticle(@RequestParam String id ) {
        return new ModelAndView("/phone/share/s_article");
    }

    @RequestMapping(value = "/share/activity", method = RequestMethod.GET)
    public ModelAndView redirectActivity(@RequestParam String id) {
        return new ModelAndView("/phone/share/s_activity");
    }

    @RequestMapping(value = "/share/course", method = RequestMethod.GET)
    public ModelAndView redirectCourse(@RequestParam String id) {
        return new ModelAndView("/phone/share/s_course");
    }

    @RequestMapping(value = "/share/dynamic", method = RequestMethod.GET)
    public ModelAndView redirectDynamic(@RequestParam String id) {
        return new ModelAndView("/phone/share/s_dynamic");
    }

    @RequestMapping(value = "/share/rankingList", method = RequestMethod.GET)
    public ModelAndView redirectRankingList(@RequestParam String id) {
        return new ModelAndView("/phone/share/s_rankingList");
    }
    @RequestMapping(value = "/share/history", method = RequestMethod.GET)
    public ModelAndView redirectHistory(@RequestParam String id) {
        return new ModelAndView("/phone/share/s_history");
    }

    @RequestMapping(value = "/share/equip", method = RequestMethod.GET)
    public ModelAndView redirectEquip(@RequestParam String id) {
        return new ModelAndView("/phone/share/s_equipment");
    }
    @RequestMapping(value = "/agreement", method = RequestMethod.GET)
    public ModelAndView redirectAgreement() {
        return new ModelAndView("/phone/agreement/s_agreement");
    }
}

