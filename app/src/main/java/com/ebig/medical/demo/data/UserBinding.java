package com.ebig.medical.demo.data;

import androidx.annotation.NonNull;


public class UserBinding {
    @NonNull

    private long id;//ID

    private long user_id;//用户id
    private String userName;//账号
    private String bindType;//绑定类型
    private String bindData;//绑定数据
    private String bindLink;//链接信息；如图片地址
    private String createTime;//创建时间
    private String ext1;//扩展一
    private String ext2;//扩展二
    private String ext3;//扩展三

    public UserBinding() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getBindData() {
        return bindData;
    }

    public void setBindData(String bindData) {
        this.bindData = bindData;
    }

    public String getBindLink() {
        return bindLink;
    }

    public void setBindLink(String bindLink) {
        this.bindLink = bindLink;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}
