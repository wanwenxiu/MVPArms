package me.wwx.mvparms.demo.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import me.wwx.mvparms.demo.mvp.contract.PhotoContract;

/**
 * 作者：wwx on 2017/5/25 0025 16:23
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@ActivityScope
public class PhotoModel extends BaseModel implements PhotoContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public PhotoModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

}
