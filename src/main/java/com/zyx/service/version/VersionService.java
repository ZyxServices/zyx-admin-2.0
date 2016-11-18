package com.zyx.service.version;

import com.zyx.model.Version;
import com.zyx.parm.version.VersionParam;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by HL on 2016/11/15.
 */
public interface VersionService extends BaseService<Version>{
    /**
     * 添加版本
     * @param version
     * @return
     */
    Map<String,Object> insertVersion(Version version);

    /**
     * 修改版本信息
     * @param version
     * @return
     */
    Map<String,Object> updateVersion(Version version);

    /**
     * 删除版本
     * @param ids
     * @return
     */
    Map<String,Object> delVersion(String ids);

    /**
     * 多条件查询版本
     * @param versionParam
     * @return
     */
    Map<String,Object> queryVersion(VersionParam versionParam);
}
