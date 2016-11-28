package com.zyx.aop;

import com.zyx.constants.Constants;
import com.zyx.mapper.LogMapper;
import com.zyx.model.Log;
import com.zyx.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;

import static java.awt.SystemColor.info;
import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * Created by MrDeng on 2016/11/18.
 */
public class LogHandler {
    private static Logger logger= LoggerFactory.getLogger(LogHandler.class);
    @Autowired
    private LogMapper logMapper;

    public void before(){
    }
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);

        //拦截的方法名称
        String methodName = pjp.getSignature().getName();
        Object object = pjp.proceed(); //获取被切函数的 返回值
        String rst = object.toString();
        if (user!=null){
            if (Constants.logMap.containsKey(methodName)){
                Log log = new Log();
                log.setOperationDesc(Constants.logMap.get(methodName));
                log.setUserId(user.getId());
                log.setCreateTime(new Date().getTime());
                log.setDel(0);
                log.setResult(0);
                logMapper.insert(log);
            }
            logger.info("用户操作记录:………方法名："+methodName+"………用户id"+user==null?"":user.getId()+"…………操作描述："+Constants.logMap.get(methodName)+"………返回值："+rst);
        }
        return object;
    }

    public void after(){
    }

    public void exception(JoinPoint point,Throwable e){
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null){
            String methodName = point.getSignature().getName();
            if (Constants.logMap.containsKey(methodName)){
                Log log = new Log();
                log.setOperationDesc(Constants.logMap.get(methodName));
                log.setUserId(user.getId());
                log.setCreateTime(new Date().getTime());
                log.setDel(0);
                log.setResult(1);
                logMapper.insert(log);
            }
            logger.info("用户操作记录:………方法名："+methodName+"………用户id"+user==null?"":user.getId()+"…………操作描述："+Constants.logMap.get(methodName)+"…………Exception:"+e);
        }
    }

}
