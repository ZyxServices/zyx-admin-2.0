package com.zyx.service.activity;


import com.zyx.model.Activity;
import com.zyx.parm.activity.QueryActivityParm;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by Rainbow on 16-6-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title com.zyx.service.activity
 */
public interface ActivityService extends BaseService<Activity>{

    /**
     * 添加活动
     * @param activity
     * @return
     */
    Map<String, Object> insertActivity(Activity activity);

    /**
     * 多条条件查询活动
     *
     * @param queryActivityParm
     * @param
     * @return
     */
    Map<String, Object> queryActivity(QueryActivityParm queryActivityParm);

    /**
     * 通过ID查询活动
     *
     * @param activityId
     * @return
     */
    Map<String, Object> queryActivityById(Integer activityId);

    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    Map<String, Object> updateActivity(Activity activity);

    /**
     * 屏蔽活动
     *
     * @param maskType
     * @return
     */
    Map<String, Object> maskActivity(int id,int maskType);

    /**
     * 删除活动 （逻辑删除）
     *
     * @param
     * @return
     */
    Map<String, Object> delActivity(String id);

    /**
     * 分享  获取活动和评论
     * @param id
     * @return
     */
    Map<String, Object> getActivityDataById(Integer type,Integer id);

}
