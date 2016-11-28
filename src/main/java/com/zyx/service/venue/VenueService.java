package com.zyx.service.venue;

import com.zyx.model.Venue;
import com.zyx.parm.venue.VenueParam;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by HL on 2016/11/7.
 */
public interface VenueService extends BaseService<Venue>{

    /**
     * 添加场馆
     * @param venue
     * @return
     */
    Map<String, Object> insertVenue(Venue venue);

    /**
     * 修改场馆数据
     * @param venue
     * @return
     */
    Map<String,Object> updateVenue(Venue venue);

    /**
     * 删除场馆 （数据库中直接删除）
     * @param id
     * @return
     */
    Map<String,Object> delVenue(String id);

    /**
     * 多条件查询场馆
     * @param venueParam
     * @return
     */
    Map<String,Object> queryVenue(VenueParam venueParam);

    /**
     * 分享 获取场馆和评论数据
     * @param id 场馆id
     * @param type 评论类型
     * @return
     */
    Map<String,Object> getVenueDataById(Integer type,Integer id);
}
