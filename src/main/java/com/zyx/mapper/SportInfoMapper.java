package com.zyx.mapper;

import com.zyx.dto.SportInfoDto;
import com.zyx.model.SportInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by HL on 2016/11/7.
 */
@Repository("sportInfoMapper")
public interface SportInfoMapper extends Mapper<SportInfo>{

    /**
     * 修改路线数据
     * @param sportInfo
     * @return
     */
    int updateSportInfo(SportInfo sportInfo);

    /**
     * 根据多条件查询路线
     * @param sportInfo
     * @return
     */
    List<SportInfoDto> querySportInfo(SportInfo sportInfo);

    /**
     * 根据多条件查询路线获取路线数量
     * @param sportInfo
     * @return
     */
    int selectCountSportInfo(SportInfo sportInfo);

    /**
     * 根据主键逻辑删除
     * @param id
     * @return
     */
    int delSportInfo(Integer id);

}