package com.zyx.controller.course;

import com.zyx.model.CourseLabel;
import com.zyx.service.course.CourseLabelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zjx on 2016/11/10.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Controller
@RequestMapping("/v1/CourseLabel")
public class CourseLabelController {
    @Resource
    private CourseLabelService courseLabelService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ApiOperation(value="添加标签",notes="添加标签")
    public ModelAndView add(@RequestParam(name="userId",required = true)Integer userId,
                            @RequestParam(name="labelName",required = true)String labelName){
        AbstractView jsonView = new MappingJackson2JsonView();
        CourseLabel courseLabel = new CourseLabel();
        courseLabel.setLabelName(labelName);
        courseLabel.setUserId(userId);

        Map<String,Object> map = courseLabelService.insertCourseLabel(courseLabel);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/delCourseLabel",method = RequestMethod.DELETE)
    @ApiOperation(value="删除标签",notes="删除标签")
    public ModelAndView delCourseLabel(@RequestParam(name = "id", required = true) Integer id){
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

}
