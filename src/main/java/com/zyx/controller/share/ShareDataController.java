package com.zyx.controller.share;

import com.zyx.service.AppUserService;
import com.zyx.service.activity.ActivityService;
import com.zyx.service.community.ConcernService;
import com.zyx.service.course.CourseService;
import com.zyx.service.sportinfo.SportInfoService;
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

import java.util.Map;

/*
 * Created by HL on 2016/11/23.
 */
@Controller
@RequestMapping("/share")
public class ShareDataController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private VenueService venueService;
    @Autowired
    private SportInfoService sportInfoService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "分享查看页面数据获取",notes = "分享查看页面数据获取")
    @RequestMapping(name = "/getData",method = RequestMethod.POST)
    public ModelAndView getData(@ApiParam(name = "id",required = true,value = "主键id(对应数据为用户id、用户id、活动id、场馆id、教程id)")@RequestParam(name = "id",required = true)Integer id,
                                @ApiParam(name = "type",required = true,value = "1历史记录、2排行榜、3求约、4场馆、0教程")@RequestParam(name = "type",required = true)Integer type,
                                @ApiParam(name = "userId",required = false,value = "用户id，此为场馆必填参数，其他的不填")@RequestParam(name = "userId",required = false)Integer userId){
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map=null;
        switch (type){
            case 1:
                //历史记录
                map=sportInfoService.getSportRecordData(id);
                break;
            case 2:
                //排行榜
                map=appUserService.getRank(id);
                break;
            case 3:
                //求约
                map=activityService.getActivityDataById(type,id);
                break;
            case 4:
                //场馆
                map=venueService.getVenueDataById(type,id,userId);
                break;
            case 0:
                //教程
                map=courseService.getCourseDataById(type,id);
                break;
        }

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
