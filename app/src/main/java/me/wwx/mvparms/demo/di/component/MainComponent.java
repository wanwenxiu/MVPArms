package me.wwx.mvparms.demo.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import me.wwx.mvparms.demo.di.module.MainModule;
import me.wwx.mvparms.demo.mvp.ui.activity.MainActivity;

/**
 * 作者：wwx on 2017/5/22 0022 14:51
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}