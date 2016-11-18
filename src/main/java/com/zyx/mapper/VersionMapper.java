package com.zyx.mapper;

import com.zyx.model.Version;
import com.zyx.parm.version.VersionParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by HL on 2016/11/15.
 */
@Repository("versionMapper")
public interface VersionMapper extends Mapper<Version>{

    int updateVersion(Version version);

    int delVersion(Integer id);

    List<Version> queryVersion(VersionParam versionParam);

    int selectCountVersion(VersionParam versionParam);
}
