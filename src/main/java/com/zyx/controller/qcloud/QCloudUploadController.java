package com.zyx.controller.qcloud;

import com.zyx.constants.Constants;
import com.zyx.qcloud.VodDemo;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HL on 2016/11/24.
 */

@Controller
@RequestMapping("/v2/qcloud")
public class QCloudUploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ModelAndView upload(@RequestPart(name = "file") MultipartFile file){
        AbstractView jsonView = new MappingJackson2JsonView();
        String fileName = file.getOriginalFilename();
        CommonsMultipartFile cf= (CommonsMultipartFile)file;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File f = fi.getStoreLocation();
        VodDemo vodDemo = new VodDemo();
        String fileId = vodDemo.upload(f.getAbsolutePath(),fileName);
        Map<String,Object> map = new HashMap<String,Object>();
        if (StringUtils.isNotEmpty(fileId)){
            map.put(Constants.STATE, Constants.SUCCESS);
            map.put(Constants.SUCCESS_MSG, "视频上传成功");
            Map<String, Object> map2 = new HashMap<>();
            map2.put("url", fileId);
            map.put(Constants.DATA, map2);
            jsonView.setAttributesMap(map);
        }else {
            map.put(Constants.STATE, Constants.ERROR);
            map.put(Constants.ERROR_MSG, "视频上传失败");
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
