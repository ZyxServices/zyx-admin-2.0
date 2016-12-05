package com.zyx.service.course.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.CourseLabelMapper;
import com.zyx.mapper.CourseMapper;
import com.zyx.model.Course;
import com.zyx.model.CourseLabel;
import com.zyx.parm.course.QueryCourseParam;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.course.CourseLabelService;
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
 *          教程标签的业务层实现类
 */
@Service
public class CourseLabelServiceImpl extends BaseServiceImpl<CourseLabel> implements CourseLabelService{

    @Resource
    private CourseLabelMapper courseLabelMapper;

    @Resource
    private CourseMapper courseMapper;

    public CourseLabelServiceImpl() {
        super(CourseLabel.class);
    }

    /**
     * 添加教程标签
     * @param courseLabel 教程标签对象
     * @return
     */
    @Override
    public Map<String, Object> insertCourseLabel(CourseLabel courseLabel) {
        if(courseLabel.getLabelName()!=null && courseLabel.getUserId()!=null && courseLabel.getAppType()!=null){
            courseLabel.setState(0);
            courseLabel.setCreateTime(new Date().getTime());
            int insert = courseLabelMapper.insert(courseLabel);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "添加成功", null);
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED, "教程标签添加失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    /**
     * 删除标签
     * @param courseLabelId 标签id
     * @return
     */
    @Override
    public Map<String, Object> delCourseLabel(Integer courseLabelId) {
        if(courseLabelId!=null){
            QueryCourseParam param = new QueryCourseParam();
            param.setLabelId(courseLabelId);
            List<Course> list =  courseMapper.queryCourse(param);
            if(list.size()==0){
                int i = courseLabelMapper.deleteByPrimaryKey(courseLabelId);
                if(i>0){
                    return MapUtils.buildSuccessMap(Constants.SUCCESS,"删除成功",null);
                }else{
                    return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"教程标签删除失败");
                }
            }else {
                return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"该标签下还有教程，不能删除");
            }

        }else{
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
        }


    }

    /**
     * 查询所有标签
     * @return
     */
    @Override
    public Map<String, Object> queryCourseLabel(CourseLabel courseLabel) {
        List<CourseLabel> courseLabels = courseLabelMapper.select(courseLabel);
        if(courseLabels!=null && courseLabels.size()>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",courseLabels);
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }

    }

    /**
     * 设置标签屏蔽
     * @param courseLabel
     * @return
     */
    @Override
    public Map<String, Object> updateState(CourseLabel courseLabel) {
        if(courseLabel!=null){
            if(courseLabelMapper.selectByPrimaryKey(courseLabel.getId())==null){
                return MapUtils.buildErrorMap(Constants.NO_DATA,"该标签不存在");
            }
            int i = courseLabelMapper.updateState(courseLabel);
            if(i>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"修改成功",null);
            }else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED,"数据更新失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
        }
    }

    /**
     * 查询启用的标签集合
     * @param
     * @return
     */
    @Override
    public Map<String, Object> queryByState(CourseLabel courseLabel) {
        courseLabel.setState(0);
        List<CourseLabel> courseLabels = courseLabelMapper.queryByState(courseLabel);
        if(courseLabels!=null && courseLabels.size()>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",courseLabels);
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }
    }

}
