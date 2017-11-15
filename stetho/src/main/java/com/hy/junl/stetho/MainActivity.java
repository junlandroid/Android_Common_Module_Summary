package com.hy.junl.stetho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hy.junl.stetho.bean.Person;
import com.jayfeng.lesscode.core.SharedPreferenceLess;
import com.jayfeng.lesscode.core.ToastLess;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//    public void requestService(View view) {
//        ToastLess.$("save to SP successfully !");
//    }
//
//    public void write2SP(View view) {
//        SharedPreferenceLess.$put("name", "StethoDemo");
//        SharedPreferenceLess.$put("version", "1.0.0");
//        ToastLess.$("save to SP successfully !");
//    }
//
//
//    public void write2DB(View view) {
//        wirte2DB();
//    }
//
//    private void wirte2DB() {
//        Person person = new Person();
//        person.setName("张三");
//        person.setAge(19);
//        person.save();//保存
//
//        ToastLess.$(this,"Save to Sqlite Successfully !");
//    }


}
