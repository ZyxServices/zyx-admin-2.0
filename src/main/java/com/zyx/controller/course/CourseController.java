package com.zyx.controller.course;

import com.zyx.constants.Constants;
import com.zyx.model.Course;
import com.zyx.service.course.CourseService;
import com.zyx.service.deva.DevaService;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/v1/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Autowired
    DevaService devaService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ApiOperation(value="发布教程",notes="发布教程")
    public ModelAndView add( @ApiParam(name = "userId", required = true, value = "用户id")@RequestParam(name="userId",required = true)Integer userId,
                             @ApiParam(name = "content", required = true, value = "内容")@RequestParam(name="content",required = true)String content,
                             @ApiParam(name = "title", required = true, value = "标题") @RequestParam(name = "title", required = true) String title,
                             @ApiParam(name = "courseType", required = true, value = "教程类型：图文、视频")@RequestParam(name = "courseType", required = true) String courseType,
                             @ApiParam(name = "labelId", required = true, value = "标签id")@RequestParam(name = "labelId", required = true) Integer labelId,
                             @ApiParam(name = "imgUrl", required = true, value = "图片路径")@RequestParam(name = "imgUrl", required = true) String imgUrl){
        AbstractView jsonView = new MappingJackson2JsonView();

        if (imgUrl == null || imgUrl.equals("")) {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
            return new ModelAndView(jsonView);
        }

        Course course = new Course();
        if(courseType.equals("图文")){
            course.setCourseType(0);
        }else if(courseType.equals("视频")){
            course.setCourseType(1);
        }else {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
        }

        course.setLabelId(labelId);
        course.setContent(content);
        course.setImgUrl(imgUrl);
        course.setTitle(title);
        course.setUserId(userId);

        Map<String,Object> map = courseService.insertCourse(course);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/queryCourse",method = RequestMethod.GET)
    @ApiOperation(value="根据标签和类型分页查询教程攻略",notes="根据标签和类型分页查询教程攻略")
    public ModelAndView queryCourse( @ApiParam(name = "labelId", required = false, value = "标签id")@RequestParam(name="labelId",required = false)Integer labelId,
                                     @ApiParam(name = "courseType", required = false, value = "教程类型：图文、视频")@RequestParam(name="courseType",required = false)String courseType,
                                     @ApiParam(name = "page", required = true, value = "页码")@RequestParam(name="page",required = true)Integer page,
                                     @ApiParam(name = "pageNumber", required = true, value = "当页显示数量") @RequestParam(name="pageNumber",required = true)Integer pageNumber){
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String,Object> map = courseService.queryCourse(labelId,courseType,page,pageNumber);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/queryByTitle",method = RequestMethod.GET)
    @ApiOperation(value = "根据标题查询教程",notes="根据标题查询教程")
    public ModelAndView queryByTitle( @ApiParam(name = "title", required = true, value = "标题")@RequestParam(name="title",required = true)String title){
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String,Object> map = courseService.queryByTitle(title);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/updateCourse",method = RequestMethod.POST)
    @ApiOperation(value = "编辑教程",notes="编辑教程")
    public ModelAndView updateCourse( @ApiParam(name = "id", required = true, value = "教程id")@RequestParam(name="id",required = true)Integer id,
                                      @ApiParam(name = "userId", required = true, value = "用户id")@RequestParam(name="userId",required = true)Integer userId,
                                      @ApiParam(name = "content", required = true, value = "内容")@RequestParam(name="content",required = true)String content,
                                      @ApiParam(name = "title", required = true, value = "标题")@RequestParam(name = "title", required = true) String title,
                                      @ApiParam(name = "courseType", required = true, value = "教程类型：图文、视频")@RequestParam(name = "courseType", required = true) String courseType,
                                      @ApiParam(name = "labelId", required = true, value = "标签id")@RequestParam(name = "labelId", required = true) Integer labelId,
                                      @ApiParam(name = "imgUrl", required = true, value = "图片路径")@RequestParam(name = "imgUrl", required = true) String imgUrl){
        AbstractView jsonView = new MappingJackson2JsonView();

        if (imgUrl == null || imgUrl.equals("")) {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
            return new ModelAndView(jsonView);
        }

        Course course = new Course();
        course.setId(id);
        if(courseType.equals("图文")){
            course.setCourseType(0);
        }else if(courseType.equals("视频")){
            course.setCourseType(1);
        }else {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
        }
        course.setLabelId(labelId);
        course.setContent(content);
        course.setImgUrl(imgUrl);
        course.setTitle(title);
        course.setUserId(userId);

        Map<String,Object> map = courseService.updateCourse(course);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);

    }

    @RequestMapping(value="/delCourse",method = RequestMethod.POST)
    @ApiOperation(value="删除教程",notes="删除教程")
    public ModelAndView delCourse( @ApiParam(name = "id", required = true, value = "教程id，可以是多个以，连接")
                                       @RequestParam(name = "id", required = true) String id){
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Object> map = courseService.delCourse(id,1);
        if (map.get("state").equals("200")) {
            String[] ids = id.split(",");
            for (String s : ids) {
                devaService.cascadeDelete(Constants.MODEL_COURSE, Integer.valueOf(s));
            }
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/maskCourse",method=RequestMethod.POST)
    @ApiOperation(value="屏蔽教程",notes="屏蔽教程攻略")
    public ModelAndView maskCourse( @ApiParam(name = "id", required = true, value = "教程id")@RequestParam(name = "id", required = true) Integer id) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Object> map = courseService.maskCourse(id,1);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
