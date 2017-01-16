package com.atguigu.ljt.mymobileplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.ljt.mymobileplayer.R;


/**
 * Created by 李金桐 on 2017/1/6.
 * QQ: 474297694
 * 功能: TitleBarView
 * 标题栏的布局 用于初始化各个子空间的监听时间
 */

public class TitleBarView extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private TextView tv_sousuokuang;
    private RelativeLayout rl_name;
    private ImageView iv_record;

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 此方法会在子控件加载成功后调用
     * 获得对应的子控件然后保存起来 用于设置事件监听器
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_sousuokuang = (TextView) getChildAt(1);
        rl_name = (RelativeLayout) getChildAt(2);
        iv_record = (ImageView) getChildAt(3);
        tv_sousuokuang.setOnClickListener(this);
        rl_name.setOnClickListener(this);
        iv_record.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sousuokuang:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
//                mContext.startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.rl_name:
                Toast.makeText(mContext, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_record:
                Toast.makeText(mContext, "历史记录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
