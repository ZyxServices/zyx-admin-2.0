package com.zyx.controller.opinion;

import com.zyx.constants.Constants;
import com.zyx.parm.appUser.OpinionParam;
import com.zyx.service.opinion.OpinionService;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zjx on 2016/11/16.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Controller
@RequestMapping("/v2/opinion")
public class OpinionController {


    @Resource
    private OpinionService opinionService;



    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询意见", notes = "分页查询意见")
    public ModelAndView queryUser(HttpServletRequest request,
                                  @ApiParam(name="page",required = true,value = "页码:从1开始")@RequestParam Integer page,
                                  @ApiParam(name="pageNumber",required = true,value = "每页显示数量")@RequestParam Integer pageNumber
//                                  ,@ApiParam(name="userId",required = true,value = "用户id")@RequestParam(required = true) Integer userId
//                             ,@ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType
    ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        OpinionParam param = new OpinionParam();
        param.setPageSize(pageNumber);
        if(page>0){
            param.setPageNumber((page - 1) * pageNumber);
        }else {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_ERROR,"页码不能小于1"));
            return new ModelAndView(jsonView);
        }

//        param.setUserId(userId);
        param.setAppType((Integer) request.getSession().getAttribute("appType"));

        Map<String, Object> map = opinionService.queryByUser(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ApiOperation(value = "删除意见", notes = "删除意见")
    public ModelAndView del(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = opinionService.delOpinion(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }





}
