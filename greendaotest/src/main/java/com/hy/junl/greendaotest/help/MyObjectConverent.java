package com.hy.junl.greendaotest.help;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hy.junl.greendaotest.bean.Apple;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 版    本：1.0
 * 描    述：
 * <p>
 * 创建日期：2017/11/9
 * 人生如诗：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class MyObjectConverent implements PropertyConverter<List<Apple>,String> {

    @Override
    public List<Apple> convertToEntityProperty(String databaseValue) {

        Type type = new TypeToken<ArrayList<Apple>>() {
        }.getType();
        ArrayList<Apple> itemList= new Gson().fromJson(databaseValue, type);
        return itemList;
    }


    @Override
    public String convertToDatabaseValue(List<Apple> entityProperty) {

        String dbString = new Gson().toJson(entityProperty);
        return dbString;
    }
}
