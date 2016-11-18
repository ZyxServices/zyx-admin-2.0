package com.zyx.controller.sysUser;

import com.zyx.constants.SysConstants;
import com.zyx.jopo.ErrorResponseEntity;
import com.zyx.model.SysUser;
import com.zyx.parm.sys.CreateSystemUserParam;
import com.zyx.parm.sys.QuerySystemUserParam;
import com.zyx.service.SysUserService;
import com.zyx.utils.CipherUtil;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 管理员的业务层方法
 */
@Controller
@RequestMapping("/v1/sysUser")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询管理员，可以模糊查询", notes = "查询管理员，可以模糊查询")
    public ModelAndView users(@ApiParam(name = "pageNumber", required = true, value = "页码")@RequestParam Integer pageNumber,
                              @ApiParam(name = "pageSize", required = true, value = "每页显示数量")@RequestParam Integer pageSize,
                              @ApiParam(name = "searchText", required = false, value = "账号或者管理员名称")@RequestParam(required = false) String searchText) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QuerySystemUserParam param = new QuerySystemUserParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        if(searchText!=null&&searchText!=""){
            param.setSearchText(searchText);
        }

//        param.setSortName(sortName);
//        param.setSortOrder(sortOrder);
        Map<String, Object> map = sysUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "创建管理员", notes = "创建管理员")
    public ModelAndView sysRoleInsert( @ApiParam(name = "userName", required = true, value = "账号")@RequestParam String userName,
                                       @ApiParam(name = "pass", required = true, value = "密码")@RequestParam String pass,
                                       @ApiParam(name = "name", required = true, value = "管理员名称")@RequestParam String name,
                                       @ApiParam(name = "roleId", required = true, value = "权限id")@RequestParam String roleId,
                                       @ApiParam(name = "bz", required = true, value = "备注")@RequestParam(required = true) String bz) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        SysUser sysUser = sysUserService.getUserByName(userName);

        if (sysUser != null) {
            map = MapUtils.buildErrorMap(SysConstants.ERROR_9004, SysConstants.ERROR_9004_MSG);
        } else {
            CreateSystemUserParam param = new CreateSystemUserParam();
            param.setRoleId(roleId);
            param.setName(name);
            param.setUserName(userName);
            param.setPassword(CipherUtil.generatePassword(pass));
            param.setBz(bz);
            map = sysUserService.insertSysUser(param);
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    @ApiOperation(value = "权限变更", notes = "权限变更")
    public ModelAndView sysRoleEdit(@RequestParam Integer id, @ApiParam(name="roleId",required = true,value = "权限id")
                        @RequestParam String roleId) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        CreateSystemUserParam param = new CreateSystemUserParam();
        param.setId(id);
        param.setRoleId(roleId);
        map = sysUserService.editSysRole(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryOperation", method = RequestMethod.GET)
    @ApiOperation(value = "查询操作日志", notes = "查询操作日志")
    public ModelAndView queryOperation(@RequestParam Integer id, @ApiParam(name="userId",required = true,value = "管理员的用户id")
    @RequestParam String userId) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;

//        map = sysUserService.editSysRole(param);
//        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    public ModelAndView deleteById(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String,Object> map = sysUserService.deleteWithAdminRoleById(id);
        jsonView.setAttributesMap(map);

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {APPLICATION_JSON_VALUE})
    @ApiOperation(value = "暂时没用", notes = "暂时没用")
    public ResponseEntity addUser(@RequestBody SysUser user) {
        SysUser countUser = new SysUser();
        countUser.setUsername(user.getUsername());
        user.setUserId("10010");
        int result = sysUserService.addUser(user);
        if (result == 1) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ErrorResponseEntity.buildToResponseEntity(1000, "添加用户失败");

    }


}
