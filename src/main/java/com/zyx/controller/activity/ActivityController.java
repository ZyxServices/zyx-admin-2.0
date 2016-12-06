package com.zyx.controller.activity;

import com.zyx.constants.Constants;
import com.zyx.model.Activity;
import com.zyx.parm.activity.QueryActivityParm;
import com.zyx.service.activity.ActivityService;
import com.zyx.service.deva.DevaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

import static com.zyx.utils.GetTimeUtil.getDateTime;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.title;

/**
 * Created by SubDong on 16-7-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title ActivityController
 * @package com.zyx.controller.activity
 * @update 16-7-12 下午2:27
 */
@Controller
@RequestMapping("/v2/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;
    @Autowired
    DevaService devaService;

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    @ApiOperation(value = "活动发布", notes = "活动发布")
    public ModelAndView release(@ApiParam(name = "userId",required = true,value = "用户id") @RequestParam(name = "userId", required = true) Integer userId,
                                HttpServletRequest request,
                                //@ApiParam(name = "appType",required = true,value = "app类型 1为趣攀岩") @RequestParam(name = "appType", required = true) Integer appType,
                                @ApiParam(name = "title",required = true,value = "活动名称")@RequestParam(name = "title", required = true) String title,
                                @ApiParam(name = "descContent",required = true,value = "活动简介")@RequestParam(name = "descContent", required = true) String descContent,
                                @ApiParam(name = "imageUrls",required = false,value = "活动图片")@RequestParam(name = "imageUrls", required = false) String imageUrls,
                                @ApiParam(name = "activityType",required = true,value = "活动类型 1-求约 2-求带")@RequestParam(name = "activityType", required = true) Integer activityType,
                                @ApiParam(name = "activityModule",required = false,value = "活动模块 1-攀岩 2-跑步")@RequestParam(name = "activityModule", required = false) Integer activityModule,
                                @ApiParam(name = "startTime",required = false,value = "活动开始时间")@RequestParam(name = "startTime", required = true) String startTime,//转时间戳
                                @ApiParam(name = "endTime",required = false,value = "活动结束时间")@RequestParam(name = "endTime", required = true) String endTime,//转时间戳
                                @ApiParam(name = "lastTime",required = false,value = "活动报名截止时间")@RequestParam(name = "lastTime", required = true) String lastTime,//转时间戳
                                @ApiParam(name = "address",required = false,value = "活动集合地点")@RequestParam(name = "address", required = false) String address,
                                @ApiParam(name = "city",required = false,value = "城市")@RequestParam(name = "city", required = false) String city,
                                @ApiParam(name = "maxPeople",required = false,value = "活动人数上限")@RequestParam(name = "maxPeople", required = false) Integer maxPeople,
                                @ApiParam(name = "price",required = false,value = "活动金额")@RequestParam(name = "price", required = false) Double price,
                                @ApiParam(name = "targetUrl",required = false,value = "活动跳转地址")@RequestParam(name = "targetUrl", required = false) String targetUrl,
                                @ApiParam(name = "latitude",required = true,value = "活动地址纬度")@RequestParam(name = "latitude", required = true) Double latitude,
                                @ApiParam(name = "longitude",required = true,value = "活动地址经度")@RequestParam(name = "longitude", required = true) Double longitude,
                                @ApiParam(name = "type",required = false,value = "0官方 1用户")@RequestParam(name = "type", required = true) Integer type,
                                @ApiParam(name = "paymentType",required = false,value = "付费类型 0-奖励 1-免费 2-AA")@RequestParam(name = "paymentType", required = false) Integer paymentType) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Activity activity = new Activity();
        activity.setUserId(userId);
        Integer appType=(Integer) request.getSession().getAttribute("appType");
        activity.setAppType(appType);
        activity.setTitle(title);
        activity.setLatitude(latitude);
        activity.setLongitude(longitude);
        activity.setDescContent(descContent);
        activity.setImgUrls(imageUrls);
        activity.setActivityType(activityType);
        activity.setActivityModule(activityModule);
        activity.setTargetUrl(targetUrl);
        activity.setStartTime(getDateTime(startTime));
        activity.setEndTime(getDateTime(endTime));
        activity.setLastTime(getDateTime(lastTime));

        activity.setAddress(address);
        activity.setCity(city);
        activity.setMaxPeople(maxPeople != null ? maxPeople : 9999);

        activity.setPrice(price != null ? price : 0);
        activity.setType(type);
        activity.setPaymentType(paymentType);
        Map<String, Object> map = activityService.insertActivity(activity);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "活动修改", notes = "活动修改")
    public ModelAndView update(@ApiParam(name = "id",required = true,value = "主键id")@RequestParam(name = "id", required = true) Integer id,
                               @ApiParam(name = "title",required = true,value = "活动名称")@RequestParam(name = "title", required = true) String title,
                               @ApiParam(name = "descContent",required = true,value = "活动简介")@RequestParam(name = "descContent", required = true) String descContent,
                               @ApiParam(name = "activityType",required = true,value = "活动类型 1-求约 2-求带")@RequestParam(name = "activityType", required = true) Integer activityType,
                               @ApiParam(name = "price",required = false,value = "活动金额")@RequestParam(name = "price", required = false) Double price,
                               @ApiParam(name = "latitude",required = false,value = "活动地址纬度")@RequestParam(name = "latitude", required = false) Double latitude,
                               @ApiParam(name = "longitude",required = false,value = "活动地址经度")@RequestParam(name = "longitude", required = false) Double longitude,
                               @ApiParam(name = "address",required = false,value = "活动集合地点")@RequestParam(name = "address", required = false) String address,
                               @ApiParam(name = "paymentType",required = false,value = "付费类型 0-奖励 1-免费 2-AA")@RequestParam(name = "paymentType", required = false) Integer paymentType) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Activity activity = new Activity();
        activity.setId(id);
        activity.setTitle(title);
        activity.setLongitude(longitude);
        activity.setLatitude(latitude);
        activity.setAddress(address);
        activity.setDescContent(descContent);
        activity.setActivityType(activityType);
        activity.setPrice(price != null ? price : 0);
        activity.setPaymentType(paymentType);
        Map<String, Object> map = activityService.updateActivity(activity);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryActivity", method = RequestMethod.GET)
    @ApiOperation(value = "活动查询", notes = "活动查询")
    public ModelAndView queryActivity(@ApiParam(name = "type",required = false,value = "活动分类 0-官方 1用户")@RequestParam(name = "type", required = false) Integer type,
                                      @ApiParam(name = "activityType",required = false,value = "活动类型 1-求约 2-求带")@RequestParam(name = "activityType", required = false) Integer activityType,
                                      @ApiParam(name = "paymentType",required = false,value = "付费类型 0-奖励 1-免费 2-AA")@RequestParam(name = "paymentType", required = false) Integer paymentType,
                                      HttpServletRequest request,
                                      //@ApiParam(name = "appType",required = true,value = "app类型 1为趣攀岩")@RequestParam(name = "appType", required = true) Integer appType,
                                      @ApiParam(name = "title",required = false,value = "搜索标题")@RequestParam(name = "title", required = false) String title,
                                      @ApiParam(name = "page",required = true,value = "页码 从0开始")@RequestParam(name = "page", required = true) Integer page,
                                      @ApiParam(name = "pageNumber",required = true,value = "每页显示数量")@RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                      @ApiParam(name = "status",required = false,value = "活动状态 0-正在报名 1-结束")@RequestParam(name = "status", required = false) Integer status) {

        AbstractView jsonView = new MappingJackson2JsonView();
        QueryActivityParm queryActivityParm = new QueryActivityParm();
        Integer appType=(Integer) request.getSession().getAttribute("appType");
        queryActivityParm.setAppType(appType);
        queryActivityParm.setPageSize(pageNumber);
        queryActivityParm.setPageNumber(page);
        queryActivityParm.setType(type);
        queryActivityParm.setTitle(title);
        queryActivityParm.setActivityType(activityType);
        queryActivityParm.setPaymentType(paymentType);
        queryActivityParm.setStatus(status);
        queryActivityParm.setCurrentTime(new Date().getTime());

        Map<String, Object> map = activityService.queryActivity(queryActivityParm);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryActivityById", method = RequestMethod.POST)
    @ApiOperation(value = "通过id查询活动", notes = "通过id查询活动")
    public ModelAndView queryActivityById(@ApiParam(name = "activityId",required = true,value = "活动主键id")@RequestParam(name = "activityId", required = true) Integer activityId) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.queryActivityById(activityId);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/maskActivity", method = RequestMethod.POST)
    @ApiOperation(value = "活动屏蔽", notes = "活动屏蔽")
    public ModelAndView maskActivity(@ApiParam(name = "id",required =true,value = "活动主键id")@RequestParam(name = "id", required = true) Integer id,
                                     @ApiParam(name = "maskType",required = true,value = "0-撤销 1-屏蔽")@RequestParam(name = "maskType", required = true) Integer maskType){
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.maskActivity(id, maskType);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delActivity", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动", notes = "删除活动")
    public ModelAndView delActivity(@ApiParam(name = "id",required = true,value = "活动主键id")@RequestParam(name = "id", required = true) String id) {
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.delActivity(id);
        if (map.get("state").equals("200")) {
            String[] ids = id.split(",");
            for (String s : ids) {
                devaService.cascadeDelete(Constants.MODULE_ACTIVITY, Integer.valueOf(s));
            }
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
