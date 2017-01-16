package com.atguigu.ljt.mymobileplayer.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.ljt.mymobileplayer.R;
import com.atguigu.ljt.mymobileplayer.bean.NetAudioBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by 李金桐 on 2017/1/16.
 * QQ: 474297694
 * 功能: ViewHolder基类
 */

public class BaseViewHolder {
    ImageView ivHeadpic;
    TextView tvName;
    TextView tvTimeRefresh;
    ImageView ivRightMore;
    ImageView ivVideoKind;
    TextView tvVideoKindText;
    TextView tvShenheDingNumber;
    TextView tvShenheCaiNumber;
    TextView tvPostsNumber;
    LinearLayout llDownload;

    public BaseViewHolder (View convertView) {
        //公共的
        ivHeadpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
        tvName = (TextView) convertView.findViewById(R.id.tv_name);
        tvTimeRefresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
        ivRightMore = (ImageView) convertView.findViewById(R.id.iv_right_more);
        //bottom
        ivVideoKind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
        tvVideoKindText = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
        tvShenheDingNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
        tvShenheCaiNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
        tvPostsNumber = (TextView) convertView.findViewById(R.id.tv_posts_number);
        llDownload = (LinearLayout) convertView.findViewById(R.id.ll_download);
    }
    /**
     * 设置公共的数据
     *
     * @param mediaItem
     */
    public void setData(NetAudioBean.ListBean mediaItem) {
        if (mediaItem.getU() != null && mediaItem.getU().getHeader() != null && mediaItem.getU().getHeader().get(0) != null) {
            x.image().bind(ivHeadpic, mediaItem.getU().getHeader().get(0));
        }
        if (mediaItem.getU() != null && mediaItem.getU().getName() != null) {
            tvName.setText(mediaItem.getU().getName() + "");
        }

        tvTimeRefresh.setText(mediaItem.getPasstime());

        //设置标签
        List<NetAudioBean.ListBean.TagsBean> tagsEntities = mediaItem.getTags();
        if (tagsEntities != null && tagsEntities.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < tagsEntities.size(); i++) {
                buffer.append(tagsEntities.get(i).getName() + " ");
            }
            tvVideoKindText.setText(buffer.toString());
        }

        //设置点赞，踩,转发

        tvShenheDingNumber.setText(mediaItem.getUp()+"");
        tvShenheCaiNumber.setText(mediaItem.getDown() + "");
        tvPostsNumber.setText(mediaItem.getForward() + "");

    }
}
