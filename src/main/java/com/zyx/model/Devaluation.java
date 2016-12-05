package com.zyx.model;

import javax.persistence.*;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

@Table(name = "devaluation")
public class Devaluation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 模块类型（1、活动.........）
     * //  对面模块类型 1、教程攻略，2求约
     */
    @Column(name = "model")
    private Integer model;

    /**
     * 对应模块首推数据ID
     */
    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "create_time")
    private Long createTime;

    /**
     * 图片地址
     */
    @Column(name = "image_url")
    private String imageUrl;
    /**
     * 首推排序
     */
    @Column(name = "sequence")
    private Integer sequence;

    @Column(name = "state")
    private Integer state;//激活0，未激活1

    /**
     * app类型 1为趣攀岩
     */
    @Column(name = "app_type")
    private Integer appType;

    @Column(name = "area")
    private Integer area;
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
     * 获取模块类型（1、活动.........）
     *
     * @return types - 模块类型（1、活动.........）
     */
    public Integer getModel() {
        return model;
    }

    /**
     * 设置模块类型（1、活动.........）
     *
     * @param model 模块类型（1、活动.........）
     */
    public void setModel(Integer model) {
        this.model = model;
    }

    /**
     * 获取对应模块首推数据ID
     *
     * @return devaluation_id - 对应模块首推数据ID
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * 设置对应模块首推数据ID
     *
     * @param modelId 对应模块首推数据ID
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * @return create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取图片地址
     *
     * @return imageUrl - 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imageUrl 图片地址
     */
    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取首推排序
     *
     * @return sequence - 首推排序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置首推排序
     *
     * @param sequence 首推排序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 推荐位置 1-首页 2-求约
     * @return
     */
    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
    /**
     * 删除（0，正常  1，删除）
     */
    private Integer del;

    public Integer getDel() { return del; }

    public void setDel(Integer del) { this.del = del; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getAppType() { return appType; }

    public void setAppType(Integer appType) { this.appType = appType; }
}