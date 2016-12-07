package com.zyx.controller.venue;

import com.zyx.model.Venue;
import com.zyx.parm.venue.VenueParam;
import com.zyx.service.venue.VenueService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;

/**
 * Created by HL on 2016/11/7.
 */
@RequestMapping("/v2/venue")
@Controller
public class VenueController {

    @Autowired
    private VenueService venueService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ApiOperation(value = "新增场馆",notes = "新增场馆")
    public ModelAndView insert( @ApiParam(name = "type",required = true, value = "场馆类型:1-室内 2-室外")
                                @RequestParam(name = "type",required =true)Integer type,
                                //@ApiParam(name = "appType",required = true, value = "app类型类型:1-趣攀岩")
                                //@RequestParam(name = "appType",required =true)Integer appType,
                                HttpServletRequest request,
                                @ApiParam(name = "name",required = true, value = "场馆名称")
                                @RequestParam(name = "name",required = true)String name,
                                @ApiParam(name = "background",required = false, value = "开发背景")
                                @RequestParam(name = "background",required = false)String background,
                                @ApiParam(name = "level",required = true, value = "难度系数")
                                @RequestParam(name = "level",required = true)Integer level,
                                @ApiParam(name = "address",required = true, value = "场馆地址")
                                @RequestParam(name = "address",required = true)String address,
                                @ApiParam(name = "longitude",required = true, value = "经度")
                                @RequestParam(name = "longitude",required = true)Double longitude,
                                @ApiParam(name = "latitude",required = true, value = "纬度")
                                @RequestParam(name = "latitude",required = true)Double latitude,
                                @ApiParam(name = "mark", value = "标签")
                                @RequestParam(name = "mark",required = false)String mark,
                                @ApiParam(name = "description", value = "场馆介绍")
                                @RequestParam(name = "description",required = false)String description,
                                @ApiParam(name = "phone",required = true, value = "联系电话")
                                @RequestParam(name = "phone",required = true)String phone,
                                @ApiParam(name = "imgUrls",required = true, value = "场馆封面")
                                @RequestParam(name = "imgUrls",required = true)String imgUrls,
                                @ApiParam(name = "city", value = "城市",required =true)
                                @RequestParam(name = "city",required = true)String city){
        AbstractView jsonView = new MappingJackson2JsonView();
        Integer appType=(Integer) request.getSession().getAttribute("appType");
        Venue venue  = new Venue();
        venue.setName(name);
        venue.setAppType(appType);
        venue.setType(type);
        venue.setDescription(description);
        venue.setLatitude(latitude);
        venue.setLongitude(longitude);
        venue.setMark(mark);
        venue.setBackground(background);
        venue.setCity(city);
        venue.setAddress(address);
        venue.setPhone(phone);
        venue.setLevel(level);
        venue.setImgUrls(imgUrls);
        Map<String, Object> map =venueService.insertVenue(venue);
        jsonView.setAttributesMap(map);
        return  new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除场馆", notes = "删除场馆")
    public ModelAndView delActivity(@ApiParam(name = "id",required = true, value = "主键id")
                                    @RequestParam(name = "id", required = true) String id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map =venueService.delVenue(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @ApiOperation(value = "修改场馆数据",notes = "修改场馆数据")
    @RequestMapping(value = "/update",method =RequestMethod.POST)
    public ModelAndView update(@ApiParam(name = "id",required = true, value = "主键id")
                               @RequestParam(name ="id",required =true)Integer id,
                               @ApiParam(name = "name",required = true, value = "场馆名称")
                               @RequestParam(name = "name",required = true)String name,
                               @ApiParam(name = "background",required = false, value = "开发背景")
                               @RequestParam(name = "background",required = false)String background,
                               @ApiParam(name = "level",required = true, value = "难度系数、正整数")
                               @RequestParam(name = "level",required = true)Integer level,
                               @ApiParam(name = "address",required = true, value = "场馆地址")
                               @RequestParam(name = "address",required = true)String address,
                               @ApiParam(name = "longitude",required = true, value = "经度")
                               @RequestParam(name = "longitude",required = true)Double longitude,
                               @ApiParam(name = "latitude",required = true, value = "纬度")
                               @RequestParam(name = "latitude",required = true)Double latitude,
                               @ApiParam(name = "mark", value = "标签")
                               @RequestParam(name = "mark",required = false)String mark,
                               @ApiParam(name = "description", value = "场馆介绍")
                               @RequestParam(name = "description",required = false)String description,
                               @ApiParam(name = "phone",required = true, value = "联系电话")
                               @RequestParam(name = "phone",required = true)String phone,
                               @ApiParam(name = "imgUrls",required = true, value = "场馆封面")
                               @RequestParam(name = "imgUrls",required = true)String imgUrls,
                               @ApiParam(name = "type",required = true, value = "场馆类型:1-室内 2-室外")
                               @RequestParam(name = "type",required =true)Integer type,
                               @ApiParam(name = "city",required = true, value = "城市")
                               @RequestParam(name = "city",required = true)String city){
        AbstractView jsonView = new MappingJackson2JsonView();
        Venue venue  = new Venue();
        venue.setId(id);
        venue.setName(name);
        venue.setType(type);
        venue.setDescription(description);
        venue.setLatitude(latitude);
        venue.setLongitude(longitude);
        venue.setMark(mark);
        venue.setCity(city);
        venue.setBackground(background);
        venue.setAddress(address);
        venue.setPhone(phone);
        venue.setLevel(level);
        venue.setImgUrls(imgUrls);
        Map<String,Object> map=venueService.updateVenue(venue);
        jsonView.setAttributesMap(map);
        return  new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryVenue",method = RequestMethod.GET)
    @ApiOperation(value ="场馆查询",notes = "场馆查询")
    public ModelAndView queryVenue(@ApiParam(name = "page",required = true, value = "页码，从0开始")
                                   @RequestParam(name = "page",required = true)Integer page,
                                   @ApiParam(name = "pageNumber",required = true, value = "每页显示数量")
                                   @RequestParam(name = "pageNumber",required = true)Integer pageNumber,
                                   //@ApiParam(name = "appType",required = true, value = "app类型")
                                   //@RequestParam(name = "appType",required = true)Integer appType,
                                   HttpServletRequest request,
                                   @ApiParam(name = "type",required =false,value = "场馆类型 1-室内 2-室外")
                                   @RequestParam(name = "type",required = false)Integer type,
                                   @ApiParam(name = "search",required = false, value = "搜索")
                                   @RequestParam(name = "search",required = false)String search
                                   ){
        AbstractView jsonView = new MappingJackson2JsonView();
        Integer appType=(Integer) request.getSession().getAttribute("appType");
        VenueParam venueParam = new VenueParam();
        venueParam.setAppType(appType);
        venueParam.setPageSize(pageNumber);
        venueParam.setPageNumber(page);
        venueParam.setSearch(search);
        venueParam.setType(type);
        venueParam.setSearch(search);
        Map<String,Object> map = venueService.queryVenue(venueParam);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
