package com.zyx.mapper;

import com.zyx.dto.LevelDto;
import com.zyx.model.Level;
import io.swagger.models.auth.In;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by HL on 2016/11/11.
 */
public interface LevelMapper extends Mapper<Level> {
    /**
     *  根据主键id逻辑删除
     * @param id
     * @return
     */
    int delLevel(Integer id);

    /**
     * 修改等级相关数据
     * @param level
     * @return
     */
    int updateLevel(Level level);

    /**
     * 多条件查询
     * @param level
     * @return
     */
    List<LevelDto> queryLevel(Level level);

    /**
     * 获取已有的step
     * @param appType
     * @return
     */
    List<String>  querySteps(Integer appType);


}
