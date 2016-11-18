package com.zyx.service.sportinfo;

import com.zyx.model.SportInfo;
import com.zyx.parm.sportinfo.SportInfoQueryParam;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by HL on 2016/11/7.
 */
public interface SportInfoService extends BaseService<SportInfo> {
    /**
     * 新增线路
     * @param sportInfo
     * @return
     */
    Map<String,Object> insertSportInfo(SportInfo sportInfo);

    /**
     * 修改场馆
     * @param sportInfo
     * @return
     */
    Map<String,Object> updateSportInfo(SportInfo sportInfo);

    /**
     * 删除线路
     * 可传入id批量删除
     * @param id
     * @return
     */
    Map<String,Object> delSportInfo(String id);

    /**
     * 多条件联合查询路线
     * @param sportInfoQueryParam
     * @return
     */
    Map<String,Object> querySportInfo(SportInfoQueryParam sportInfoQueryParam);
}
