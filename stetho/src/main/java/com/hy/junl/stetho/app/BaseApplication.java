package com.hy.junl.stetho.app;

import com.activeandroid.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Author:Junl(袁军亮)
 * Desc:新建一个App类，继承com.activeandroid.app.Application，重写onCreate方法，在里面完成Stetho的初始化工作。构建一个Builder并进行配置，最后初始化Stetho
 * Date:2017/11/15
 * Version:1.0
 * Foreword:但行好事，莫问前程，只需努力每一天。
 * Contact:15800904094@163.com
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
        Stetho.initializeWithDefaults(this);
    }

}
