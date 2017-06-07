package me.wwx.mvparms.demo.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import me.wwx.mvparms.demo.R;
import me.wwx.mvparms.demo.mvp.ui.holder.PicturePreviewHolder;

/**
 * 作者：wwx on 2017/6/7 0007 11:17
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class PicturePreviewAdapter extends DefaultAdapter<String> {


    public PicturePreviewAdapter(List<String> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<String> getHolder(View v, int viewType) {
        return new PicturePreviewHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycle_list;
    }
}
