package com.zyx.mapper;

import com.zyx.dto.LogDto;
import com.zyx.model.Log;
import com.zyx.parm.log.LogParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by HL on 2016/11/17.
 */
@Repository("logMapper")
public interface LogMapper extends Mapper<Log>{

    /**
     * 获取操作日志
     * @param logParam
     * @return
     */
    List<LogDto> queryLog(LogParam logParam);

    /**
     * 查询日志数量
     * @return
     */
    int selectCountLog();

    int insertLog(Log log);


}
