package com.atguigu.ljt.mymobileplayer.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.ljt.mymobileplayer.R;
import com.atguigu.ljt.mymobileplayer.adapter.RecyclerViewFragmentAdapter;
import com.atguigu.ljt.mymobileplayer.base.BaseFragment;
import com.atguigu.ljt.mymobileplayer.bean.NetAudioBean;
import com.atguigu.ljt.mymobileplayer.util.CacheUtils;
import com.atguigu.ljt.mymobileplayer.util.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 李金桐 on 2017/1/6.
 * QQ: 474297694
 * 功能: 网络视频播放的Fragment
 */

public class NetVideoFragment extends BaseFragment {

    private static final String TAG = NetAudioFragment.class.getSimpleName();
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;

    private List<NetAudioBean.ListBean> datas;
    private RecyclerViewFragmentAdapter myAdapter;

    @Override
    public View initView() {
        Log.e(TAG, "网络音频UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_recyclerview, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e("TAG", "网络视频数据初始化了...");
        setListener();
        String saveJson = CacheUtils.getString(mContext, Constants.NET_AUDIO_URL);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }

        getDataFromNet();
    }

    private void setListener() {

    }

    private void getDataFromNet() {
        RequestParams reques = new RequestParams(Constants.NET_AUDIO_URL);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtils.putString(mContext, Constants.NET_AUDIO_URL, result);
                LogUtil.e("onSuccess==" + result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });
    }

    private void processData(String result) {


        datas = parsedJson(result);
        LogUtil.e(datas.size() + "-----------");
        if(datas != null && datas.size() >0){
            //有视频
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            myAdapter = new RecyclerViewFragmentAdapter(mContext,datas);
            recyclerview.setAdapter(myAdapter);
            LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

            GridLayoutManager gridLayoutManager =  new GridLayoutManager(mContext, 3,GridLayoutManager.VERTICAL, false);

            StaggeredGridLayoutManager staggeredGridLayoutManager =  new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
            recyclerview.setLayoutManager(linearLayoutManager);
        }else{
            //没有视频
            tvNomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);

    }

    /**
     * 使用Gson解析json数据
     *
     * @param json
     * @return
     */
    private List<NetAudioBean.ListBean> parsedJson(String json) {
        NetAudioBean netAudioBean = new Gson().fromJson(json, NetAudioBean.class);
        return netAudioBean.getList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
