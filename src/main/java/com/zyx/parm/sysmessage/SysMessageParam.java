package com.zyx.parm.sysmessage;

import com.zyx.parm.QueryParam;

/**
 * Created by HL on 2016/11/18.
 */
public class SysMessageParam extends QueryParam{
    /**
     * 是否发送完成
     */
    private Integer done;

    public Integer getDone() { return done; }

    public void setDone(Integer done) { this.done = done; }
}