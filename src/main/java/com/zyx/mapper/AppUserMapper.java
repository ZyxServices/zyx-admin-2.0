package com.zyx.mapper;

import com.zyx.dto.OfficialUserListDto;
import com.zyx.dto.RankDto;
import com.zyx.model.AppUser;
import com.zyx.parm.QueryAppUserParam;
import com.zyx.parm.appUser.AppUserCreateParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户管理的持久层接口
 */
@Repository("appUserMapper")
public interface AppUserMapper extends Mapper<AppUser> {

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delByPrimaryKey(Integer id);

//    int unDelByPrimaryKey(Integer id);

    /**
     * 屏蔽
     * @param id
     * @return
     */
    int maskByPrimaryKey(Integer id);

    /**
     * 取消屏蔽
     * @param id
     * @return
     */
    int unMaskByPrimaryKey(Integer id);

    List<String> queryAppUserByName(List<Integer> userId);

    /**
     * 按条件查询用户
     * @param param
     * @return
     */
    List<AppUser> queryAppUserList(QueryAppUserParam param);

    /**
     * 查询出来的数量
     * @param param
     * @return
     */
    int selectAppUserListCount(QueryAppUserParam param);


    List<OfficialUserListDto> queryOfficialAccountList();

    /**
     * 编辑用户
     * @param param
     * @return
     */
    int updateAppUserByPrimaryKey(AppUserCreateParam param);


    // =============认证相关==============
//    int selectAuthCount(Integer user_id);
//
//    int insertAuthInfo(AppUserCreateParam param);
//
//    int updateAuthInfo(AppUserCreateParam param);
//
//    int authAppUserByPrimaryKey(Integer id, int i);
//
    int resetAppUser(AppUserCreateParam param);

    /**
     * 查询用户排名
     * @param id
     * @return
     */
    RankDto getOwnRank(Integer id,Integer appType);


    /**
     * 查询排名前5
     * @return
     */
    List<RankDto> getTopRank(Integer appType);

}