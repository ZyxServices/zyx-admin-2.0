package com.zyx.mapper;

import com.zyx.base.BaseMapper;
import com.zyx.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Repository("cityMapper")
public interface CityMapper extends BaseMapper<City>{

    /**
     * 设置启用或者禁用
     * @param city 城市对象
     * @return
     */
    int updateState(City city);

    /**
     * 查询所有启动状态的城市
     * @return
     */
    List<City> queryByState();
}
