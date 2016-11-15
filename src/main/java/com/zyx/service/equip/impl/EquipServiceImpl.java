package com.zyx.service.equip.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.EquipMapper;
import com.zyx.model.Equip;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.equip.EquipService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Created by zjx on 2016/11/14.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Service
public class EquipServiceImpl extends BaseServiceImpl<Equip> implements EquipService {

    @Resource
    private EquipMapper equipMapper;
    public EquipServiceImpl() {
        super(Equip.class);
    }

    /**
     * 添加装备帖
     * @param equip
     * @return
     */
    @Override
    public Map<String, Object> insertEquip(Equip equip) {
        if(equip.getTitle()!=null && equip.getContent()!=null && equip.getAccountId()!=null){

            equip.setDel(0);
            equip.setMask(0);
            equip.setCreateTime(new Date().getTime());

            int insert = equipMapper.insertEquip(equip);
            if(insert>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"发布成功",null);
            }else {
                return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED,"添加失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
        }

    }

    /**
     * 查询装备帖
     * @param equip
     * @return
     */
    @Override
    public Map<String, Object> queryEquip(Equip equip) {
        List<Equip> equipList = equipMapper.queryEquip(equip);
        int i = equipMapper.selectCountEquip();
        if(equipList!=null && equipList.size()>0){
            Map<String, Object> map =MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",equipList);
            map.put("total",i);
            return map;
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"查无数据");
        }

    }

    /**
     * 修改装备帖
     * @param equip 装备帖对象
     * @return
     */
    @Override
    public Map<String, Object> updateEquip(Equip equip) {
        if(equip!=null){
            int i = equipMapper.updateEquip(equip);
            if(i>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"修改成功",null);
            }else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED,"数据更新失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
        }

    }

    /**
     * 删除装备帖
     * @param idStr id字符串
     * @param delType 删除状态
     * @return
     */
    @Override
    public Map<String, Object> delEquip(String idStr, int delType) {
        if(delType>-1 && delType<2 && idStr!=null && !idStr.equals("")){
            int a = 0;
            String[] ids = idStr.split(",");
            for(String id :ids){
                if(id!=null && Integer.valueOf(id) > 0){

                    Equip equip = new Equip();
                    equip.setId(Integer.parseInt(id));
                    equip.setDel(delType);
                    int i = equipMapper.updateEquip(equip);
                    if(i>0){
                        a++;
                    }

                }
            }

            if(a>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"删除成功",null);
            }else {
               return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED,"删除失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数有误");
        }

    }

    /**
     * 屏蔽装备帖
     * @param id 装备id
     * @param maskType 屏蔽状态
     * @return
     */
    @Override
    public Map<String, Object> maskEquip(int id, int maskType) {
        if(maskType>-1 && maskType<2 && id>0){
            Equip equip = new Equip();
            equip.setId(id);
            equip.setMask(maskType);
            int i = equipMapper.updateEquip(equip);
            if(i>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"屏蔽成功",null);

            }else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED,"屏蔽失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数有误");
        }

    }
}
