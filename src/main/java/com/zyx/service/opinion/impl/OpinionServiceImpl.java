package com.zyx.service.opinion.impl;

import com.zyx.constants.Constants;
import com.zyx.dto.OpinionListDto;
import com.zyx.mapper.OpinionMapper;
import com.zyx.model.Opinion;
import com.zyx.parm.appUser.OpinionParam;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.opinion.OpinionService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zjx on 2016/12/5.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Service
public class OpinionServiceImpl extends BaseServiceImpl<Opinion> implements OpinionService {

    @Resource
    private OpinionMapper opinionMapper;

    public OpinionServiceImpl() {
        super(Opinion.class);
    }



    @Override
    public Map<String, Object> queryByUser(OpinionParam param) {
        if(param.getAppType()>0){
            List<OpinionListDto> list = opinionMapper.queryByUser(param);
            int count = opinionMapper.queryByUserCount(param);

            if(count>0){
                Map<String ,Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",list);
                map.put("total",count);
                return map;
            }else {
                return MapUtils.buildErrorMap(Constants.NO_DATA, "查无数据");
            }
        }

        return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
    }

    @Override
    public Map<String, Object> delOpinion(Integer opinionId) {
        if(opinionId!=null){
            Opinion opinion = new Opinion();
            opinion.setId(opinionId);
            try {
                int i =  opinionMapper.updateOpinion(opinion);
                if(i>0){
                    return MapUtils.buildSuccessMap(Constants.SUCCESS,"删除成功","");
                }else {
                    return Constants.MAP_DEL_ERROR;
                }
            }catch (Exception e){
                return MapUtils.buildErrorMap(Constants.PARAM_ERROR,"id错误或者没有该id");
            }


        }
        return MapUtils.buildErrorMap(Constants.PARAM_MISS,"参数缺失");
    }
}
