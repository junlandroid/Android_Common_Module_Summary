package com.hy.junl.androidcustomview;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 增加imageView点击变暗的效果
 * @author lmy
 *
 */
public class MyImageView extends AppCompatImageView {

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setOnTouchListener(VIEW_TOUCH_DARK);
	}
	
	public static final OnTouchListener VIEW_TOUCH_DARK = new OnTouchListener() {  
  	  
		//变暗(三个-50，值越大则效果越深)
        public final float[] BT_SELECTED_DARK = new float[] { 1, 0, 0, 0, -50, 0, 1,  
                0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0 };
        /*
        //变亮
        public final float[] BT_SELECTED_LIGHT = new float[] { 1, 0, 0, 0, 50, 0, 1,  
                0, 0, 50, 0, 0, 1, 0, 50, 0, 0, 0, 1, 0 };
        
        //恢复
        public final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0,  
                1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };  
         */
        @Override  
        public boolean onTouch(View v, MotionEvent event) {  
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ImageView iv = (ImageView) v;
                iv.setColorFilter(new ColorMatrixColorFilter(BT_SELECTED_DARK));
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                ImageView iv = (ImageView) v;  
                iv.clearColorFilter();
            }  
            return true;  //如为false，执行ACTION_DOWN后不再往下执行
        }  
    };  

}
