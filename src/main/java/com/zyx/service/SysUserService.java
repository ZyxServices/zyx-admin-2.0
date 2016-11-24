package com.zyx.service;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.Constants;
import com.zyx.constants.SysConstants;
import com.zyx.dto.OfficialUserListDto;
import com.zyx.dto.SystemUserListDto;
import com.zyx.mapper.AppUserMapper;
import com.zyx.mapper.SysUserMapper;
import com.zyx.model.AppUser;
import com.zyx.model.SysUser;
import com.zyx.parm.sys.CreateSystemUserParam;
import com.zyx.parm.sys.QuerySystemUserParam;
import com.zyx.utils.MapUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by chenkaihua on 15-9-15.
 */
@Service
public class SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    AppUserMapper appUserMapper;


    /**
     * 删除管理员
     * @param id
     */
    @RequiresRoles("admin")
    public Map<String, Object>  deleteWithAdminRoleById(int id) {
        if(sysUserMapper.selectByPrimaryKey(id)==null){
            return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"该id不存在");
        }
       int i =  sysUserMapper.deleteByPrimaryKey(id);
        if(i>0){
            return MapUtils.buildSuccessMap(Constants.SUCCESS,"删除成功",null);
        }else{
            return MapUtils.buildErrorMap(Constants.ERROR_DEL_1001,"删除失败");
        }
    }


    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    public SysUser getUserById(int id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询该管理员是否存在
     * @param user
     * @return
     */
    public boolean isExist(SysUser user) {

        return sysUserMapper.selectCount(user) > 0;
    }

    public int addUser(SysUser user) {
        int result = sysUserMapper.insert(user);
        return result;
    }


    /**
     * 查询所有管理员或者按照条件查询管理员
     * @param user
     * @return
     */
    public List<SysUser> getUsers(SysUser user) {
        if (user == null) {
            return sysUserMapper.selectAll();
        }

        return sysUserMapper.select(user);
    }


    /**
     *
     * @param id
     */
    public void deleteById(int id) {
        sysUserMapper.deleteByPrimaryKey(id);

    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     * @param user
     */
    public void update(SysUser user) {
        sysUserMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据用户名和密码查询管理员
     * @param username
     * @param password
     * @return
     */
    public SysUser getUserByNamePass(String username, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        return sysUserMapper.selectOne(sysUser);
    }

    /**
     * 根据账户查询管理员
     * @param username
     * @return
     */
    public SysUser getUserByName(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        return sysUserMapper.selectOne(sysUser);
    }



    /**
     * 按照条件查询管理员
     * @param param
     * @return
     */
    public Map<String, Object> queryList(QuerySystemUserParam param) {

        List<SystemUserListDto> _list = sysUserMapper.querySystemUserList(param);
        int count = sysUserMapper.querySystemUserListCount(param);
        if(_list!=null && _list.size()>0){
            for(SystemUserListDto user : _list){
                String temp = user.getOfficialId();
                if(temp!=null && !temp.isEmpty()){
                    String[] str = user.getOfficialId().split(",");

                    if(str!=null && str.length>0){
                        List<OfficialUserListDto> userListDtos = new ArrayList<OfficialUserListDto>();
                        for(String userId : str){
                            if(userId!=null && Integer.valueOf(userId)>0){
                                AppUser appUser = appUserMapper.selectByPrimaryKey(Integer.parseInt(userId));
                                OfficialUserListDto dto = new OfficialUserListDto();
                                dto.setId(appUser.getId());
                                dto.setNickname(appUser.getNickname());
                                userListDtos.add(dto);
                            }

                        }

                        user.setOfficialUserListDto(userListDtos);
                    }

                }



            }
            Map<String, Object> map =MapUtils.buildSuccessMap(Constants.SUCCESS,"查询成功",_list);
            map.put("total", count);
            return map;
        }else {
            return MapUtils.buildErrorMap(Constants.NO_DATA, "查无数据");
        }

    }

    /**
     * 创建管理员
     * @param param
     * @return
     */
    public Map<String, Object> insertSysUser(CreateSystemUserParam param) {
        SysUser _user = new SysUser();
        _user.setRoleId(param.getRoleId());
        _user.setPassword(param.getPassword());
        _user.setBz(param.getRemark());
        _user.setName(param.getName());
        _user.setUsername(param.getUserName());
        _user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
        _user.setBz(param.getBz());

        try {
            int result = sysUserMapper.insert(_user);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(SysConstants.ERROR_9005, SysConstants.ERROR_9005_MSG);
            }
        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }
    }

    /**
     * 编辑权限
     * @param param
     * @return
     */
    public Map<String, Object> editSysRole(CreateSystemUserParam param) {
        try {
            int result = sysUserMapper.editSysRole(param);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(SysConstants.ERROR_9006, SysConstants.ERROR_9006_MSG);
            }
        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }
    }


    /**
     * 绑定官方账户
     * @param
     * @return
     */
    public Map<String, Object> addAccount(Integer id,String idStr) {
        try {
            if (idStr != null && !idStr.isEmpty()) {
//                String[] ids = idStr.split(",");
//                int result = 0;
//                for (String userId : ids) {
//                    if (userId != null && Integer.valueOf(userId) > 0) {
                        CreateSystemUserParam param = new CreateSystemUserParam();
                        param.setId(id);
                        param.setOfficialId(idStr);
                int result = sysUserMapper.addAccount(param);
//                        result++;
//                    }

//                }
                if (result >= 1) {
                    return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
                } else {
                    return MapUtils.buildErrorMap(SysConstants.ERROR_9007, SysConstants.ERROR_9007_MSG);
                }

            }else {
                return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数有误");
            }


        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }

    }

    /**
     * 选择官方列表
     * @param idStr
     * @return
     */
    public Map<String, Object> choiceAccount(String idStr) {
        List<AppUser> list = new ArrayList<>();
        try {
            if (idStr != null && !idStr.isEmpty()) {
                String[] ids = idStr.split(",");
                int result = 0;
                for (String userId : ids) {
                    if (userId != null && Integer.valueOf(userId) > 0) {

                        AppUser user  = appUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
                        list.add(user);
                        result++;
                    }

                }
                if (result >= 1) {
                    return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, list);
                } else {
                    return MapUtils.buildErrorMap(SysConstants.ERROR_9007, SysConstants.ERROR_9007_MSG);
                }

            }else {
                return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数有误");
            }


        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }

    }



}
