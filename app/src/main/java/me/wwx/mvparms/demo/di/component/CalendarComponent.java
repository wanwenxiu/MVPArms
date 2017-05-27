package me.wwx.mvparms.demo.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import me.wwx.mvparms.demo.di.module.CalendarModule;
import me.wwx.mvparms.demo.mvp.ui.activity.CalendarActivity;

/**
 * 作者：wwx on 2017/5/25 0025 14:58
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@ActivityScope
@Component(modules = CalendarModule.class, dependencies = AppComponent.class)
public interface CalendarComponent {
    void inject(CalendarActivity activity);
}