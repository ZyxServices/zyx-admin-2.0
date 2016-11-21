package com.zyx.service;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.Constants;
import com.zyx.constants.SysConstants;
import com.zyx.dto.SystemRoleListDto;
import com.zyx.mapper.SysRoleMapper;
import com.zyx.model.SysRole;
import com.zyx.parm.sys.CreateSystemRoleParam;
import com.zyx.parm.sys.QuerySystemRoleParam;
import com.zyx.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 权限业务层方法
 */
@Service
public class SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    /**
     * 分页查询
     * @param param
     * @return
     */
    public Map<String, Object> queryList(QuerySystemRoleParam param) {

            List<SysRole> _list = sysRoleMapper.querySystemRoleList(param);
            int count = sysRoleMapper.querySystemRoleListCount(param);
        if(_list!=null&& _list.size()>0){
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",_list);
            map.put("total", count);
            return map;
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }

    }

    public SysRole selectByRoleName(String roleName) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        return sysRoleMapper.selectOne(sysRole);
    }

    public SysRole selectByRoleId(String roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setId(Integer.valueOf(roleId));
        return sysRoleMapper.selectOne(sysRole);
    }

    /**
     * 添加权限
     * @param param
     * @return
     */
    public Map<String, Object> insertSysRole(CreateSystemRoleParam param) {
        SysRole _role = new SysRole();
//        _role.setRoleId(param.getRoleId());
        _role.setRoleName(param.getRoleName());
        _role.setRoleDesc(param.getRoleDesc());
        _role.setMenuPerm(param.getMenuPerm());

            int result = sysRoleMapper.insert(_role);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9002, SysConstants.ERROR_APP_USER_9002_MSG);
            }

    }

    /**
     * 编辑权限
     * @param param
     * @return
     */
    public Map<String, Object> editSysRole(CreateSystemRoleParam param) {
        try {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(param.getId());
            if (sysRole == null) {
                return MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9003, SysConstants.ERROR_APP_USER_9003_MSG);
            } else {
                sysRole.setRoleDesc(param.getRoleDesc());
                sysRole.setMenuPerm(param.getMenuPerm());
                int result = sysRoleMapper.updateByPrimaryKey(sysRole);
                if (result >= 1) {
                    return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
                } else {
                    return MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9003, SysConstants.ERROR_APP_USER_9003_MSG);
                }
            }
        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }
    }

    /**
     * 查询所有权限名称
     * @return
     */
    public  Map<String, Object>  queryAllList() {
        List<SystemRoleListDto> _list = sysRoleMapper.queryAllSystemRole();

        if(_list!=null&& _list.size()>0){
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",_list);
            return map;
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"没有数据");
        }
    }
}
