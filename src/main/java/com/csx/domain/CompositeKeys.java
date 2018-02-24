package com.csx.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 复合主键类
 * @Author: csx
 * @Date: 2018/02/24
 */
public class CompositeKeys implements Serializable{
    private String userName;
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
