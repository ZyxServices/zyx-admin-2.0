package com.zyx.utils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import com.zyx.dto.SysMessageDto;
import com.zyx.mapper.SysMessageMapper;
import com.zyx.model.SysMessage;
import com.zyx.service.AppUserService;
import com.zyx.service.sysmessage.SysMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

import static cn.jpush.api.push.model.notification.PlatformNotification.ALERT;

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

    public void push(){
        JPushClient jpushClient = new JPushClient("masterSecret", "appKey", 3);

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras();

        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.error("Should review the error, and fix the request", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent("推送的文本内容")
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }
}
