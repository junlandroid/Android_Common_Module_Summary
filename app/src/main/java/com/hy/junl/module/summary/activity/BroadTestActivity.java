package com.hy.junl.module.summary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hy.junl.module.summary.R;
import com.hy.junl.module.summary.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 描    述：广播发送者，通过Binder机制向AMS(Activity Message Service) 中发送广播
 * <p>
 * 创建日期：2017/10/27
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class BroadTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadtest);
    }

    public void btnBroadcastTest(View view) {
        Intent intent = new Intent(MainActivity.BROAD_TEST_TAG);
        Bundle bundle = new Bundle();
        //传递object对象  参考：http://blog.csdn.net/wirelessqa/article/details/8589200
        bundle.putSerializable("BroadcastTest",new MessageEvent(100,"这是一条通过BroadcastReceiver广播机制传递的消息"));
        intent.putExtras(bundle);
        sendBroadcast(intent);
        finish();
    }

    public void btnEventBusTest(View view) {
        //发布消息
        EventBus.getDefault().post(new MessageEvent(100,"这是一条通过eventbus广播机制传递的消息"));
        finish();
    }
}
