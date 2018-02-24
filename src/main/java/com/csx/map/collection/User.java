package com.csx.map.collection;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class User implements Serializable{
    private int userId;
    private String userName;
    /**一个用户，对应的多个地址*/
    private Set<String> address;
    private List<String> addressList = new ArrayList<String>();
    /**
     * // 映射方式和list一样     <array name=""></array>
     * private String[] addressArray;
     */
    private Map<String,String> addressMap = new HashMap<String, String>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getAddress() {
        return address;
    }

    public void setAddress(Set<String> address) {
        this.address = address;
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    public Map<String, String> getAddressMap() {
        return addressMap;
    }

    public void setAddressMap(Map<String, String> addressMap) {
        this.addressMap = addressMap;
    }
}
