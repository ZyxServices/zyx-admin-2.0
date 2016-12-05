package com.zyx.controller.city;

import com.zyx.model.City;
import com.zyx.parm.city.QueryCityParam;
import com.zyx.service.city.CityService;
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
import java.util.Map;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Controller
@RequestMapping("/v2/city")
public class CityController {

    @Resource
    private CityService cityService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ApiOperation(value="添加城市",notes="添加城市")
    public ModelAndView add(@ApiParam(name = "cityName", required = true, value = "城市名")
                            @RequestParam(name="cityName",required = true)String cityName,
                            @ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType){
        AbstractView jsonView = new MappingJackson2JsonView();
        City city = new City();
        city.setCityName(cityName);
        city.setAppType(appType);

        Map<String,Object> map = cityService.add(city);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/delCity",method = RequestMethod.DELETE)
    @ApiOperation(value="删除城市",notes = "删除城市")
    public ModelAndView delCity(@ApiParam(name = "id", required = true, value = "城市id")@RequestParam(name = "id", required = true) Integer id){
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Object> map = cityService.delCity(id);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/queryAll",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有城市",notes="查询所有城市")
    public ModelAndView queryAll(@ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType){
        AbstractView jsonView = new MappingJackson2JsonView();

//        QueryCityParam param = new QueryCityParam();
//        param.setAppType(appType);
        City city = new City();
        city.setAppType(appType);
        Map<String,Object> map = cityService.queryCity(city);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/updateState",method = RequestMethod.POST)
    @ApiOperation(value="设置城市禁用或者启用",notes="设置城市禁用或者启用")
    public ModelAndView updateState( @ApiParam(name = "id", required = true, value = "城市id")
                                     @RequestParam(name = "id", required = true) Integer id,
                                     @ApiParam(name = "state", required = true, value = "城市状态：0启用、1禁用")
                                     @RequestParam(name = "state", required = true) Integer state){
        AbstractView jsonView = new MappingJackson2JsonView();

        City city = new City();
       city.setId(id);
        city.setState(state);
        Map<String,Object> map = cityService.updateCity(city);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/queryByState",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有启用状态的城市",notes="查询所有启用状态的城市")
    public ModelAndView queryByState(@ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType){
        AbstractView jsonView = new MappingJackson2JsonView();

//        QueryCityParam param = new QueryCityParam();
//        param.setAppType(appType);

        City city = new City();
        city.setAppType(appType);
        Map<String,Object> map = cityService.queryByState(city);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }




}
