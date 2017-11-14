package com.hy.junl.module.summary;

import org.junit.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 描    述：
 * <p>
 * 创建日期：2017/10/13
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */
public class TestTest {

    @org.junit.Test
    public void summary() throws Exception {
        System.out.println(createData());
    }

    /**测试数据*/
    private Collection<Model> createData() {
        return Arrays.asList(
                new Model() {{
                    this.name = "Junl";
                    this.age = 27;
                    this.sex = "男";
                }},
                new Model() {{
                    this.name = "军权";
                    this.age = 23;
                    this.sex = "男";
                }}
        );

    }

    class Model {
        String name;
        int age;
        String sex;
    }


}