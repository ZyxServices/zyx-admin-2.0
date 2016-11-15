package com.zyx.mapper;

import com.zyx.dto.ActivityDto;
import com.zyx.dto.SysMessageDto;
import com.zyx.model.Activity;
import com.zyx.model.SysMessage;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

@Repository("sysMessageMapper")
public interface SysMessageMapper extends Mapper<SysMessage> {

    /**
     * 多条条件查询活动
     *
     * @param sysMessage
     * @return
     */
    List<SysMessageDto> querySysMessage(SysMessage sysMessage);

    /**
     * 修改活动
     *
     * @param sysMessage
     * @return
     */
    int updateSysMessage(SysMessage sysMessage);

    /**
     *
     */
    int selectCountSysMessage(SysMessage sysMessage);

    /**
     *
     * @param id
     * @return
     */
    int delSysMessage(Integer id);


    /**
     * 插入数据并返回主键id
     */
    Integer insertSysMessage(SysMessage sysMessage);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    SysMessageDto queryById(Integer id);
}