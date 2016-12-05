package com.zyx.mapper;

import com.zyx.dto.OpinionListDto;
import com.zyx.model.Opinion;
import com.zyx.parm.appUser.OpinionParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zjx on 2016/12/5.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright(c)2016 tyj-版权所有
 */
@Repository("opinionMapper")
public interface OpinionMapper extends Mapper<Opinion> {

    /**
     * 分页查询用户的意见反馈
     * @param param
     * @return
     */
    List<OpinionListDto> queryByUser(OpinionParam param);

    /**
     * 删除意见反馈
     * @param opinion
     * @return
     */
    int updateOpinion(Opinion opinion);

    /**
     * 分页查询出来的数量
     * @param param
     * @return
     */
    int queryByUserCount(OpinionParam param);
}
