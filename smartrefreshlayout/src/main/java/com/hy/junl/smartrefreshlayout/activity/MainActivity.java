package com.hy.junl.smartrefreshlayout.activity;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.hy.junl.smartrefreshlayout.R;
import com.hy.junl.smartrefreshlayout.fragment.RefreshPracticeFragment;
import com.hy.junl.smartrefreshlayout.fragment.RefreshStyleFragment;
import com.hy.junl.smartrefreshlayout.fragment.RefreshUsingFragment;
import com.hy.junl.smartrefreshlayout.util.StatusBarUtil;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private enum TabFragment {
        practice(R.id.navigation_practice, RefreshPracticeFragment.class),
        styles(R.id.navigation_style,RefreshStyleFragment.class),
        using(R.id.navigation_using,RefreshUsingFragment.class);

        private Fragment fragment;
        private int id;
        private Class<? extends Fragment> clazz;
        TabFragment(@IdRes int ResId, Class<? extends Fragment> clazz) {
            this.id = ResId;
            this.clazz = clazz;
        }

        @NonNull
        public Fragment fragment() {
            if (fragment == null) {
                try {
                    fragment = clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    fragment = new Fragment();
                }
            }
            return fragment;
        }

        public static TabFragment from(int menuId) {
            for (TabFragment fragment : values()) {
                if (fragment.id == menuId) {
                    return fragment;
                }
            }
            return styles;
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_style);//设置默认选中的样式
        navigation.setOnNavigationItemSelectedListener(this);

        //状态栏透明和间距处理
        StatusBarUtil.immersive(this, 0xff0000, 0.5f);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, TabFragment.from(item.getItemId()).fragment())
                .commit();
        return true;
    }
}
