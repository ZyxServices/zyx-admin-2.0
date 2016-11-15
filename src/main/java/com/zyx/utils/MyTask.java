package com.zyx.utils;

import com.zyx.dto.SysMessageDto;
import com.zyx.mapper.SysMessageMapper;
import com.zyx.model.SysMessage;
import com.zyx.service.AppUserService;
import com.zyx.service.sysmessage.SysMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HL on 2016/11/14.
 */
public class MyTask extends TimerTask{
    private static Logger logger=LoggerFactory.getLogger(Timer.class);
    private Long time;//间隔多少毫秒执行
    private Integer sysMessageId;
    private SysMessageMapper sysMessageMapper;

    public Long getTime() { return time; }

    public void setTime(Long time) { this.time = time; }

    public Integer getSysMessageId() { return sysMessageId; }

    public void setSysMessageId(Integer sysMessageId) { this.sysMessageId = sysMessageId; }

    public SysMessageMapper getSysMessageMapper() { return sysMessageMapper; }

    public void setSysMessageMapper(SysMessageMapper sysMessageMapper) { this.sysMessageMapper = sysMessageMapper; }

    public       MyTask(Long time,Integer sysMessageId, SysMessageMapper sysMessageMapper){
        this.time=time;
        this.sysMessageMapper=sysMessageMapper;
        this.sysMessageId=sysMessageId;
    }

    @Override
    public void run() {
        logger.debug("定时发布消息开始");
        //TODO 具体怎么发送消息
        SysMessageDto sysMessage = sysMessageMapper.queryById(sysMessageId);
        SysMessage db = new SysMessage();
        db.setId(sysMessage.getId());
        db.setDone(0);
        sysMessageMapper.updateSysMessage(db);
        logger.debug("定时发布消息结束");
        System.gc();
    }






}
