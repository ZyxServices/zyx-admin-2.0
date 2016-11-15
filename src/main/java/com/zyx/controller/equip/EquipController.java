package com.zyx.controller.equip;

import com.zyx.model.Equip;
import com.zyx.service.equip.EquipService;
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
 * Created by zjx on 2016/11/14.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          装备控的控制层
 */
@Controller
@RequestMapping("/v1/equip")
public class EquipController {

    @Resource
    private EquipService equipService;


    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ApiOperation(value="添加装备控帖子",notes = "添加装备控帖子")
    public ModelAndView add(@ApiParam(name = "accountId", required = true, value = "账户id")
                            @RequestParam(name="accountId",required = true)Integer accountId,
                            @ApiParam(name = "title", required = true, value = "标题")
                            @RequestParam(name="title",required = true)String title,
                            @ApiParam(name = "content", required = true, value = "内容")
                            @RequestParam(name="content",required = true)String content,
                            @ApiParam(name = "labelId", required = true, value = "标签id")
                            @RequestParam(name="labelId",required = true)Integer labelId){

        AbstractView jsonView = new MappingJackson2JsonView();
        Equip equip = new Equip();
        equip.setAccountId(accountId);
        equip.setContent(content);
        equip.setTitle(title);
        equip.setLabelId(labelId);
        Map<String,Object> map = equipService.insertEquip(equip);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/queryEquip",method = RequestMethod.GET)
    @ApiOperation(value="动态分页查询装备控帖子",notes = "动态分页装备控帖子")
    public ModelAndView queryEquip(@ApiParam(name = "title", required = false, value = "装备控标题")
                                    @RequestParam(name="title",required = false)String title,
                                    @ApiParam(name = "equipType", required = true, value = "装备控状态：0用户帖；1官方贴")
                                    @RequestParam(name="equipType",required = true)Integer equipType,
                                    @ApiParam(name = "page", required = true, value = "页码")
                                    @RequestParam(name="page",required = true)Integer page,
                                    @ApiParam(name = "pageNumber", required = true, value = "每页显示数量")
                                    @RequestParam(name="pageNumber",required = true)Integer pageNumber){

        AbstractView jsonView = new MappingJackson2JsonView();
        Equip equip = new Equip();
        equip.setTitle(title);
        equip.setEquipType(equipType);
        equip.setPage((page-1)*pageNumber);
        equip.setPageNumber(pageNumber);

        Map<String,Object> map = equipService.queryEquip(equip);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/updateEquip",method = RequestMethod.POST)
    @ApiOperation(value = "编辑装备控",notes="编辑装备控")
        public ModelAndView updateEquip(@ApiParam(name = "id", required = true, value = "装备控id")
                                         @RequestParam(name="id",required = true)Integer id,
                                         @ApiParam(name = "accountId", required = true, value = "账户id")
                                         @RequestParam(name="accountId",required = true)Integer accountId,
                                         @ApiParam(name = "title", required = true, value = "标题")
                                         @RequestParam(name="title",required = true)String title,
                                         @ApiParam(name = "content", required = true, value = "内容")
                                         @RequestParam(name="content",required = true)String content,
                                         @ApiParam(name = "labelId", required = true, value = "标签id")
                                          @RequestParam(name="labelId",required = true)Integer labelId){
        AbstractView jsonView = new MappingJackson2JsonView();


        Equip equip = new Equip();
        equip.setId(id);
        equip.setAccountId(accountId);
        equip.setContent(content);
        equip.setTitle(title);
        equip.setLabelId(labelId);

        Map<String,Object> map = equipService.updateEquip(equip);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);

    }

    @RequestMapping(value="/delEquip",method = RequestMethod.POST)
    @ApiOperation(value="删除装备控",notes="删除装备控")
    public ModelAndView delEquip( @ApiParam(name = "id", required = true, value = "装备控id")
                                   @RequestParam(name = "id", required = true) String id){
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Object> map = equipService.delEquip(id,1);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value="/maskEquip",method=RequestMethod.POST)
    @ApiOperation(value="屏蔽装备控",notes="屏蔽准备控")
    public ModelAndView maskEquip(@ApiParam(name = "id", required = true, value = "装备控id")
                                   @RequestParam(name = "id", required = true) Integer id) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Object> map = equipService.maskEquip(id,1);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


}