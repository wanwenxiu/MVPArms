package me.wwx.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import me.wwx.mvparms.demo.mvp.contract.MineContract;
import me.wwx.mvparms.demo.mvp.model.MineModel;

/**
 * 作者：wwx on 2017/5/23 0023 10:57
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@Module
public class MineModule {
    private MineContract.View view;

    /**
     * 构建MineModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MineModule(MineContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MineContract.View provideMineView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MineContract.Model provideMineModel(MineModel model) {
        return model;
    }
}