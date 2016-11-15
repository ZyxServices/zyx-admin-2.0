package com.zyx.listener;

import com.zyx.dto.SysMessageDto;
import com.zyx.mapper.SysMessageMapper;
import com.zyx.model.SysMessage;
import com.zyx.utils.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Created by HL on 2016/11/15.
 */
@WebListener
public class MyTaskListener implements ServletContextListener{
    private static Logger logger= LoggerFactory.getLogger(Timer.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("开始执行未完成的定时发送任务");
        SysMessageMapper sysMessageMapper= WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getBean(SysMessageMapper.class);
        SysMessage sysMessage = new SysMessage();
        sysMessage.setDone(1);
        List<SysMessageDto> sysMessageDtos = sysMessageMapper.querySysMessage(sysMessage);
        for (SysMessageDto temp:sysMessageDtos){
            if (temp.getPushTime()<new Date().getTime()){
                SysMessage change = new SysMessage();
                change.setId(temp.getId());
                change.setDone(0);
                sysMessageMapper.updateSysMessage(change);
            }else {
                long time =temp.getPushTime()-new Date().getTime();
                MyTask myTimer  = new MyTask(time,temp.getId(),sysMessageMapper);
                Timer timer = new Timer();
                timer.schedule(myTimer,time);
            }
        }
        logger.info("处理未完成的定时任务结束");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
