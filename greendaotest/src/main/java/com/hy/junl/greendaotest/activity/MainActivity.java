package com.hy.junl.greendaotest.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hy.junl.greendaotest.R;
import com.hy.junl.greendaotest.adapter.ShopListAdapter;
import com.hy.junl.greendaotest.bean.Shop;
import com.hy.junl.greendaotest.help.ShopDaoUtil;
import com.hy.junl.greendaotest.util.JudgeNullUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考：http://blog.csdn.net/qq_30379689/article/details/54410838
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_add, bt_delete, bt_update, bt_query,btn_upgrade;
    private ListView lv_content;
    private ShopListAdapter adapter;
    private List<Shop> shopList = new ArrayList<>();
    private static int i = 10;

    ShopDaoUtil shopDaoUtil = new ShopDaoUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        Shop shop = new Shop("梅菜扣肉", "23.5");
//        binding.setShop(shop);
        setContentView(R.layout.activity_main);

        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_query = (Button) findViewById(R.id.bt_query);
        btn_upgrade = (Button) findViewById(R.id.bt_onUpgrade);
        lv_content = (ListView) findViewById(R.id.lv_content);
        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_query.setOnClickListener(this);
        queryShop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                addShop();
                break;
            case R.id.bt_delete:
                deleteShop();
                break;
            case R.id.bt_update:
                updateShop();
                break;
            case R.id.bt_query:
                queryShop();
                if (JudgeNullUtil.iList(shopList)) {
                    Snackbar.make(bt_add, "查询数据条数为："+shopList.size(), 1000).show();
                }
                break;
            case R.id.bt_onUpgrade:
                upGradeVersion();
                break;
        }
    }

    /**
     * 数据库升级
     */
    private void upGradeVersion() {

    }

    private List<Shop> queryShop() {
        shopList.clear();
        shopList =  shopDaoUtil.queryShop();
        adapter = new ShopListAdapter(this, shopList);
        lv_content.setAdapter(adapter);
        return shopList;
    }

    private void updateShop() {
        if (JudgeNullUtil.iList(shopList)) {
            Shop shop = shopList.get(0);
            shop.setName("我是被修改的名字");
            shopDaoUtil.updateShop(shop);
            queryShop();
            Snackbar.make(bt_add, "修改成功", 1000).show();
        }
    }

    private void deleteShop() {
        if (JudgeNullUtil.iList(shopList)) {
            shopDaoUtil.deleteShop(shopList.get(0).getId());
            queryShop();
            Snackbar.make(bt_add, "删除成功", 1000).show();
        }
    }

    private void addShop() {
        Shop shop = new Shop();
        shop.setType(Shop.TYPE_LOVE);
        shop.setAddress("广东深圳");
        shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
        shop.setPrice("19.40");
        shop.setSell_num(15263);
        shop.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮" + i++);
        shopDaoUtil.insert(shop);
        queryShop();
    }
}
