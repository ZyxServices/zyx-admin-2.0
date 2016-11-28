package com.zyx.mapper;

import com.zyx.dto.SportInfoDto;
import com.zyx.dto.SportRecordDto;
import com.zyx.model.SportInfo;
import com.zyx.parm.sportinfo.SportInfoQueryParam;
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
     * @param sportInfoQueryParam
     * @return
     */
    List<SportInfoDto> querySportInfo(SportInfoQueryParam sportInfoQueryParam);

    /**
     * 根据多条件查询路线获取路线数量
     * @param sportInfoQueryParam
     * @return
     */
    int selectCountSportInfo(SportInfoQueryParam sportInfoQueryParam);

    /**
     * 根据主键逻辑删除
     * @param id
     * @return
     */
    int delSportInfo(Integer id);

    /**
     * 分享 查询用户的记录
     * @param id
     * @return
     */
    List<SportRecordDto> querySportRecordByUserId(Integer id);


    /**
     * 分享 用户去过每个场馆的记录数量
     */
    int queryCountByUserIdAndVenueId(Integer userId,Integer venueId);

}
