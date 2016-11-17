package com.zyx.controller.course;

import com.zyx.constants.Constants;
import com.zyx.model.CourseLabel;
import com.zyx.model.SysUser;
import com.zyx.service.course.CourseLabelService;
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
 * Created by zjx on 2016/11/10.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          教程标签控制层
 */
@Controller
@RequestMapping("/v1/CourseLabel")
public class CourseLabelController {
    @Resource
    private CourseLabelService courseLabelService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ApiOperation(value="添加标签",notes="添加标签")
    public ModelAndView add(HttpServletRequest request,@ApiParam(name = "labelName", required = true, value = "标签名字")@RequestParam(name="labelName",required = true)String labelName){
        AbstractView jsonView = new MappingJackson2JsonView();
        CourseLabel courseLabel = new CourseLabel();
        courseLabel.setLabelName(labelName);

        SysUser sysUser =(SysUser) request.getSession().getAttribute(Constants.CURRENT_USER);
        courseLabel.setUserId(Integer.valueOf(sysUser.getUserId()));
        Map<String,Object> map = courseLabelService.insertCourseLabel(courseLabel);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/delCourseLabel",method = RequestMethod.DELETE)
    @ApiOperation(value="删除标签",notes="删除标签")
    public ModelAndView delCourseLabel(@ApiParam(name = "id", required = true, value = "标签id")@RequestParam(name = "id", required = true) Integer id){
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String,Object> map = courseLabelService.delCourseLabel(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/queryAll",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有教程标签",notes="查询所有教程标签")
    public ModelAndView queryAll(){
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String,Object> map = courseLabelService.queryCourseLabel();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value="/updateState",method = RequestMethod.POST)
    @ApiOperation(value="设置标签禁用或者启用",notes="设置标签禁用或者启用")
    public ModelAndView updateState( @ApiParam(name = "id", required = true, value = "标签id")
                                     @RequestParam(name = "id", required = true) Integer id,
                                     @ApiParam(name = "state", required = true, value = "标签状态：0启用、1禁用")
                                     @RequestParam(name = "state", required = true) Integer state){
        AbstractView jsonView = new MappingJackson2JsonView();

        CourseLabel courseLabel = new CourseLabel();
        courseLabel.setId(id);
        courseLabel.setState(state);
        Map<String,Object> map = courseLabelService.updateState(courseLabel);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value="/queryByState",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有启用状态的标签，用于发布教程时的标签下拉框",notes="查询所有启用状态的标签，用于发布帖子时的标签下拉框")
    public ModelAndView queryByState(){
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String,Object> map = courseLabelService.queryByState();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
