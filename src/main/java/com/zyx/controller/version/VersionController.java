package com.zyx.controller.version;

import com.zyx.constants.Constants;
import com.zyx.model.Version;
import com.zyx.service.version.VersionService;
import com.zyx.utils.GetTimeUtil;
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

/**
 * Created by HL on 2016/11/16.
 */
@RequestMapping("/v2/version")
@Controller
public class VersionController {
    @Autowired
    private VersionService versionService;

    @ApiOperation(value = "新增版本信息",notes = "新增版本信息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ModelAndView insert(@ApiParam(name = "number",required = true,value = "版本号")@RequestParam(name = "number",required = true)String number,
                               @ApiParam(name = "publishTime",required = true,value = "发布时间 格式yyyy-MM-dd hh:mm:ss")@RequestParam(name = "publishTime",required = true)String publishTime,
                               @ApiParam(name = "createTime",required = true,value = "创建时间 格式yyyy-MM-dd hh:mm:ss")@RequestParam(name = "createTime",required = true)String createTime,
                               @ApiParam(name = "downloadUrl",required = false,value = "下载地址")@RequestParam(name = "downloadUrl",required = false)String downloadUrl,
                               @ApiParam(name = "notes",required = false,value = "注释")@RequestParam(name = "notes",required = false)String notes,
                               @ApiParam(name = "systemType",required = true,value = "系统类型 0-Android 1-IOS")@RequestParam(name = "systemType",required = true)Integer systemType
                                ){
        AbstractView jsonView = new MappingJackson2JsonView();
        Version version = new Version();
        version.setPublishTime(GetTimeUtil.getDateTime(publishTime));
        version.setCreateTime(GetTimeUtil.getDateTime(createTime));
        version.setSystemType(systemType);
        version.setNumber(number);
        version.setNotes(notes);
        version.setDownloadUrl(downloadUrl);
        Map<String, Object> map = versionService.insertVersion(version);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动", notes = "删除活动")
    public ModelAndView del(@ApiParam(name = "id",required = true,value = "主键id 批量删除id用英文逗号隔开（1,2,3）")@RequestParam(name = "id", required = true) String id) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = versionService.delVersion(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @ApiOperation(value = "修改版本信息",notes = "修改版本信息")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ModelAndView insert(@ApiParam(name = "number",required = true,value = "版本号")@RequestParam(name = "number",required = true)String number,
                               @ApiParam(name = "publishTime",required = true,value = "发布时间 格式yyyy-MM-dd hh:mm:ss")@RequestParam(name = "publishTime",required = true)String publishTime,
                               @ApiParam(name = "createTime",required = true,value = "创建时间 格式yyyy-MM-dd hh:mm:ss")@RequestParam(name = "createTime",required = true)String createTime,
                               @ApiParam(name = "downloadUrl",required = false,value = "下载地址")@RequestParam(name = "downloadUrl",required = false)String downloadUrl,
                               @ApiParam(name = "notes",required = false,value = "注释")@RequestParam(name = "notes",required = false)String notes,
                               @ApiParam(name = "systemType",required = true,value = "系统类型 0-Android 1-IOS")@RequestParam(name = "systemType",required = true)Integer systemType,
                               @ApiParam(name = "id",required = true,value = "主键id")@RequestParam(name = "id",required = true)Integer id
                            ){
        AbstractView jsonView = new MappingJackson2JsonView();
        Version version = new Version();
        version.setId(id);
        version.setPublishTime(GetTimeUtil.getDateTime(publishTime));
        version.setCreateTime(GetTimeUtil.getDateTime(createTime));
        version.setSystemType(systemType);
        version.setNumber(number);
        version.setNotes(notes);
        version.setDownloadUrl(downloadUrl);
        Map<String, Object> map = versionService.updateVersion(version);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "queryVersion",method =RequestMethod.GET)
    @ApiOperation(value = "版本查询",notes = "版本查询")
    public ModelAndView queryVersion(@ApiParam(name = "systemType",required = false,value = "系统类型 0-Android 1-IOS")@RequestParam(name = "systemType",required = false)Integer systemType,
                                     @ApiParam(name = "page",required = true,value = "页码 从0开始")@RequestParam(name = "page",required = true)Integer page,
                                     @ApiParam(name = "pageNumber",required = true,value = "每页数量")@RequestParam(name = "pageNumber",required = true)Integer pageNumber
                                    ){
        AbstractView jsonView = new MappingJackson2JsonView();
        Version version = new Version();
        version.setPage(page);
        version.setPageNumber(pageNumber);
        version.setSystemType(systemType);
        Map<String,Object> map = versionService.queryVersion(version);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


}
