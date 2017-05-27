package me.wwx.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import me.wwx.mvparms.demo.mvp.contract.CalendarContract;
import me.wwx.mvparms.demo.mvp.model.CalendarModel;

/**
 * 作者：wwx on 2017/5/25 0025 14:58
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@Module
public class CalendarModule {
    private CalendarContract.View view;

    /**
     * 构建CalendarModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CalendarModule(CalendarContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CalendarContract.View provideCalendarView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CalendarContract.Model provideCalendarModel(CalendarModel model) {
        return model;
    }
}