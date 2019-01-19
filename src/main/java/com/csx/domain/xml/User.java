package com.csx.domain.xml;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */

public class User implements Serializable {
    private CompositeKeys keys;
    private int age;

    public CompositeKeys getKeys() {
        return keys;
    }

    public void setKeys(CompositeKeys keys) {
        this.keys = keys;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
