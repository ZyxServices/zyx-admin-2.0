package com.zyx.controller.choiceAPPType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjx on 2016/12/5.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Controller
@RequestMapping("/v2/app")
public class ChoiceController {

    @RequestMapping(value = "/choiceAppType", method = RequestMethod.POST)
    @ApiOperation(value="修改app类型：1趣攀岩",notes = "修改app类型：1趣攀岩")
    public void choiceAppType(HttpServletRequest request,
            @ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType){

        request.getSession().setAttribute("appType",appType);

    }

    @RequestMapping(value = "/queryAppType", method = RequestMethod.POST)
    @ApiOperation(value="查询app类型",notes = "查询app类型")
    public ModelAndView queryAppType(HttpServletRequest request){

        Integer appType = (Integer) request.getSession().getAttribute("appType");

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("appType",appType);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);

    }
}
