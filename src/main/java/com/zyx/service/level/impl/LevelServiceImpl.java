package com.zyx.service.level.impl;

import com.zyx.constants.ActivityConstants;
import com.zyx.constants.Constants;
import com.zyx.dto.LevelDto;
import com.zyx.mapper.LevelMapper;
import com.zyx.model.Level;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.level.LevelService;
import com.zyx.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

/**
 * Created by HL on 2016/11/11.
 */
@Service("levelService")
public class LevelServiceImpl extends BaseServiceImpl<Level> implements LevelService{
    @Autowired
    LevelMapper levelMapper;
    public LevelServiceImpl() {
        super(Level.class);
    }

    @Override
    public Map<String, Object> insertLevel(Level level) {
        List<String> steps = levelMapper.querySteps(level.getAppType());
        if (steps.contains(level.getStep())){
            return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED, "添加等级失败,重复的阶级");
        }
        if (level.getName()!=null&&level.getStep()!=null&&level.getScore()!=null) {
            level.setDel(0);
            level.setCreateTime(new Date().getTime());
            int insert = levelMapper.insert(level);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "添加等级成功", null);
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED, "添加等级失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> delLevel(String id) {
        String ids[] = id.split(",");
        int a=0;
        for(String temp:ids){
            int i=levelMapper.delLevel(Integer.valueOf(temp));
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
    public Map<String, Object> updateLevel(Level level) {
        if (level.getName()!=null&&level.getId()!=null) {
            int insert = levelMapper.updateLevel(level);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "修改等级成功", null);
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "修改等级失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> queryLevel(Level level) {
        List<LevelDto> levels = levelMapper.queryLevel(level);
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", levels);
            map.put("total", levels.size());
            return map;
    }
}
