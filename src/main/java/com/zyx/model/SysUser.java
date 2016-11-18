package com.zyx.model;

import javax.persistence.*;

/**
 * 管理员实体类
 */
@Table(name = "SYS_USER")
public class SysUser {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 用户账号
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 用户名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 权限
     */
//    @Column(name = "RIGHTS")
//    private String rights;

    /**
     * 角色id
     */
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     *最后登录时间
     */
    @Column(name = "LAST_LOGIN")
    private String lastLogin;

    /**
     * 登录ip
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 用户状态：0: 正常,1:冻结,2：删除
     */
    @Column(name = "STATUS")
    private String status;
    /**
     * 备注
     */
    @Column(name = "BZ")
    private String bz;

//    @Column(name = "SKIN")
//    private String skin;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

  /*  @Column(name = "NUMBER")
    private String number;*/

    /**
     * 手机号
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

//    /**
//     * @return RIGHTS
//     */
//    public String getRights() {
//        return rights;
//    }
//
//    /**
//     * @param rights
//     */
//    public void setRights(String rights) {
//        this.rights = rights;
//    }

    /**
     * @return ROLE_ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return LAST_LOGIN
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return BZ
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

//    /**
//     * @return SKIN
//     */
//    public String getSkin() {
//        return skin;
//    }
//
//    /**
//     * @param skin
//     */
//    public void setSkin(String skin) {
//        this.skin = skin;
//    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

//    /**
//     * @return NUMBER
//     */
//    public String getNumber() {
//        return number;
//    }
//
//    /**
//     * @param number
//     */
//    public void setNumber(String number) {
//        this.number = number;
//    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}