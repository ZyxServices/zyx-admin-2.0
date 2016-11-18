package com.zyx.controller.log;

import com.zyx.model.Log;
import com.zyx.parm.log.LogParam;
import com.zyx.service.log.LogService;
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

import java.util.Map;

/**
 * Created by HL on 2016/11/17.
 */
@Controller
@RequestMapping("/v2/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/queryLog",method = RequestMethod.POST)
    @ApiOperation(value = "日志查看",notes = "日志查看")
    public ModelAndView queryLog(@ApiParam(name = "page",required = true,value = "页码 从0开始")@RequestParam(name = "page",required = true)Integer page,
                                 @ApiParam(name = "pageNumber",required = true,value = "每页数量")@RequestParam(name = "pageNumber",required = true)Integer pageNumber){
        AbstractView jsonView = new MappingJackson2JsonView();
        LogParam log = new LogParam();
        log.setPageSize(pageNumber);
        log.setPageNumber(page);
        Map<String, Object> map = logService.queryLog(log);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
