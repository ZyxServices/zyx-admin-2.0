package com.zyx.service.community.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.PgConstants;
import com.zyx.mapper.ConcernMapper;
import com.zyx.model.Activity;
import com.zyx.model.CircleItem;
import com.zyx.model.Concern;
import com.zyx.model.LiveInfo;
import com.zyx.model.vo.ConcernVo;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.community.ConcernService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          社区动态的业务层实现类
 */
@Service
public class ConcernServiceImpl extends BaseServiceImpl<Concern> implements ConcernService {

    @Resource
    private ConcernMapper concernMapper;

    public ConcernServiceImpl() {
        super(Concern.class);
    }

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> findByPager(int start, int pageSize,int appType) {

        Optional.ofNullable(start).orElse(0);
        Optional.ofNullable(pageSize).orElse(0);
        Optional.ofNullable(appType).orElse(0);
        List<ConcernVo> concerns = concernMapper.findByPager(start * pageSize, pageSize,appType);
        Integer count = concernMapper.countConcern(start * pageSize, pageSize,appType);

        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", concerns, new HashMap() {{
            put("total", count);
        }});
        return map;
    }

//    @Override
//    public Map<String, Object> findById(Integer id) {
//        Optional.ofNullable(id).orElse(0);
////        concernM
//        Concern concern = concernMapper.findById(id);
//        if (concern != null) {
//            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, concern);
//        }
//        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
//    }

    /**
     * 删除
     * @param id
     * @return
//     */
//    @Override
//    public Map<String, Object> deleteOne(Integer id) {
//        Integer result = concernMapper.setState(-1, id);
//        if (result > 0) {
//            return MapUtils.buildSuccessMap(Constants.SUCCESS, "删除成功", null);
//        }
//        return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"删除失败");
//    }

    /**
     * 修改状态
     * @param id
     * @param state
     * @return
     */
    @Override
    public Map<String, Object> setVisible(Integer id, Integer state) {
        if(id!=null && state!=null){
        Integer result = concernMapper.setState(state, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "修改成功", null);
        }
        else {
            return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED,"数据更新失败");
        }
    }else {
        return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
    }
    }

    /**
     * 编辑动态
     * @param topicContent 动态内容
     * @param imgUrl 图片路径
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> edit(String topicContent, String imgUrl,String videoUrl, Integer id) {
        if (id == null) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30021, PgConstants.PG_ERROR_CODE_30021_MSG);
        }
        Concern concernFind = concernMapper.selectByPrimaryKey(id);
        if (id != null) {
//            if (topicContent == null) {
//                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30022, PgConstants.PG_ERROR_CODE_30022_MSG);
//            }
//            Optional.ofNullable(topicContent).ifPresent(concernFind::setTopicContent);
//            if (imgUrl == null || Objects.equals(topicContent, "")) {
//                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30013, PgConstants.PG_ERROR_CODE_30013_MSG);
//            }
//            Optional.ofNullable(imgUrl).ifPresent(concernFind::setImgUrl);
//            concernFind.setImgUrl(imgUrl);
            Integer result = concernMapper.edit(topicContent, imgUrl, videoUrl,id);
            if (result > 0) {
                return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_36000_MSG, null);
            }
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    /**
     * 添加动态
     * @param content 动态内容
     * @param createId 发布人id
     * @param topVisible 可见范围：0所有人可见，1朋友可见
     * @param dbImgPath 视频路径
     * @return
     */
    @Override
    public Map<String, Object> createConcern(String content, Integer createId, Integer topVisible, String dbImgPath, String videoUrl,Integer appType) {
        if (Objects.equals(content, null) || Objects.equals(content, "")) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30010, PgConstants.PG_ERROR_CODE_30010_MSG);
        }
        if (Objects.equals(createId, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30005, PgConstants.PG_ERROR_CODE_30005_MSG);
        }
//        if (Objects.equals(type, null)) {
//            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30017, PgConstants.PG_ERROR_CODE_30017_MSG);
//        }
        if (Objects.equals(topVisible, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30023, PgConstants.PG_ERROR_CODE_30023_MSG);
        }
