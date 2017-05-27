package me.wwx.mvparms.demo.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import me.wwx.mvparms.demo.di.module.PhotoModule;
import me.wwx.mvparms.demo.mvp.ui.fragment.PhotoFragment;

/**
 * 作者：wwx on 2017/5/25 0025 16:23
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@ActivityScope
@Component(modules = PhotoModule.class, dependencies = AppComponent.class)
public interface PhotoComponent {
    void inject(PhotoFragment fragment);
}