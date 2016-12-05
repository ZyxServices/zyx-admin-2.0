package com.zyx.mapper;

import com.zyx.dto.SystemRoleListDto;
import com.zyx.model.SysRole;
import com.zyx.parm.sys.QuerySystemRoleParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("sysRoleMapper")
public interface SysRoleMapper extends Mapper<SysRole> {

    /**
     * 动态条件分页查询权限
     * @param param
     * @return
     */
    List<SysRole> querySystemRoleList(QuerySystemRoleParam param);

    /**
     * 查询数目
     * @param param
     * @return
     */
    int querySystemRoleListCount(QuerySystemRoleParam param);

    /**
     *查询所有权限
     * @return
     */
    List<SystemRoleListDto> queryAllSystemRole(QuerySystemRoleParam param);
}