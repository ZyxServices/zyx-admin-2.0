package com.zyx.mapper;

import com.zyx.model.Version;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by HL on 2016/11/15.
 */
public interface VersionMapper extends Mapper<Version>{

    int updateVersion(Version version);

    int delVersion(Integer id);

    List<Version> queryVersion(Version version);

    int selectCountVersion(Version version);
}
