package com.zyx.service.community;

import com.zyx.model.Concern;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          社区动态业务层接口
 */

public interface ConcernService extends BaseService<Concern>{
    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    Map<String, Object> findByPager(int start, int pageSize);

    /**
     * 根据id查查询
     * @param id
     * @return
     */
//    Map<String, Object> findById(Integer id);

    /**
     * 删除动态
     * @param id
     * @return
     */
//    Map<String, Object> deleteOne(Integer id);

    /**
     * 修改状态
     * @param id
     * @param state
     * @return
     */
    Map<String, Object> setVisible(Integer id, Integer state);

    /**
     * 编辑动态
     * @param topicContent 动态内容
     * @param imgUrl 图片路径
     * @param id
     * @return
     */
    Map<String, Object> edit(String topicContent, String imgUrl,String videoUrl, Integer id);

    /**
     * 新增动态
     * @param content 动态内容
     * @param createId 发布人id
     * @param topVisible 可见范围：0所有人可见，1朋友可见
     * @param dbImgPath 图片路径
     * @param dbImgPath 视频路径
     * @return
     */
    Map<String, Object> createConcern(String content, Integer createId, Integer topVisible, String dbImgPath,String videoUrl);

    /**
     * 根据发布人动态查询
     * @param start
     * @param pageSize
     * @param userName
     * @return
     */
    Map<String, Object> search(Integer start, Integer pageSize, String userName);
    /**
     *
     * @param fromId
     * @param fromType 目前3个模块用到，1：直播，2：求约，3帖子
     * @param formObj
     * @return
     */
    Integer fromConcern(Integer fromId, Integer fromType,Object formObj);
}
