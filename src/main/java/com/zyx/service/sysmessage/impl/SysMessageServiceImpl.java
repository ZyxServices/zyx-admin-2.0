package com.zyx.service.sysmessage.impl;

import com.zyx.constants.Constants;
import com.zyx.dto.SysMessageDto;
import com.zyx.mapper.SysMessageMapper;
import com.zyx.model.SysMessage;
import com.zyx.service.AppUserService;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.sysmessage.SysMessageService;
import com.zyx.utils.MapUtils;
import com.zyx.utils.MyTask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * Created by HL on 2016/11/14.
 */
@Service
public class SysMessageServiceImpl extends BaseServiceImpl<SysMessage> implements SysMessageService{
    @Resource
    private SysMessageMapper sysMessageMapper;
    @Resource
    private AppUserService appUserService;
    public SysMessageServiceImpl() {
        super(SysMessage.class);
    }

    @Override
    public Map<String, Object> insertSysMessage(SysMessage sysMessage) {
        sysMessage.setCreateTime(new Date().getTime());
        if(sysMessage.getPushType()==1){
            sysMessage.setDel(0);
            sysMessage.setDone(1);
        }else {
            sysMessage.setDel(0);
            sysMessage.setDone(0);
        }
        int rst=sysMessageMapper.insertSysMessage(sysMessage);
        if (rst>0){
            if (1==sysMessage.getPushType()){
                //处理定时推送
                long time =sysMessage.getPushTime()-new Date().getTime();
                if (time>0){
                    MyTask myTimer  = new MyTask(time,sysMessage.getId(),sysMessageMapper);
                    Timer timer = new Timer();
                    timer.schedule(myTimer,time);
                }else {
                    SysMessage chang = new SysMessage();
                    chang.setId(sysMessage.getId());
                    chang.setDone(0);
                    sysMessageMapper.updateSysMessage(chang);
                }
            }
            return MapUtils.buildErrorMap(Constants.SUCCESS, "数据插入成功");
        }else {
            return  MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED,"数据插入失败");
        }
    }

    @Override
    public Map<String, Object> updateSysMessage(SysMessage sysMessage) {
        int i = sysMessageMapper.updateSysMessage(sysMessage);
        if (i > 0) {
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
        } else {
            return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据更新失败");
        }
    }

    @Override
    public Map<String, Object> delSysMessage(String id) {
        String ids[] = id.split(",");
        int a=0;
        for(String temp:ids){
            int i=sysMessageMapper.delSysMessage(Integer.valueOf(temp));
            if (i>0){
                a++;
            }
        }
        if (a>0){
            return MapUtils.buildErrorMap(Constants.SUCCESS, "数据删除成功");
        }else {
            return  MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"数据删除失败");
        }
    }

    @Override
    public Map<String, Object> querySysMessage(SysMessage sysMessage) {
        sysMessage.setPage(sysMessage.getPage()*sysMessage.getPageNumber());
        sysMessage.setDel(0);
        List<SysMessageDto> sysMessages = sysMessageMapper.querySysMessage(sysMessage);
        int i = sysMessageMapper.selectCountSysMessage(sysMessage);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", sysMessages);
        map.put("total", i);
        return map;
    }
}
