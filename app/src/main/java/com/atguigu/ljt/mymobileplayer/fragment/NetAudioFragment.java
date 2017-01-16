package com.atguigu.ljt.mymobileplayer.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.ljt.mymobileplayer.base.BaseFragment;


/**
 * Created by 李金桐 on 2017/1/6.
 * QQ: 474297694
 * 功能: 网络音频播放的Fragment
 */

public class NetAudioFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("网络音频");

    }
    protected void onRequesData(){
        textView.setText("网络音频刷新");
    };
}
