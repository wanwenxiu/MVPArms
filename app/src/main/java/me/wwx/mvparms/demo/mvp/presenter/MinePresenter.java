package me.wwx.mvparms.demo.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.wwx.mvparms.demo.mvp.contract.MineContract;
import me.wwx.mvparms.demo.mvp.ui.adapter.TextTagsAdapter;


/**
 * 作者：wwx on 2017/5/23 0023 10:57
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@ActivityScope
public class MinePresenter extends BasePresenter<MineContract.Model, MineContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private TextTagsAdapter tagsAdapter;

    @Inject
    public MinePresenter(MineContract.Model model, MineContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void requestData(){
        if(tagsAdapter == null){
            tagsAdapter = new TextTagsAdapter(new String[8]);
        }
        mRootView.setTagCloudAdapter(tagsAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}