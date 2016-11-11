package com.zyx.service.course.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.CourseMapper;
import com.zyx.model.Course;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.course.CourseService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zjx on 2016/11/10.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          教程攻略的业务层实现类
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService{

    @Resource
    private CourseMapper courseMapper;

    public CourseServiceImpl( ) {
        super(Course.class);
    }

    /**
     * 发布教程
     * @param course 教程攻略对象
     * @return
     */
    @Override
    public Map<String, Object> insertCourse(Course course) {
        if(course.getUserId()!=null && course.getContent()!=null && course.getCourseType()!=null
                && course.getImgUrl()!=null && course.getTitle()!=null){
            course.setDel(0);
            course.setMask(0);
            course.setCreateTime(new Date().getTime());

            int insert = courseMapper.insert(course);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "发布成功", null);
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED, "教程攻略发布失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    /**
     * 动态条件分页查询
     * @param label 标签
     * @param courseType 教程攻略类型
     * @param page  页码
     * @param pageNumber 当页显示数量
     * @return
     */
    @Override
    public Map<String, Object> queryCourse(Integer label, String courseType,int page, int pageNumber) {
        Course course = new Course();
        course.setLabelId(label);
        course.setCourseType(courseType);
        course.setPage((page-1)*pageNumber);
        course.setPageNumber(pageNumber);
        List<Course> courses = courseMapper.queryCourse(course);
        int i = courseMapper.selectCountCourse();
        if(courses !=null && courses.size() > 0){
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", courses);
            map.put("total", i);
            return map;
        } else {
            return MapUtils.buildErrorMap(Constants.NO_DATA, "查无数据");
        }
    }

    /**
     * 根据标题查询
     * @param title 标题
     * @return
     */
    @Override
    public Map<String, Object> queryByTitle(String title) {
        if(title!=null) {
            Course course = new Course();
            course.setTitle(title);
            List<Course> courses = courseMapper.queryByTitle(course);
            if (courses != null && courses.size() > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", courses);
            } else {
                return MapUtils.buildErrorMap(Constants.NO_DATA, "查无数据");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    /**
     * 编辑教程
     * @param course 教程攻略对象
     * @return
     */
    @Override
    public Map<String, Object> updateCourse(Course course) {
        if(course!=null){
            int i = courseMapper.updateCourse(course);
            if (i > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据更新失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }


    }

    /**
     * 删除教程
     * @param idStr id字符串，可以进行批量删除
     * @param delType 删除类型
     * @return
     */
    @Override
    public Map<String, Object> delCourse(String idStr, int delType) {
        if (delType > -1 && delType < 2 && idStr != null && !idStr.equals("")) {
            int a = 0;
            String[] ids = idStr.split(",");
            for (String integer : ids) {
                if (integer != null && Integer.valueOf(integer) > 0) {
                    Course course = new Course();
                    course.setId(Integer.valueOf(integer));
                    course.setDel(delType);
                    int i = courseMapper.updateCourse(course);
                    if (i > 0) {
                        a++;
                    }
                }
            }
            if (a > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据删除失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数有误");
        }
    }

    /**
     * 屏蔽教程
     * @param id 教程id
     * @param maskType 屏蔽类型
     * @return
     */
    @Override
    public Map<String, Object> maskCourse(int id, int maskType) {
        if (maskType > -1 && maskType < 2 && id > 0) {
            Course course = new Course();
            course.setId(id);
            course.setMask(maskType);
            int i = courseMapper.updateCourse(course);
            if (i > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据屏蔽失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数有误");
        }
    }
}
