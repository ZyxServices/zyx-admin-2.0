package com.zyx.controller.community;

import com.zyx.constants.Constants;
import com.zyx.model.SysUser;
import com.zyx.service.community.ConcernService;
import com.zyx.service.deva.DevaService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */

@Controller
@RequestMapping("/v2/concern")
public class ConcernController {

    @Resource
    private ConcernService concernService;
    @Autowired
    DevaService devaService;

    @RequestMapping(value = "/createConcern", method = RequestMethod.POST)
    @ApiOperation(value = "创建动态", notes = "创建动态")
    public ModelAndView createConcern( HttpServletRequest request,
            @ApiParam(name = "content", required = true, value = "动态内容")
            @RequestParam(value = "content") String content,
            @ApiParam(name = "visible", required = true, value = "可见范围：0所有人可见、1好友可见")
            @RequestParam(value = "visible") Integer visible,
            @ApiParam(name = "createId", required = true, value = "创建人id")
            @RequestParam(value = "createId") Integer createId,
            @ApiParam(name = "imgFileUrl", required = false, value = "图片路径")
            @RequestParam(value = "imgFileUrl", required = false) String imgFileUrl,
            @ApiParam(name = "videoUrl", required = false, value = "视频路径")
            @RequestParam(value = "videoUrl", required = false) String videoUrl
//            ,@ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType
    ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        SysUser sysUser =(SysUser) request.getSession().getAttribute(Constants.CURRENT_USER);
        Map<String, Object> map = concernService.createConcern(content, createId, visible, imgFileUrl,videoUrl,
                (Integer) request.getSession().getAttribute("appType"));
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/findConcern", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有动态", notes = "查询所有动态")
    public ModelAndView findConcern(HttpServletRequest request,
                                    @ApiParam(name = "page", required = true, value = "页码:从零开始")
                                    @RequestParam(value = "page") Integer page,
                                    @ApiParam(name = "pageSize", required = true, value = "显示数量")
                                    @RequestParam(value = "pageSize") Integer pageSize,
                                    @ApiParam(name = "searchText", required = false, value = "动态模糊查询条件。此处为发布人")
                                    @RequestParam(value = "searchText", required = false) String searchText
//                                    ,@ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType
                                                                                                                         ) {
        Map<String, Object> map = null;
        if (Objects.equals(searchText, null) || Objects.equals(searchText, "")) {
            map = concernService.findByPager(page, pageSize,(Integer) request.getSession().getAttribute("appType"));
        } else {
            map = concernService.search(page, pageSize, searchText,(Integer) request.getSession().getAttribute("appType"));
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

//    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
//    @ApiOperation(value = "获取单条动态数据", notes = "获取单条动态数据")
//    public ModelAndView getOne(@RequestParam(value = "id") Integer id) {
//        Map<String, Object> map = concernService.findById(id);
//        AbstractView jsonView = new MappingJackson2JsonView();
//        jsonView.setAttributesMap(map);
//        return new ModelAndView(jsonView);
//    }

//    @RequestMapping(value = "/deleteOne", method = RequestMethod.DELETE)
//    @ApiOperation(value = "删除动态", notes = "逻辑删除")
//    public ModelAndView deleteOne(@RequestParam(value = "id") Integer id) {
//        Map<String, Object> map = concernService.deleteOne(id);
//        devaService.cascadeDelete(Constants.MODEL_CONCERN,id);
//        AbstractView jsonView = new MappingJackson2JsonView();
//        jsonView.setAttributesMap(map);
//        return new ModelAndView(jsonView);
//    }

    @RequestMapping(value = "/mask", method = RequestMethod.DELETE)
    @ApiOperation(value = "屏蔽", notes = "屏蔽")
    public ModelAndView mask(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = concernService.setVisible(id, -2);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "删除")
    public ModelAndView del(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = concernService.setVisible(id, -1);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑动态", notes = "编辑动态")
    public ModelAndView edit(@RequestParam(value = "id") Integer id,
                             @ApiParam(name = "topic_content", required = false, value = "内容")
                             @RequestParam(value = "topic_content", required = false) String topicContent,
                             @ApiParam(name = "img_url", required = false, value = "图片路径")
                             @RequestParam(value = "img_url", required = false) String imgUrl,
                             @ApiParam(name = "videoUrl", required = false, value = "视频路径")
                                 @RequestParam(value = "videoUrl", required = false) String videoUrl) {
        Map<String, Object> map = concernService.edit(topicContent, imgUrl, videoUrl,id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

//    @RequestMapping(value = "search", method = RequestMethod.POST)
//    @ApiOperation(value = "动态模糊查询", notes = "动态模糊查询")
//    ModelAndView search(@RequestParam(value = "start") Integer start,
//                        @RequestParam(value = "pageSize") Integer pageSize,
//                        @RequestParam(value = "searchText") String searchText) {
//        Map<String, Object> map = concernService.search(start, pageSize, searchText);
//        AbstractView jsonView = new MappingJackson2JsonView();
//        jsonView.setAttributesMap(map);
//        return new ModelAndView(jsonView);
//    }

}
