package me.wwx.mvparms.demo.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import me.wwx.mvparms.demo.di.module.MineModule;
import me.wwx.mvparms.demo.mvp.ui.fragment.MineFragment;

/**
 * 作者：wwx on 2017/5/23 0023 10:57
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@ActivityScope
@Component(modules = MineModule.class, dependencies = AppComponent.class)
public interface MineComponent {
    void inject(MineFragment fragment);
}