package com.zyx.service.level.iml;

import com.zyx.constants.ActivityConstants;
import com.zyx.constants.Constants;
import com.zyx.dto.ActivityDto;
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

/**
 * Created by HL on 2016/11/11.
 */
@Service("levelService")
public class LevelServiceImp extends BaseServiceImpl<Level> implements LevelService{
    @Autowired
    LevelMapper levelMapper;
    public LevelServiceImp() {
        super(Level.class);
    }

    @Override
    public Map<String, Object> insertLevel(Level level) {
        if (level.getName()!=null&&level.getStep()!=null&&level.getScore()!=null) {
            level.setDel(0);
            level.setCreateTime(new Date().getTime());
            int insert = levelMapper.insert(level);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "添加等级成功", null);
            } else {
                return MapUtils.buildErrorMap(ActivityConstants.AUTH_ERROR_10000, "添加等级失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> delLevel(Integer id) {
        if (id!=null){
            int rst = levelMapper.delLevel(id);
            if (rst>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "删除等级成功", null);
            }else {
                return MapUtils.buildSuccessMap(Constants.DATA_UPDATE_FAILED, "删除等级失败", null);
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
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
        if (levels != null && levels.size() > 0) {
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", levels);
            map.put("total", levels.size());
            return map;
        } else {
            return MapUtils.buildErrorMap(Constants.NO_DATA, "查无数据");
        }
    }
}
