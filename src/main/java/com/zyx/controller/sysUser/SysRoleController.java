package com.zyx.controller.sysUser;

import com.zyx.constants.SysConstants;
import com.zyx.model.SysRole;
import com.zyx.parm.sys.CreateSystemRoleParam;
import com.zyx.parm.sys.QuerySystemRoleParam;
import com.zyx.service.SysRoleService;
import com.zyx.utils.MapUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/v2/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value="动态分页查询权限，可以按照权限名称或者权限描述查询",notes="动态分页查询权限，可以按照权限名称或者权限描述查询")
    public ModelAndView sysRoleLists(HttpServletRequest request,
                                     @ApiParam(name="pageSize",required = true,value = "每页显示数量")@RequestParam Integer pageSize,
                                     @ApiParam(name="pageNumber",required = true,value = "页码，从1开始")@RequestParam Integer pageNumber,
                                     @ApiParam(name="searchText",required = false,value = "权限名称模糊查询")
                                     @RequestParam(required = false) String searchText
//                                     @ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType
                                    ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QuerySystemRoleParam param = new QuerySystemRoleParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        param.setSearchText(searchText);
        param.setAppType((Integer) request.getSession().getAttribute("appType"));

        Map<String, Object> map = sysRoleService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value="查询所有权限，用于下拉框列表",notes="查询所有权限，用于下拉框列表")
    public ModelAndView sysRoleAllLists( HttpServletRequest request
//                                         @ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType
    ) {
        AbstractView jsonView = new MappingJackson2JsonView();

        QuerySystemRoleParam param = new QuerySystemRoleParam();
        param.setAppType((Integer) request.getSession().getAttribute("appType"));
        Map<String, Object> map = sysRoleService.queryAllList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 添加权限
     * @param roleName
     * @param roleDesc
     * @param menuPerm
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value="创建权限",notes = "创建权限")
    public ModelAndView sysRoleInsert(HttpServletRequest request,
                                      @ApiParam(name="roleName",required = true,value = "权限名称")@RequestParam String roleName,
                                      @ApiParam(name="roleDesc",required = true,value = "权限描述")@RequestParam String roleDesc,
                                      @ApiParam(name="menuPerm",required = false,value = "权限菜单")@RequestParam(required = false) String menuPerm
//                                      ,@ApiParam(name="appType",required = true,value = "app类型：1趣攀岩")@RequestParam(required = true) Integer appType
    ) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        SysRole sysRole = sysRoleService.selectByRoleName(roleName);

        if (sysRole != null) {
            map = MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9001, SysConstants.ERROR_APP_USER_9001_MSG);
        } else {
            CreateSystemRoleParam param = new CreateSystemRoleParam();
            param.setRoleName(roleName);
            param.setRoleDesc(roleDesc);
            param.setMenuPerm(menuPerm);
            param.setAppType((Integer) request.getSession().getAttribute("appType"));
//            param.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
            map = sysRoleService.insertSysRole(param);
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 编辑权限
     * @param editRoleId
     * @param roleDesc
     * @param menuPerm
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value="编辑权限",notes = "编辑权限")
    public ModelAndView sysRoleEdit(@ApiParam(name="editRoleId",required = true,value = "编辑的权限id")@RequestParam Integer editRoleId,
                                    @ApiParam(name="roleDesc",required = true,value = "权限描述")@RequestParam String roleDesc,
                                    @ApiParam(name="menuPerm",required = true,value = "权限菜单")@RequestParam(required = false) String menuPerm) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        CreateSystemRoleParam param = new CreateSystemRoleParam();
        param.setId(editRoleId);
        param.setRoleDesc(roleDesc);
        param.setMenuPerm(menuPerm);
        map = sysRoleService.editSysRole(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}