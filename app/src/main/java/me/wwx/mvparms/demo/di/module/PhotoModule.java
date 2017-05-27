package me.wwx.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import me.wwx.mvparms.demo.mvp.contract.PhotoContract;
import me.wwx.mvparms.demo.mvp.model.PhotoModel;

/**
 * 作者：wwx on 2017/5/25 0025 16:23
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@Module
public class PhotoModule {
    private PhotoContract.View view;

    /**
     * 构建PhotoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PhotoModule(PhotoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PhotoContract.View providePhotoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PhotoContract.Model providePhotoModel(PhotoModel model) {
        return model;
    }
}