//        if (Objects.equals(dbImgPath, null) || Objects.equals(dbImgPath, "")) {
//            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30013, PgConstants.PG_ERROR_CODE_30013_MSG);
//        }

        if (Objects.equals(appType, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30034, PgConstants.PG_ERROR_CODE_30034_MSG);
        }
        Concern concern = new Concern();
        concern.setCreateTime(new Date().getTime());
        concern.setTopicContent(content);
        concern.setTopicVisible(topVisible);
//        concern.setType(type);
        concern.setUserId(createId);
        concern.setState(0);//默认正常
        concern.setImgUrl(dbImgPath);
        concern.setVideoUrl(videoUrl);
        concern.setAppType(appType);
        Integer result = concernMapper.insertConcern(concern);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_33000_MSG, null);
        }
        return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG, null);
    }

    /**
     * 根据发布人动态查询动态信息
     * @param start
     * @param pageSize
     * @param userName
     * @return
     */
    @Override
    public Map<String, Object> search(Integer start, Integer pageSize, String userName,Integer appType) {
        if (Objects.equals(userName, null) || Objects.equals(userName, "")) {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, Constants.MSG_PARAM_MISS);
        }
        List<Concern> concerns = concernMapper.search(userName, start * pageSize, pageSize,appType);
//        return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, concerns);
        Integer count = concernMapper.serachCount(userName, start * pageSize, pageSize,appType);

        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", concerns, new HashMap() {{
            put("total", count);
        }});
        return map;
    }

    /**
     *
     * @param fromId
     * @param fromType 目前3个模块用到，1：直播，2：求约，3帖子
     * @param fromObj
     * @return
     */
    @Override
    public Integer fromConcern(Integer fromId, Integer fromType, Object fromObj) {
        if (Objects.equals(fromId, null)) {
            return 0;
        }
        if (Objects.equals(fromType, null)) {
            return 0;
        }
        if (Objects.equals(fromObj, null)) {
            return 0;
        }
        switch (fromType) {
            case 1:
                //直播
                LiveInfo liveInfo = (LiveInfo) fromObj;
                Concern concern = new Concern();
                concern.setFromId(fromId);
                concern.setFromType(1);
                concern.setType(5);
                concern.setCreateTime(new Date().getTime());
                if (!Objects.equals(liveInfo.getBgmUrl(), null)) {
                    concern.setImgUrl(liveInfo.getBgmUrl());//设置动态图片为直播的背景图片
                }
                concern.setTopicTitle(liveInfo.getTitle());
                concern.setTopicVisible(1);
                concern.setUserId(liveInfo.getUserId());
                concern.setState(0);
                return concernMapper.insert(concern);
            case 2:
                //活动
                Activity activity = (Activity) fromObj;
                Concern concernActivity = new Concern();
                concernActivity.setFromId(fromId);
                concernActivity.setFromType(fromType);
                concernActivity.setType(2);
                concernActivity.setCreateTime(new Date().getTime());
                if (!Objects.equals(activity.getImgUrls(), null)) {
                    concernActivity.setImgUrl(activity.getImgUrls());
                }
                concernActivity.setTopicTitle(activity.getTitle());
                concernActivity.setTopicVisible(1);
                concernActivity.setUserId(activity.getUserId());
                concernActivity.setState(0);
                return concernMapper.insert(concernActivity);
            case 3:
                //帖子
                CircleItem circleItem = (CircleItem) fromObj;
                Concern concernItem = new Concern();
                concernItem.setFromId(fromId);
                concernItem.setFromType(fromType);
                concernItem.setType(6);
                concernItem.setCreateTime(new Date().getTime());
                concernItem.setTopicTitle(circleItem.getTitle());
                concernItem.setTopicVisible(1);
                concernItem.setUserId(circleItem.getCreateId());
                concernItem.setState(0);
                return concernMapper.insert(concernItem);
        }
        return 0;
    }
}
