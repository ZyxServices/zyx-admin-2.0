package com.zyx.service.log;

import com.zyx.model.Log;
import com.zyx.parm.log.LogParam;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by HL on 2016/11/17.
 */
public interface LogService extends BaseService<Log>{
    /**
     * 日志查询
     * @param log
     * @return
     */
    Map<String,Object> queryLog(LogParam log);
}
