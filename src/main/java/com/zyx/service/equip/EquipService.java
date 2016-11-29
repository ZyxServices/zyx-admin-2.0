package com.zyx.service.equip;

import com.zyx.model.Equip;
import com.zyx.parm.Equip.QueryEquipParam;
import com.zyx.service.BaseService;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * Created by zjx on 2016/11/14.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          装备贴的业务层接口
 */
public interface EquipService extends BaseService<Equip>{
    /**
     * 添加装备帖
     * @param equip
     * @return
     */
    Map<String,Object> insertEquip(Equip equip);

    /**
     * 动态分页查询装备帖
     * @param equipParam
     * @return
     */
    Map<String,Object> queryEquip(QueryEquipParam equipParam);

    /**
     * 编辑装备帖
     * @param equip 装备帖对象
     * @return
     */
    Map<String ,Object> updateEquip(Equip equip);

    /**
     * 删除装备帖，逻辑删除
     * @param idStr id字符串
     * @param delType 删除状态
     * @return
     */
    Map<String,Object> delEquip(String idStr,int delType);

    /**
     * 屏蔽装备帖
     * @param id 装备id
     * @param maskType 屏蔽状态
     * @return
     */
    Map<String ,Object> maskEquip(int id,int maskType);

    /**
     *
     * @param type
     * @param id
     * @return
     */
    Map<String ,Object> getEquipDataById(Integer type, Integer id);
}
