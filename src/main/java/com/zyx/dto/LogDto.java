package com.zyx.dto;

/**
 * Created by HL on 2016/11/17.
 */
public class LogDto {

    private Integer id;
    private String operationDesc;
    private Long createTime;
    private String userName;
    /**
     * 权限等级
     */
    private String authLevel;
    private Integer result;

    public Integer getResult() { return result; }

    public void setResult(Integer result) { this.result = result; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getOperationDesc() { return operationDesc; }

    public void setOperationDesc(String operationDesc) { this.operationDesc = operationDesc; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getAuthLevel() { return authLevel; }

    public void setAuthLevel(String authLevel) { this.authLevel = authLevel; }
}
