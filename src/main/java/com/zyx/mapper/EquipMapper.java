package com.zyx.mapper;

import com.zyx.model.Equip;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zjx on 2016/11/11.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          装备控持久层接口
 */
@Repository("equipMapper")
public interface EquipMapper extends Mapper<Equip> {

    /**
     * 动态分页查询装备贴
     * @param equip 装备贴
     * @return
     */
    List<Equip> queryEquip(Equip equip);

//    /**
//     * 根据帖子类型查询帖子
//     * @param equipType
//     * @return
//     */
//    List<Equip> queryByType(int equipType);

    /**
     * 编辑装备控
     * @param equip 装备控对象
     * @return
     */
    int updateEquip(Equip equip);

    /**
     * 记录查询数量
     * @return
     */
    int selectCountEquip( Equip equip);

    /**
     * 添加装备控
     * @param equip
     * @return
     */
    int insertEquip(Equip equip);



}
