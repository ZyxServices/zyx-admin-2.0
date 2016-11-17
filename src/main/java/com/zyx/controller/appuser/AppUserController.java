package com.zyx.controller.appuser;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.Constants;
import com.zyx.model.AppUser;
import com.zyx.parm.QueryAppUserParam;
import com.zyx.parm.appUser.AppUserCreateParam;
import com.zyx.service.AppUserService;
import com.zyx.service.deva.DevaService;
import com.zyx.utils.CipherUtil;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * Created by zjx on 2016/11/16.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Controller
@RequestMapping("/v1/appUser")
public class AppUserController {


    @Autowired
    AppUserService appUserService;
    @Autowired
    DevaService devaService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "创建官方账号", notes = "创建官方账户")
    public ModelAndView insert(@ApiParam(name = "phone",required = true,value = "电话，即账号")@RequestParam String phone,
                               @ApiParam(name = "password",required = true,value = "密码")@RequestParam String password,
                               @ApiParam(name = "avatar",required = true,value = "头像") @RequestPart(required = false) MultipartFile avatar,
                               @ApiParam(name = "nickname",required = true,value = "昵称")@RequestParam String nickname,
                               @ApiParam(name = "sex",required = true,value = "性别:1男、0女")@RequestParam String sex,
                               @ApiParam(name = "birthday",required = true,value = "生日")@RequestParam long birthday,
                               @ApiParam(name = "address",required = true,value = "地址")@RequestParam String address,
                               @ApiParam(name = "signature",required = true,value = "签名")@RequestParam String signature) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        try {
            if (appUserService.selectByPhone(phone) != null) {
                map = MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5001, AppUserConstants.ERROR_APP_USER_5001_MSG);
            } else {
                AppUserCreateParam param = new AppUserCreateParam();
                param.setPhone(phone);
                param.setAddress(address);
                param.setSex(Integer.parseInt(sex));
                param.setNickname(nickname);
                param.setPassword(CipherUtil.generatePassword(password));
                if (!avatar.isEmpty()) {
                    String _avatar = FileUploadUtils.uploadFile(avatar);
                    param.setAvatar(_avatar);
                }
                map = appUserService.insertAppUser(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = Constants.MAP_500;
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "APP用户接口-编辑官方用户", notes = "APP用户接口-编辑官方用户")
    public ModelAndView update(@RequestParam Integer id,
                               @ApiParam(name = "phone",required = true,value = "电话，即账号")@RequestParam String phone,
                               @ApiParam(name = "password",required = false,value = "密码")@RequestParam(required = false) String password,
                               @ApiParam(name = "avatar",required = false,value = "头像")@RequestPart(required = false) MultipartFile avatar,
                               @ApiParam(name = "nickname",required = false,value = "昵称")@RequestParam(required = false) String nickname,
                               @ApiParam(name = "sex",required = false,value = "性别:1男、0女")@RequestParam(required = false) String sex,
                               @ApiParam(name = "address",required = false,value = "地址") @RequestParam(required = false) String address,
                               @ApiParam(name = "signature",required = false,value = "签名")@RequestParam(required = false) String signature
                               ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        AppUserCreateParam param = new AppUserCreateParam();

        Map<String, Object> map;
        if(phone!=null && phone!=""){
            AppUser appUser = appUserService.selectByPhone(phone);
            if (appUser != null && !appUser.getId().equals(id)) {
                map = MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5001, AppUserConstants.ERROR_APP_USER_5001_MSG);
            }else {
                param.setPhone(phone);
            }
        }



            param.setAppUserId(id);
            param.setAddress(address);
            if (sex != null) {
                param.setSex(Integer.parseInt(sex));
            }
            param.setNickname(nickname);
            if (password != null) {
                param.setPassword(CipherUtil.generatePassword(password));
            }
            if (avatar != null && !avatar.isEmpty()) {
                String _avatar = FileUploadUtils.uploadFile(avatar);
                param.setAvatar(_avatar);
            }
            param.setModifyTime(System.currentTimeMillis());
//            param.setAuthInfo(authInfo);
//            param.setAuthFile(authFile);
            map = appUserService.updateAppUser(param);


        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    @ApiOperation(value = "动态查询用户", notes = "动态查询用户")
    public ModelAndView queryUser(@ApiParam(name="page",required = true,value = "页码:从1开始")@RequestParam Integer page,
                                  @ApiParam(name="official",required = true,value = "是否官方：0用户、1官方")@RequestParam Integer official,
                             @ApiParam(name="pageNumber",required = true,value = "每页显示数量")@RequestParam Integer pageNumber,
                             @ApiParam(name="searchText",required = false,value = "此为用户昵称")@RequestParam(required = false) String searchText,
                             @ApiParam(name="sortName",required = false,value = "排序字段：可以是id、birthday生日、lastLoginTime最后一次登录日期、create_time创建日期、money攀岩币") @RequestParam(required = false) String sortName,
                             @ApiParam(name="sortOrder",required = false,value = "排序规则：desc倒序、asc正序")@RequestParam(required = false) String sortOrder) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QueryAppUserParam param = new QueryAppUserParam();
        param.setPageSize(pageNumber);
        if(page>0){
            param.setPageNumber((page - 1) * pageNumber);
        }else {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_ERROR,"页码不能小于1"));
            return new ModelAndView(jsonView);
        }

        param.setSearchText(searchText);
        param.setSortName(sortName);
        param.setSortOrder(sortOrder);
        param.setOfficial(official);
        Map<String, Object> map = appUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public ModelAndView del(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.del(id);
//        devaService.cascadeDelete(Constants.MODEL_USER,id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/mask", method = RequestMethod.GET)
    @ApiOperation(value = "屏蔽用户", notes = "屏蔽用户")
    public ModelAndView mask(@RequestParam Integer id,
                             @ApiParam(name="mask",required = true,value = "屏蔽状态：0取消屏蔽、1屏蔽")
                             @RequestParam Integer mask) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashedMap();
        if(1==mask){
            map = appUserService.mask(id);
        }else if(0==mask){
            map = appUserService.unMask(id);
        }

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }




}
