package com.zyx.mapper;

import com.zyx.dto.SystemUserListDto;
import com.zyx.model.SysUser;
import com.zyx.parm.sys.CreateSystemUserParam;
import com.zyx.parm.sys.QuerySystemUserParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("sysUserMapper")
public interface SysUserMapper extends Mapper<SysUser> {
    /**
     * 查询所有管理员
     * @param param
     * @return
     */
    List<SystemUserListDto> querySystemUserList(QuerySystemUserParam param);

    /**
     *
     * @param param
     * @return
     */
    int querySystemUserListCount(QuerySystemUserParam param);

    /**
     * 修改管理员权限
     * @param param
     * @return
     */
    int editSysRole(CreateSystemUserParam param);

    /**
     * 给管理员绑定官方账号
     * @param param
     * @return
     */
    int addAccount(CreateSystemUserParam param);


}