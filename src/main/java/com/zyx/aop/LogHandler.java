package com.zyx.aop;

import com.zyx.constants.Constants;
import com.zyx.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by MrDeng on 2016/11/18.
 */
public class LogHandler {
    @Autowired
    private HttpServletRequest request;

    public void before(){
        System.out.println("……………………before method logger info………………………………");
    }
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
        pjp.getArgs();
        Object object = pjp.proceed(); //获取被切函数的 返回值
        System.out.println(user.toString()+"^^^^^^^^^^"+user.getId()+"……………………around method logger info………………………………"+object.toString());
        return object;
    }
    public void after(){
        System.out.println("……………………after method logger info………………………………");
    }
    public void exception(){
        System.out.println("……………………excepion method logger info………………………………");
    }

}
