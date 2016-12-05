package com.zyx.service.opinion;

import com.zyx.model.Opinion;
import com.zyx.parm.appUser.OpinionParam;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by zjx on 2016/12/5.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
public interface OpinionService extends BaseService<Opinion> {

    /**
     * 分页查询
     * @param param
     * @return
     */
    Map<String,Object> queryByUser(OpinionParam param);

    /**
     * 删除意见反馈
     * @return
     */
    Map<String,Object> delOpinion(Integer opinionId);
}
