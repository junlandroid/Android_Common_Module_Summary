package com.hy.junl.stetho.bean;

import com.activeandroid.Model;

/**
 * Author:Junl(袁军亮)
 * Desc:
 * Date:2017/11/15
 * Version:1.0
 * Foreword:但行好事，莫问前程，只需努力每一天。
 * Contact:15800904094@163.com
 */

public class Person extends Model {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
