package me.wwx.mvparms.demo.mvp.ui.holder;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import me.wwx.mvparms.demo.R;

/**
 * 作者：wwx on 2017/6/7 0007 11:24
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class PicturePreviewHolder extends BaseHolder<String> {

    @Nullable
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @Nullable
    @BindView(R.id.tv_name)
    TextView tvName;

    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架

    public PicturePreviewHolder(View itemView) {
        super(itemView);
        mAppComponent = ((App)itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(String data, int position) {
        Log.d("geek", "setData: data"+data+",po="+position);
        mImageLoader.loadImage(mAppComponent.appManager().getCurrentActivity() == null
                ? mAppComponent.Application() : mAppComponent.appManager().getCurrentActivity(),
                GlideImageConfig.builder().url(data).imageView(ivAvatar).build());
    }

    @Override
    protected void onRelease() {
        mImageLoader.clear(mAppComponent.Application(), GlideImageConfig.builder()
                .imageViews(ivAvatar)
                .build());
    }
}
