package com.zyx.controller.level;

import com.zyx.model.Level;
import com.zyx.service.level.LevelService;
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

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

/**
 * Created by HL on 2016/11/11.
 */
@Controller
@RequestMapping("/v2/level")
public class LevelController {

    @Autowired
    private LevelService levelService;


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ApiOperation(value = "新增等级",notes = "新增等级")
    public ModelAndView insert(@ApiParam(name = "name",required = true,value = "等级名称")@RequestParam(name = "name",required = true) String name,
                               @ApiParam(name = "step",required = true,value = "阶级")@RequestParam(name = "step",required = true) String step,
                               @ApiParam(name = "minScore",required = true,value = "等级最小积分 如：0-300")@RequestParam(name = "minScore",required = true) Integer minScore,
                               @ApiParam(name = "maxScore",required = true,value = "等级最大积分 如：0-300")@RequestParam(name = "maxScore",required = true) Integer maxScore,
                               @ApiParam(name = "appType",required = true,value = "app类型，1为趣攀岩，默认值为1")@RequestParam(name = "appType",required = true) Integer appType
                                ){
        AbstractView jsonView = new MappingJackson2JsonView();
        Level level = new Level();
        level.setName(name);
        level.setStep(step);
        if (appType!=null){
            level.setAppType(appType);
        }else {
            level.setAppType(1);
        }
        level.setMaxScore(maxScore);
        level.setMinScore(minScore);
        level.setScore(maxScore-minScore);
        Map<String,Object> map =levelService.insertLevel(level);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);

    }

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ApiOperation(value = "删除等级",notes = "删除等级")
    public ModelAndView del(@ApiParam(name = "id",required = true,value = "等级主键id")@RequestParam(name = "id",required = true)String id){
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Object> map = levelService.delLevel(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "修改等级",notes = "修改等级")
    public  ModelAndView update(@ApiParam(name = "id",required = true,value = "主键id")@RequestParam(name = "id",required =true)Integer id,
                                @ApiParam(name = "name",required = true,value = "等级名称")@RequestParam(name = "name",required =true)String name
                                ){
        AbstractView jsonView = new MappingJackson2JsonView();
        Level level = new Level();
        level.setId(id);
        level.setName(name);
        Map<String,Object> map =levelService.updateLevel(level);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @ApiOperation(value = "获取所有等级",notes = "获取所有等级")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public  ModelAndView list(@ApiParam(name = "appType",required = true,value = "app类型，1为趣攀岩，默认值为1")@RequestParam(name = "appType",required = true) Integer appType){
        AbstractView jsonView = new MappingJackson2JsonView();
        Level query = new Level();
        query.setAppType(appType);
        Map<String,Object> map=levelService.queryLevel(query);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


}
