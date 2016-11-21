package com.zyx.service.city;

import com.zyx.model.City;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          城市管理的业务层接口
 */

public interface CityService extends BaseService<City> {

    /**
     * 添加城市
     * @param city 城市对象
     * @return
     */
    Map<String,Object> add(City city);

    /**
     * 删除城市
     * @param id
     * @return
     */
    Map<String,Object> delCity(Integer id);

    /**
     * 查询所有城市
     * @return
     */
    Map<String,Object>queryCity();

    /**
     * 查询所有已经启用的城市
     * @return
     */
    Map<String,Object> queryByState();

    /**
     * 修改城市启用状态（启用/禁用）
     * @param city
     * @return
     */
    Map<String,Object> updateCity(City city);
}
