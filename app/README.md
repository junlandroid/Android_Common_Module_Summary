
## BroadcastReceiver

 注册方式：

    1、静态注册：常驻广播，

       a、不受任何组件生命周期的影响（应用程序杀死后，如有信息广播来，程序依旧会被系统调用）

       b、应用场景：需要时刻监听广播

    2、动态注册：非常驻，

       a、跟随组件的生命周期变动，程序杀死，广播结束，结束前必须移除广播接收器，否则内存泄露。

       b、应用场景：需要特定时刻监听广播

  [BroadcastReceiver参考资料](http://www.jianshu.com/p/ca3d87a4cdf3)

## EventBus3.0

    ### EventBus是针对Android的发布/订阅事件总线，可以轻松的实现各组件之间的消息传递，并且代码可读性更好，耦合性更低。

[EventBus GitHub地址：](https://github.com/greenrobot/EventBus)

    注意点：
        1、注册放在onCreate()方法中。
        2、注销事件监听需要在onDestory,不应该放在onStop()方法中。

[EventBus参考链接](http://www.jianshu.com/p/da9e193e8b03)

[EventBus3.0接入问题](http://www.jianshu.com/p/2d4e435a3413)

## Service


## Android6.0权限封装

