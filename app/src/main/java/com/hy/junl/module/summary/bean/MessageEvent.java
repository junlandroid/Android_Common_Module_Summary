package com.hy.junl.module.summary.bean;

import java.io.Serializable;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 *
 * 描    述：Eventbus定义消息事件类
 * <p>
 * 创建日期：2017/10/27
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class MessageEvent implements Serializable{

    private int age;
    private String message;

    public MessageEvent(int age, String message) {
        this.age = age;
        this.message = message;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
