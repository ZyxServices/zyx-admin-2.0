package com.zyx.service.city.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.CityMapper;
import com.zyx.model.City;
import com.zyx.parm.city.QueryCityParam;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.city.CityService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          城市管理的业务层实现类
 */
@Service
public class CityServiceImpl  extends BaseServiceImpl<City> implements CityService{

    @Resource
    private CityMapper cityMapper;

    public CityServiceImpl() {
        super(City.class);
    }

    /**
     * 添加城市
     * @param city 城市对象
     * @return
     */
    @Override
    public Map<String, Object> add(City city) {
        if(city.getCityName()!=null && city.getAppType()!=null){
            city.setCreateTime(new Date().getTime());
            city.setState(0);
            int i = cityMapper.insert(city);
            if(i>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"添加成功","");
            }else {
                return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED,"添加失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
        }

    }

    /**
     * 删除城市
     * @param id 城市id
     * @return
     */
    @Override
    public Map<String, Object> delCity(Integer id) {

        if(id!=null && id>0){
            int i = cityMapper.deleteByPrimaryKey(id);
            if(i>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"删除成功",null);
            }else{
                return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"教程标签删除失败");
            }
        }else{
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失或者错误");
        }
    }


    /**
     * 查询所有城市
     * @return
     */
    @Override
    public Map<String, Object> queryCity(City city) {
        List<City> cityList = cityMapper.select(city);
        if(cityList!=null && cityList.size()>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",cityList);
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }
    }

    /**
     * 查询所有状态为启动的城市
     * @return
     */
    @Override
    public Map<String, Object> queryByState( City city) {
        List<City> cityList = cityMapper.queryByState(city);
        if(cityList!=null && cityList.size()>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",cityList);
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }
    }

    /**
     * 设置启动或者禁用
     * @param city
     * @return
     */
    @Override
    public Map<String, Object> updateCity(City city) {
        if(cityMapper.selectByPrimaryKey(city.getId())!=null){
            int i = cityMapper.updateState(city);
            if(i>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"设置成功","");
            }else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED,"设置失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"该id不存在");
        }
    }
}
