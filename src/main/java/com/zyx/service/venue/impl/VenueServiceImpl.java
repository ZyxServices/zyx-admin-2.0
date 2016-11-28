package com.zyx.service.venue.impl;

import com.zyx.constants.Constants;
import com.zyx.dto.CommentDto;
import com.zyx.dto.CommentListDto;
import com.zyx.dto.SportInfoDto;
import com.zyx.dto.VenueDto;
import com.zyx.mapper.CommentMapper;
import com.zyx.mapper.SportInfoMapper;
import com.zyx.mapper.VenueMapper;
import com.zyx.model.Comment;
import com.zyx.model.SportInfo;
import com.zyx.model.Venue;
import com.zyx.parm.sportinfo.SportInfoQueryParam;
import com.zyx.parm.venue.VenueParam;
import com.zyx.parm.version.VersionParam;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.venue.VenueService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

/**
 * Created by HL on 2016/11/7.
 */
@Service("venueService")
public class VenueServiceImpl extends BaseServiceImpl<Venue> implements VenueService{

    @Resource
    private VenueMapper venueMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private SportInfoMapper sportInfoMapper;

    public VenueServiceImpl() {
        super(Venue.class);
    }

    @Override
    public Map<String, Object> insertVenue(Venue venue) {
        if (venue.getLatitude()!=null&&venue.getLongitude()!=null){
            venue.setDel(0);
            venue.setCreateTime(new Date().getTime());
            int rst=venueMapper.insert(venue);
            if (rst>0){
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "数据插入成功", null);
            }else {
                return  MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED,"数据插入失败");
            }
        }else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> updateVenue(Venue venue) {
        int i = venueMapper.updateVenue(venue);
        if (i > 0) {
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", null);
        } else {
            return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据更新失败");
        }

    }

    @Override
    public Map<String, Object> delVenue(String id) {
        String ids[] = id.split(",");
        int a=0;
        for(String temp:ids){
            int i=venueMapper.delVenue(Integer.valueOf(temp));
            if (i>0){
                a++;
            }
        }
        if (a>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "数据删除成功", null);
        }else {
            return  MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"数据删除失败");
        }
    }

    @Override
    public Map<String, Object> queryVenue(VenueParam venueParam) {
        venueParam.setPageNumber(venueParam.getPageNumber()*venueParam.getPageSize());
        List<VenueDto> venues = venueMapper.queryVenue(venueParam);
        int i = venueMapper.selectCountVenue(venueParam);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", venues);
        map.put("total", i);
        return map;
    }

    @Override
    public Map<String, Object> getVenueDataById(Integer type,Integer id,Integer userId) {
        try {
            //场馆数据
            Venue venue = venueMapper.selectByPrimaryKey(id);
            //评论数据
            List<CommentDto> comments = commentMapper.queryByTypeAndId(type,id);
            SportInfoQueryParam sportInfoQueryParam = new SportInfoQueryParam();
            sportInfoQueryParam.setPageSize(10);
            sportInfoQueryParam.setPageNumber(0);
            sportInfoQueryParam.setVenueId(id);
            //路线数据
            List<SportInfoDto> paths = sportInfoMapper.querySportInfo(sportInfoQueryParam);
            //是否去过
            int count = sportInfoMapper.queryCountByUserIdAndVenueId(userId,id);

            Map<String,Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功","");
            map.put("venue",venue);
            map.put("comments",comments);
            map.put("paths",paths);
            map.put("hasGone",count>0?"是":"否");
            return map;
        }catch (Exception e){
            return MapUtils.buildErrorMap(Constants.ERROR,"查询失败");
        }

    }
}
