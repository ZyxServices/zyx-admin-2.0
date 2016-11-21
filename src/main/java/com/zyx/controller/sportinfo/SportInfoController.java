package com.zyx.controller.sportinfo;

import com.zyx.model.SportInfo;
import com.zyx.parm.sportinfo.SportInfoQueryParam;
import com.zyx.service.sportinfo.SportInfoService;
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

import static com.zyx.utils.GetTimeUtil.getDateTime;

/**
 * Created by HL on 2016/11/9.
 */
@RequestMapping("/v2/sportInfo")
@Controller
public class SportInfoController {
    @Autowired
    private SportInfoService sportInfoService;

    @ApiOperation(value = "新增路线", notes = "新增路线")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ModelAndView insert(@ApiParam(name = "venueId", required = true, value = "场馆id")
                               @RequestParam(name = "venueId", required = true) Integer venueId,
                               @ApiParam(name = "score", required = true, value = "难度对应得分")
                               @RequestParam(name = "score", required = true) Integer score,
                               @ApiParam(name = "path", required = true, value = "路线名称")
                               @RequestParam(name = "path", required = true) String path,
                               @ApiParam(name = "url", required = false, value = "路线图")
                               @RequestParam(name = "url", required = false) String url,
                               @ApiParam(name = "level", required = true, value = "难度等级")
                               @RequestParam(name = "level", required = true) String level,
                               @ApiParam(name = "pathType", required = true, value = "路线类型")
                               @RequestParam(name = "pathType", required = true) String pathType,
                               @ApiParam(name = "pathLength", required = true, value = "路线长度")
                               @RequestParam(name = "pathLength", required = true) Integer pathLength,
                               @ApiParam(name = "developer", required = true, value = "开线者")
                               @RequestParam(name = "developer", required = true) String developer,
                               @ApiParam(name = "developTime", required = true, value = "开线时间 时间格式yyyy-MM-dd HH:mm:ss")
                               @RequestParam(name = "developTime", required = true) String developTime
    ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        SportInfo sportInfo = new SportInfo();
        sportInfo.setType(1);//1表示攀岩
        sportInfo.setVenueId(venueId);
        sportInfo.setScore(score);
        sportInfo.setPath(path);
        sportInfo.setUrl(url);
        sportInfo.setLevel(level);
        sportInfo.setPathType(pathType);
        sportInfo.setPathLength(pathLength);
        sportInfo.setDeveloper(developer);
        sportInfo.setDevelopTime(getDateTime(developTime));
        Map<String, Object> map = sportInfoService.insertSportInfo(sportInfo);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除路线", notes = "删除路线")
    public ModelAndView delActivity(@ApiParam(name = "id", required = true, value = "主键id")
                                    @RequestParam(name = "id", required = true) String id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = sportInfoService.delSportInfo(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @ApiOperation(value = "修改路线", notes = "修改路线")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ModelAndView update(@ApiParam(name = "id", required = true, value = "路线主键id")
                               @RequestParam(name = "id", required = true) Integer id,
                               @ApiParam(name = "venueId", required = true, value = "场馆id")
                               @RequestParam(name = "venueId", required = true) Integer venueId,
                               @ApiParam(name = "score", required = true, value = "难度对应得分")
                               @RequestParam(name = "score", required = true) Integer score,
                               @ApiParam(name = "path", required = true, value = "路线名称")
                               @RequestParam(name = "path", required = true) String path,
                               @ApiParam(name = "url", required = false, value = "路线图")
                               @RequestParam(name = "url", required = false) String url,
                               @ApiParam(name = "level", required = true, value = "难度等级")
                               @RequestParam(name = "level", required = true) String level,
                               @ApiParam(name = "pathType", required = true, value = "路线类型")
                               @RequestParam(name = "pathType", required = true) String pathType,
                               @ApiParam(name = "pathLength", required = true, value = "路线长度")
                               @RequestParam(name = "pathLength", required = true) Integer pathLength,
                               @ApiParam(name = "developer", required = true, value = "开线者")
                               @RequestParam(name = "developer", required = true) String developer,
                               @ApiParam(name = "developTime", required = true, value = "开线时间 时间格式yyyy-MM-dd HH:mm:ss")
                               @RequestParam(name = "developTime", required = true) String developTime
    ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        SportInfo sportInfo = new SportInfo();
        sportInfo.setId(id);
        sportInfo.setVenueId(venueId);
        sportInfo.setScore(score);
        sportInfo.setPath(path);
        sportInfo.setUrl(url);
        sportInfo.setLevel(level);
        sportInfo.setPathType(pathType);
        sportInfo.setPathLength(pathLength);
        sportInfo.setDeveloper(developer);
        sportInfo.setDevelopTime(getDateTime(developTime));
        Map<String, Object> map = sportInfoService.updateSportInfo(sportInfo);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value = "/querySportInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据查询场馆id查询场馆路线", notes = "根据查询场馆id查询场馆路线")
    public ModelAndView queryPathLevel(@ApiParam(name = "page", required = true, value = "页码，从0开始")
                                       @RequestParam(name = "page", required = true) Integer page,
                                       @ApiParam(name = "pageNumber", required = true, value = "每页显示数量")
                                       @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                       @ApiParam(name = "venueId", required = true, value = "场馆id")
                                       @RequestParam(name = "venueId", required = true) Integer venueId
    ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        SportInfoQueryParam sportInfoQueryParam = new SportInfoQueryParam();
        sportInfoQueryParam.setVenueId(venueId);
        sportInfoQueryParam.setPageNumber(page);
        sportInfoQueryParam.setPageSize(pageNumber);
        Map<String, Object> map = sportInfoService.querySportInfo(sportInfoQueryParam);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}