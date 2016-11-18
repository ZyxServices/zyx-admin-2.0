package com.zyx.mapper;

import com.zyx.dto.VenueDto;
import com.zyx.model.Venue;
import com.zyx.parm.venue.VenueParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by HL on 2016/11/7.
 */
@Repository("venueMapper")
public interface VenueMapper extends Mapper<Venue>{

    /**
     * 多条件查询场馆
     * @param venueParam 场馆对象
     * @return
     */
    List<VenueDto> queryVenue(VenueParam venueParam);

    /**
     * 更新场馆数据
     * @param venue
     * @return
     */
    int updateVenue(Venue venue);

    /**
     * 根据多条件查询对应数量
     * @return
     */
    int selectCountVenue(VenueParam venueParam);

    /**
     * 新增场馆 返回主键id
     * @param venue
     * @return
     */
    Integer insertVenue(Venue venue);

    /**
     * 根据主键逻辑删除
     * @param id
     * @return
     */
    int delVenue(Integer id);
}
