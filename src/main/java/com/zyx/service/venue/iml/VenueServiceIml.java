package com.zyx.service.venue.iml;

import com.zyx.constants.Constants;
import com.zyx.dto.ActivityDto;
import com.zyx.dto.VenueDto;
import com.zyx.mapper.VenueMapper;
import com.zyx.model.Venue;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.venue.VenueService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

/**
 * Created by HL on 2016/11/7.
 */
@Service("venueService")
public class VenueServiceIml extends BaseServiceImpl<Venue> implements VenueService{

    @Resource
    private VenueMapper venueMapper;

    public VenueServiceIml() {
        super(Venue.class);
    }

    @Override
    public Map<String, Object> insertVenue(Venue venue) {
        if (venue.getLatitude()!=null&&venue.getLongitude()!=null){
            venue.setCreateTime(new Date().getTime());
            int rst=venueMapper.insert(venue);
            if (rst>0){
                return MapUtils.buildErrorMap(Constants.SUCCESS, "数据插入成功");
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
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
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
            return MapUtils.buildErrorMap(Constants.SUCCESS, "数据删除成功");
        }else {
            return  MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"数据删除失败");
        }
    }

    @Override
    public Map<String, Object> queryVenue(Venue venue) {
        venue.setPage(venue.getPage()*venue.getPageNumber());
        List<VenueDto> venues = venueMapper.queryVenue(venue);
        int i = venueMapper.selectCountVenue(venue);
        if (venues != null && venues.size() > 0) {
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", venues);
            map.put("dataCount", i);
            return map;
        } else {
            return MapUtils.buildErrorMap(Constants.NO_DATA, "查无数据");
        }
    }
}
