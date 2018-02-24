package com.csx.map.one2one;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class IdCard {
    // 身份证号(主键)
    private String cardNum;// 对象唯一表示(Object Identified, OID)
    private String place; //  身份证地址
    /**身份证与用户，一对一的关系*/
    private User user;

}
