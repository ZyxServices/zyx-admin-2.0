package com.zyx.controller.choiceAPPType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
}
