package com.zyx.service.equip.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.EquipLabelMapper;
import com.zyx.mapper.EquipMapper;
import com.zyx.model.Equip;
import com.zyx.model.EquipLabel;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.equip.EquipLabelService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zjx on 2016/11/14.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          装备标签的业务层实现类
 */
@Service
public class EquipLabelServiceImpl extends BaseServiceImpl<EquipLabel> implements EquipLabelService{

    @Resource
    private EquipLabelMapper equipLabelMapper;

    @Resource
    private EquipMapper equipMapper;

    public EquipLabelServiceImpl() {
        super(EquipLabel.class);
    }

    @Override
    public Map<String, Object> insertEquipLabel(EquipLabel equipLabel) {
        if(equipLabel.getLabelName()!=null && equipLabel.getUserId()!=null){
            equipLabel.setState(0);
            equipLabel.setCreateTime(new Date().getTime());
            int insert = equipLabelMapper.insert(equipLabel);
            if(insert>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS,"添加成功",null);
            }else {
                return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED,"添加失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
        }

    }

    /**
     * 删除标签
     * @param equipLabelId 标签id
     * @return
     */
    @Override
    public Map<String, Object> delEquipLabel(Integer equipLabelId) {
        if(equipLabelId!=null){
            Equip equip = new Equip();
            equip.setLabelId(equipLabelId);
            List<Equip> equipList = equipMapper.queryEquip(equip);
            if(equipList.size()==0){
                int i = equipLabelMapper.deleteByPrimaryKey(equipLabelId);
                if(i>0){
                    return MapUtils.buildSuccessMap(Constants.SUCCESS,"删除成功",null);
                }else {
                    return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"删除失败");
                }
            }else {
                return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"该标签下还有数据，不能删除");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
        }

    }

    /**
     * 查询标签
     * @return
     */
    @Override
    public Map<String, Object> queryEquipLabel() {
        List<EquipLabel> equipLabels = equipLabelMapper.selectAll();
        if(equipLabels!=null && equipLabels.size()>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",equipLabels);
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }
    }

    /**
     * 设置标签禁用或者启用
     * @param equipLabel
     * @return
     */
    @Override
    public Map<String, Object> updateState(EquipLabel equipLabel) {
        if(equipLabel!=null){
            if(equipLabelMapper.selectByPrimaryKey(equipLabel.getId())==null){
                return MapUtils.buildErrorMap(Constants.NO_DATA,"该标签不存在");
            }
            int i = equipLabelMapper.updateState(equipLabel);
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
     * 查询启用的标签集合
     * @param
     * @return
     */
    @Override
    public Map<String, Object> queryByState() {
        EquipLabel equipLabel = new EquipLabel();
        equipLabel.setState(0);
        List<EquipLabel> equipLabels = equipLabelMapper.queryByState(equipLabel);
        if(equipLabels!=null && equipLabels.size()>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",equipLabels);
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }
    }


}
