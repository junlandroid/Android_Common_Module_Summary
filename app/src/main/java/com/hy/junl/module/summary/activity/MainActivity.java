package com.hy.junl.module.summary.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hy.junl.module.summary.R;
import com.hy.junl.module.summary.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 主要用来 测试BroadcastRecriver
 * <p>
 * 注册方式：
 * 1、静态注册：常驻广播，
 * a、不受任何组件生命周期的影响（应用程序杀死后，如有信息广播来，程序依旧会被系统调用）
 * b、应用场景：需要时刻监听广播
 * 2、动态注册：非常驻，
 * a、跟随组件的生命周期变动，程序杀死，广播结束，结束前必须移除广播接收器，否则内存泄露。
 * b、应用场景：需要特定时刻监听广播
 * <p>
 * 参考链接：http://www.jianshu.com/p/ca3d87a4cdf3
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BROAD_TEST_TAG = "broad_test_activity";

    TextView tvName, tvAge;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //广播接受者通过消息循环拿到此广播，并回调onReceive()
            MessageEvent messageEvent = (MessageEvent) intent.getExtras().getSerializable("BroadcastTest");
            tvName.setText(messageEvent.getMessage());
            tvAge.setText(messageEvent.getAge() + "");
            Snackbar.make(tvName, "name:" + messageEvent.getMessage() + ",age:" + messageEvent.getAge(), Snackbar.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = (TextView) findViewById(R.id.name);
        tvAge = (TextView) findViewById(R.id.age);

//        //注册订阅事件
        EventBus.getDefault().register(this);
        Log.e("onCreate", Thread.currentThread().getName());

    }

    public void skip2BroadActivity(View view) {
        startActivity(new Intent(MainActivity.this, BroadTestActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        //广播接受者 通过Binder机制在AMS中注册广播
        //动态广播最好在Activity的onResume()注册、onPause()注销
        // 同一个页面 只能通过一种方式注册广播总线 eventbus/broadcastrecever
        IntentFilter intentFilter = new IntentFilter(MainActivity.BROAD_TEST_TAG);//设置接收广播的类型
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 自定义消息处理方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowEventMessage(MessageEvent messageEvent) {
        if (messageEvent != null) {
            tvName.setText(messageEvent.getMessage());
            tvAge.setText(messageEvent.getAge()+"");
            Snackbar.make(tvName, "name:" + messageEvent.getMessage() + ",age:" + messageEvent.getAge(), Snackbar.LENGTH_SHORT).show();
        }
        Log.e("MAIN", Thread.currentThread().getName());
    }

    /**
     * Eventbus 之  从服务器上获取数据
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void requestService() {

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅事件
        EventBus.getDefault().unregister(this);

        //对于动态广播，有注册就必然得有注销，否则会导致内存泄露  重复注册、重复注销也不允许
        unregisterReceiver(receiver);
    }

    /*跳转到权限测试页面*/
    public void skip2TestPermissionActivity(View view) {
        startActivity(new Intent(MainActivity.this, TestPermissionActivity.class));
    }
}
