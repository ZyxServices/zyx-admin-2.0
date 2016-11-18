package com.zyx.controller.sysmessage;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zyx.model.Devaluation;
import com.zyx.model.SysMessage;
import com.zyx.parm.sysmessage.SysMessageParam;
import com.zyx.service.sysmessage.SysMessageService;
import com.zyx.utils.GetTimeUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Date;
import java.util.Map;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.st;
import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

/**
 * Created by HL on 2016/11/14.
 */
@Controller
@RequestMapping("/v2/sysMessage")
public class SysMessageController {
    @Autowired
    private SysMessageService sysMessageService;

    @ApiOperation(value = "创建消息", notes = "创建消息")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ModelAndView insert(
                                @ApiParam(name = "content",required = true,value = "消息内容")@RequestParam(name = "content",required = true)String content,
                                @ApiParam(name = "type",required = true,value = "消息类型 0-系统消息 1-日常推送")@RequestParam(name = "type",required = true)Integer type,
                                @ApiParam(name = "pushType",required = true,value = "消息发送模式 0-及时 1-定时")@RequestParam(name = "pushType",required = true)Integer pushType,
                                @ApiParam(name = "pushTime",required = false,value = "定时发送时间")@RequestParam(name = "pushTime",required = false)String pushTime
                        ){
        AbstractView jsonView = new MappingJackson2JsonView();
        SysMessage sysMessage = new SysMessage();
        sysMessage.setContent(content);
        if (StringUtils.isNotEmpty(pushTime)){
            sysMessage.setPushTime(GetTimeUtil.getDateTime(pushTime));
        }
        sysMessage.setPushType(pushType);
        sysMessage.setType(type);
        sysMessage.setMask(0);
        Map<String, Object> map = sysMessageService.insertSysMessage(sysMessage);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/mask",method = RequestMethod.POST)
    @ApiOperation(value = "消息管理 撤销-恢复",notes = "消息管理 撤销-恢复")
    public ModelAndView mask(@ApiParam(name = "id",required = true,value = "主键id")@RequestParam(name = "id",required = true)Integer id,
                             @ApiParam(name = "mask",required = true,value = "0-正常 1-撤销")@RequestParam(name = "mask",required = true)Integer mask
                            ){
        AbstractView jsonView = new MappingJackson2JsonView();
        SysMessage sysMessage = new SysMessage();
        sysMessage.setId(id);
        sysMessage.setMask(mask);
        Map<String,Object> result = sysMessageService.updateSysMessage(sysMessage);
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }



    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ApiOperation(value = "删除消息",notes = "删除消息")
    public ModelAndView update(@ApiParam(name = "id",required = true,value = "消息主键id")@RequestParam(name = "id",required = true)String ids){
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map =sysMessageService.delSysMessage(ids);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value = "querySysMessage",method = RequestMethod.GET)
    @ApiOperation(value = "消息列表",notes = "消息列表")
    public ModelAndView querySysMessage(@ApiParam(name = "page",required =true,value = "页码从0开始")@RequestParam(name = "page",required = true)Integer page,
                                        @ApiParam(name = "pageNumber",required =true,value = "每页数量")@RequestParam(name = "pageNumber",required = true)Integer pageNumber
                                        ){
        AbstractView jsonView = new MappingJackson2JsonView();
        SysMessageParam param = new SysMessageParam();
        param.setPageNumber(page);
        param.setPageSize(pageNumber);
        Map<String, Object> map =sysMessageService.querySysMessage(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
