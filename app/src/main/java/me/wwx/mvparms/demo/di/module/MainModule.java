package me.wwx.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import me.wwx.mvparms.demo.mvp.contract.MainContract;
import me.wwx.mvparms.demo.mvp.model.MainModel;

/**
 * 作者：wwx on 2017/5/22 0022 14:51
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@Module
public class MainModule {
    private MainContract.View view;

    /**
     * 构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainContract.Model provideMainModel(MainModel model) {
        return model;
    }
}