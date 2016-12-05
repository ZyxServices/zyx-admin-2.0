package com.zyx.controller.deva;

import com.zyx.constants.Constants;
import com.zyx.constants.DevaContants;
import com.zyx.constants.LiveConstants;
import com.zyx.model.Devaluation;
import com.zyx.service.activity.ActivityService;
import com.zyx.service.course.CourseService;
import com.zyx.service.deva.DevaService;
import com.zyx.vo.deva.DevaVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.required;

@Controller
@RequestMapping("/v2/deva")
@Api(description = "首推相关接口")
public class DevaController {
    @Autowired
    ActivityService activityService;
    @Autowired
    CourseService courseService;
//    @Autowired
//    RedisTemplate<String, List<Devaluation>> innerDevaTemplate;
//    @Autowired
//    RedisTemplate<String, List<Integer>> innerDevaIdTemplate;
    @Resource
    private DevaService devaService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "首推接口-新增首推", notes = "首推接口-新增首推，同模块同展示区域 模块ID唯一")
    public ModelAndView queryActivity(
            @ApiParam(required = true, name = "model", value = "模块类型（1教程攻略，2求约）") @RequestParam(name = "model", required = true) Integer model,
            @ApiParam(required = true, name = "modelId", value = "模块ID") @RequestParam(name = "modelId", required = true) Integer modelId,
            @ApiParam(required = true, name = "area", value = "展示区域（首页，2求约）") @RequestParam(name = "area", required = true) Integer area,
            @ApiParam(required = false, name = "imageUrl", value = "图片地址") @RequestParam(name = "imageUrl", required = false) String imageUrl,
            @ApiParam(required = true, name = "state", value = "状态（0-未激活，1-激活）") @RequestParam(name = "state", required = true) Integer state,
            @ApiParam(required = true, name = "sequence", value = "展示顺序") @RequestParam(name = "sequence", required = true) Integer sequence,
            @ApiParam(required = true, name = "appType", value = "app类型 1、趣攀岩") @RequestParam(name = "appType", required = true) Integer appType
    ) {
        Map<String, Object> result = new HashMap<>();
        Integer sSize = DevaContants.DEVA_AREA_MAX_ITEM.get(appType+"_"+area);
        if (sSize == null) {
            result.put(Constants.STATE, DevaContants.DEVA_NOT_EXIST_MODEL_AREA);
            result.put(Constants.ERROR_MSG, DevaContants.MSG_DEVA_NOT_EXIST_MODEL_AREA);
        } else {
            //Redis判断目前条数是否满
            Devaluation entity = new Devaluation();
            //2.0版本为区域限定个数
            //entity.setModel(model);
            entity.setArea(area);
            entity.setAppType(appType);
            entity.setDel(0);
            entity.setModelId(modelId);
            List<Devaluation> dblist = devaService.select(entity);
            if (dblist != null && !dblist.isEmpty()) {
                result.put(Constants.STATE, DevaContants.DEVA_REPEAT);
                result.put(Constants.ERROR_MSG, DevaContants.MSG_DEVA_REPEAT);
            } else {
                entity.setModel(model);
                entity.setCreateTime(System.currentTimeMillis());
                entity.setArea(area);
                entity.setImage(imageUrl);
                entity.setSequence(sequence);
                entity.setState(state);
                entity.setDel(0);
                int n = devaService.save(entity);
                //2.0版本为区域限定个数
                refreshDevas(area, null,appType);
                result.put(Constants.STATE, LiveConstants.SUCCESS);
            }
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "首推接口-更新首推", notes = "首推接口-更新首推")
    public ModelAndView updateDeva(
            @ApiParam(required = true, name = "id", value = "推荐ID") @RequestParam(name = "id", required = true) Integer id,
            @ApiParam(required = false, name = "area", value = "展示区域（1首页，2求约）") @RequestParam(name = "area", required = false) Integer area,
            @ApiParam(required = false, name = "imageUrl", value = "图片地址") @RequestParam(name = "imageUrl", required = false) String imageUrl,
            @ApiParam(required = true, name = "state", value = "状态（0-未激活，1-激活）") @RequestParam(name = "state", required = true) Integer state,
            @ApiParam(required = true, name = "appType", value = "app类型（1-趣攀岩）") @RequestParam(name = "appType", required = true) Integer appType,
            @ApiParam(required = true, name = "sequence", value = "展示顺序") @RequestParam(name = "sequence", required = true) Integer sequence) {

        Map<String, Object> result = new HashMap<>();
        if (id == null) {
            result.put(LiveConstants.STATE, LiveConstants.PARAM_MISS);
            result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_MISS);
        } else {
            //判断目前条数是否满
            Devaluation deva = devaService.selectByKey(id);
            if (deva != null) {
                Devaluation entity = new Devaluation();
                entity.setId(id);
                entity.setArea(area);
                entity.setImage(imageUrl);
                entity.setSequence(sequence);
                entity.setState(state);
                devaService.updateNotNull(entity);
                //2.0版本是展示区域限定个数
                refreshDevas(deva.getArea(), null,appType);
            }
        }
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "首推接口-删除首推", notes = "首推接口-删除首推")
    public ModelAndView deleteDeva(@ApiParam(required = true, name = "id", value = "推荐ID") @RequestParam(name = "id", required = true) Integer id
    ) {
        Map<String, Object> result = new HashMap<>();
        Devaluation deva = devaService.selectByKey(id);
        if (deva != null) {
            devaService.delDeval(id);
//            refreshDevas(deva.getArea(), deva.getModel());
            result.put(Constants.STATE, LiveConstants.SUCCESS);
        } else {
            result.put(Constants.STATE, LiveConstants.ERROR);
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    @ApiOperation(value = "首推接口-撤销、显示",notes = "首推接口-撤销、显示")
    public ModelAndView cancel(@ApiParam(name = "id",required = true,value = "主键id")@RequestParam(name = "id",required = true)Integer id,
                               @ApiParam(name = "state",required = true,value = "0-撤销 1-显示")@RequestParam(name = "state",required = true)Integer state
                                ){
        AbstractView jsonView = new MappingJackson2JsonView();
        Devaluation devaluation = new Devaluation();
        devaluation.setId(id);
        devaluation.setState(state);
        Map<String,Object> result = devaService.cancel(devaluation);
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "首推接口-获取首推", notes = "首推接口-获取首推")
    public ModelAndView getDevasByArea(
            @ApiParam(required = true, name = "appType", value = "app类型 1、趣攀岩") @RequestParam(name = "appType", required = true) Integer appType,
            @ApiParam(required = false, name = "area", value = "展示区域（1首页，2求约）") @RequestParam(name = "area", required = false) Integer area) {
        Map<String, Object> result = new HashMap<>();
        //2.0版本是区域限定个数
        List<DevaVo> list = devaService.getDevaList(null, area,appType);
        result.put(Constants.DATA, list);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/sequence", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "首推接口-获取未使用的顺序列表", notes = "首推接口-获取未使用的顺序列表")
    public ModelAndView getUnusedSquen(
            @ApiParam(required = true, name = "appType", value = "app类型（1趣攀岩）") @RequestParam(name = "appType", required = true) Integer appType,
            @ApiParam(required = true, name = "area", value = "展示区域（1首页，2求约）") @RequestParam(name = "area", required = true) Integer area) {
        Map<String, Object> result = new HashMap<>();
        Integer sSize = DevaContants.DEVA_AREA_MAX_ITEM.get(appType+"_"+area);

        if (sSize == null) {
            result.put(Constants.STATE, DevaContants.DEVA_NOT_EXIST_MODEL_AREA);
            result.put(Constants.ERROR_MSG, DevaContants.DEVA_NOT_EXIST_MODEL_AREA);
        } else {
            List<Integer> list = devaService.getUsedSequence(null,area,appType);
            List<Integer> seqList = new ArrayList<>();
            int i = 1;
            if (list != null && !list.isEmpty()) {
                for (Integer temp : list) {
                    if (temp != null) {
                        for (; i < temp; i++) {
                            seqList.add(i);
                        }
                        i++;
                    }
                }
            }
            for (; i <= sSize; i++) {
                seqList.add(i);
            }
            result.put(Constants.DATA, seqList);
            result.put(Constants.STATE, LiveConstants.SUCCESS);
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    private List refreshDevas(Integer area, Integer model,Integer appType) {
        List<Integer> ids = devaService.selectModelIds(area, model,appType);
        List list = null;
        if (null != ids && !ids.isEmpty()) {
            //2.0版本为区域数量限定
            switch (area) {
                case Constants.MODULE_ACTIVITY:
                    list = activityService.selectByIds(ids);
                    break;
                case Constants.MODULE_ARTICLE:
                    //TODO 换成教程攻略的service
                    list = courseService.selectByIds(ids);
                    break;
            }
        }
//        innerDevaTemplate.delete(SystemConstants.MAKE_REDIS_INNER_DEVA + area + ":" + model);
//        innerDevaIdTemplate.delete(SystemConstants.MAKE_REDIS_INNER_DEVA_ID + area + ":" + model);
        return list;
    }

}
