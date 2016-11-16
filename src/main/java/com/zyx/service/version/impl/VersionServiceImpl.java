package com.zyx.service.version.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.VersionMapper;
import com.zyx.model.Version;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.version.VersionService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by HL on 2016/11/15.
 */
@Service("versionService")
public class VersionServiceImpl extends BaseServiceImpl<Version> implements VersionService{

    @Resource
    private VersionMapper versionMapper;
    public VersionServiceImpl() {
        super(Version.class);
    }

    @Override
    public Map<String, Object> insertVersion(Version version) {
        version.setCreateTime(new Date().getTime());
        int rst=versionMapper.insert(version);
        if (rst>0){
            return MapUtils.buildErrorMap(Constants.SUCCESS, "数据插入成功");
        }else {
            return  MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED,"数据插入失败");
        }
    }

    @Override
    public Map<String, Object> updateVersion(Version version) {
        int i = versionMapper.updateVersion(version);
        if (i > 0) {
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
        } else {
            return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据更新失败");
        }
    }

    @Override
    public Map<String, Object> delVersion(String id) {
        String ids[] = id.split(",");
        int a=0;
        for(String temp:ids){
            int i=versionMapper.delVersion(Integer.valueOf(temp));
            if (i>0){
                a++;
            }
        }
        if (a>0){
            return MapUtils.buildErrorMap(Constants.SUCCESS, "数据删除成功");
        }else {
            return  MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"数据删除失败");
        }
    }

    @Override
    public Map<String, Object> queryVersion(Version version) {
        version.setPage(version.getPage()*version.getPageNumber());
        List<Version> venues = versionMapper.queryVersion(version);
        int i = versionMapper.selectCountVersion(version);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", venues);
        map.put("total", i);
        return map;
    }
}
