package com.zyx.service.sportinfo.impl;

import com.zyx.constants.Constants;
import com.zyx.dto.SportInfoDto;
import com.zyx.mapper.SportInfoMapper;
import com.zyx.model.SportInfo;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.sportinfo.SportInfoService;
import com.zyx.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by HL on 2016/11/7.
 */
@Service("pathLevelService")
public class SportInfoServiceImpl extends BaseServiceImpl<SportInfo> implements SportInfoService {
    @Autowired
    private SportInfoMapper sportInfoMapper;
    public SportInfoServiceImpl() {
        super(SportInfo.class);
    }

    @Override
    public Map<String, Object> insertSportInfo(SportInfo sportInfo) {
        if (sportInfo.getVenueId()!=null){
            sportInfo.setCreateTime(new Date().getTime());
            int rst=sportInfoMapper.insert(sportInfo);
            if (rst>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "数据插入成功",null);
            }else {
                return  MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED,"数据插入失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> updateSportInfo(SportInfo pathLevel) {
        int i = sportInfoMapper.updateSportInfo(pathLevel);
        if (i > 0) {
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
        } else {
            return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据更新失败");
        }
    }

    @Override
    public Map<String, Object> delSportInfo(String id) {
        String ids[] = id.split(",");
        int a=0;
        for(String temp:ids){
            int i=sportInfoMapper.delSportInfo(Integer.valueOf(temp));
            if (i>0){
                a++;
            }
        }
        if (a>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "数据删除成功",null);
        }else {
            return  MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"数据删除失败");
        }
    }

    @Override
    public Map<String, Object> querySportInfo(SportInfo pathLevel) {
        pathLevel.setPage(pathLevel.getPage()*pathLevel.getPageNumber());
        List<SportInfoDto> sportInfoDtos = sportInfoMapper.querySportInfo(pathLevel);
        int i = sportInfoMapper.selectCountSportInfo(pathLevel);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", sportInfoDtos);
        map.put("total", i);
        return map;

    }
}
