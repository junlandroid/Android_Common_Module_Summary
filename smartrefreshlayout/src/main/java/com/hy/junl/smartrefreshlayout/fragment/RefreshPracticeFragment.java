package com.hy.junl.smartrefreshlayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hy.junl.smartrefreshlayout.R;
import com.hy.junl.smartrefreshlayout.activity.BannerPracticeActivity;
import com.hy.junl.smartrefreshlayout.adapter.BaseRecyclerAdapter;
import com.hy.junl.smartrefreshlayout.adapter.SmartViewHolder;
import com.hy.junl.smartrefreshlayout.util.StatusBarUtil;

import java.util.Arrays;

import static android.R.layout.simple_list_item_2;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 描    述：
 * <p>
 * 创建日期：2017/10/13
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class RefreshPracticeFragment extends Fragment implements AdapterView.OnItemClickListener{

    private enum Item {

        Banner("滚动广告-Banner", BannerPracticeActivity.class);

        public String title;
        public Class<?> clazz;

        Item(String title, Class<?> clazz) {
            this.title = title;
            this.clazz = clazz;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_using_basic,container,false);
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        StatusBarUtil.setPaddingSmart(getContext(), root.findViewById(R.id.toolbar));
        View view = root.findViewById(R.id.recyclerView);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()),simple_list_item_2,this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                    holder.text(android.R.id.text1, model.name());
                    holder.text(android.R.id.text2, model.title);
                    holder.textColorId(android.R.id.text1, android.R.color.holo_blue_dark);
                    holder.textColorId(android.R.id.text2, android.R.color.holo_red_dark);
                }
            });
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        startActivity(new Intent(getContext(),Item.values()[position].clazz));
    }
}
