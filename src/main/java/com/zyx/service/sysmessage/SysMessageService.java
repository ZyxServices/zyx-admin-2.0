package com.zyx.service.sysmessage;

import com.zyx.model.SysMessage;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by HL on 2016/11/14.
 */
public interface SysMessageService extends BaseService<SysMessage>{

    Map<String,Object> insertSysMessage(SysMessage sysMessage);

    Map<String,Object> updateSysMessage(SysMessage sysMessage);

    Map<String,Object> delSysMessage(String ids);

    Map<String,Object> querySysMessage(SysMessage sysMessage);

}
