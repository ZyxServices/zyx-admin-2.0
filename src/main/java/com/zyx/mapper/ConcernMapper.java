package com.zyx.mapper;

import com.zyx.model.Concern;
import com.zyx.model.vo.ConcernVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 社区动态的持久层接口
 */
@Repository("concernMapper")
public interface ConcernMapper extends Mapper<Concern> {
    /**
     * 总动态数
     *
     * @return
     */
    int count();

    /**
     * 添加动态
     * @param concern
     * @return
     */
    int insertConcern(Concern concern);

    /**
     * 根据分页条件查询动态
     *
     * @param start
     * @param end
     * @return
     */
    List<ConcernVo> findByPager(@Param(value = "start") int start, @Param(value = "end") int end);

    /**
     * 查询某一条数据
     *
     * @param id
     * @return
     */
//    Concern findById(@Param(value = "id") Integer id);

    /**
     * 修改动态状态:屏蔽或者删除
     *
     * @param id
     * @return
     */
    Integer setState(@Param(value = "state") Integer state, @Param(value = "id") Integer id);

    /**
     * 编辑动态
     *
     * @param topicContent
     * @param imgUrl
     * @return
     */
    Integer edit(@Param(value = "topic_content") String topicContent, @Param(value = "img_url") String imgUrl, @Param(value = "video_url") String videoUrl, @Param(value = "id") Integer id);

    /**
     * 按照发布人动态搜索
     * @param userName 发布人
     * @param start
     * @param pageSize
     * @return
     */
    List<Concern> search(@Param(value = "userName") String userName, @Param(value = "start") Integer start, @Param(value = "end") Integer pageSize);

}