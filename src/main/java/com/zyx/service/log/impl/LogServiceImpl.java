package com.zyx.service.log.impl;

import com.zyx.constants.Constants;
import com.zyx.dto.LogDto;
import com.zyx.mapper.LogMapper;
import com.zyx.model.Log;
import com.zyx.parm.log.LogParam;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.log.LogService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by HL on 2016/11/17.
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{
    @Resource
    private LogMapper logMapper;

    public LogServiceImpl(){
        super(Log.class);
    }

    @Override
    public Map<String, Object> queryLog(LogParam logParam) {
        List<LogDto> rst = logMapper.queryLog(logParam);
        int i = logMapper.selectCountLog(logParam);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", rst);
        map.put("total", i);
        return map;
    }
}
