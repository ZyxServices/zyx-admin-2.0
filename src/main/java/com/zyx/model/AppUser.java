package com.zyx.model;

import javax.persistence.*;

/**
 * 用户管理实体类
 */
@Table(name = "user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户生日
     */
    private Long birthday;

    /**
     * 最后登录时间
     */
    @Column(name = "lastLoginTime")
    private Long lastlogintime;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 1 普通用户 0大咖
     */
    private Integer type;

    /**
     * 1男 0女
     */
    private Integer sex;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 是否认证字段
     */
    private Integer authenticate;

    /**
     * 屏蔽:0正常、1屏蔽
     */
    private int mask;

    /**
     * 是否删除:0正常、1删除
     */
    private int del;

    /**
     * 签名档
     */
    private String signature;

    /**
     * 官方字段
     */
    private Integer official;

    /**
     * 用户等级id
     */
    @Column(name="level_id")
    private int levelId;
    /**
     * 用户等级名称
     */
    @Transient
    private String level;

    /**
     * 攀岩币
     */
    private Integer money;


    /*******************************   get/set方法  ********************************************/

    /**
     * @return id
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
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户头像地址
     *
     * @return avatar - 用户头像地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像地址
     *
     * @param avatar 用户头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return birthday
     */
    public Long getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取最后登录时间
     *
     * @return lastLoginTime - 最后登录时间
     */
    public Long getLastlogintime() {
        return lastlogintime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastlogintime 最后登录时间
     */
    public void setLastlogintime(Long lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /**
     * 获取身份证
     *
     * @return idcard - 身份证
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证
     *
     * @param idcard 身份证
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取1 普通用户 0大咖
     *
     * @return type - 1 普通用户 0大咖
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1 普通用户 0大咖
     *
     * @param type 1 普通用户 0大咖
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取1男 0女
     *
     * @return sex - 1男 0女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置1男 0女
     *
     * @param sex 1男 0女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否认证字段
     *
     * @return authenticate - 是否认证字段0未认证，1待审核，2已认证，3认证失败
     */
    public Integer getAuthenticate() {
        return authenticate;
    }

    /**
     * 设置是否认证字段
     *
     * @param authenticate 是否认证字段
     */
    public void setAuthenticate(Integer authenticate) {
        this.authenticate = authenticate;
    }

    /**
     * 获取是否屏蔽
     *
     * @return mask - 是否屏蔽
     */
    public int getMask() {
        return mask;
    }

    /**
     * 设置是否屏蔽
     *
     * @param mask 是否屏蔽
     */
    public void setMask(int mask) {
        this.mask = mask;
    }

    /**
     * 获取是否删除
     *
     * @return del - 是否删除
     */
    public int getDel() {
        return del;
    }

    /**
     * 设置是否删除
     *
     * @param del 是否删除
     */
    public void setDel(int del) {
        this.del = del;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 获取是否官方字段
     *
     * @return authenticate - 是否认证字段0普通，1官方
     */
    public Integer getOfficial() {
        return official;
    }

    public void setOfficial(Integer official) {
        this.official = official;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}