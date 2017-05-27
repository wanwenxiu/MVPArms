package me.wwx.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wwx.mvparms.demo.R;
import me.wwx.mvparms.demo.di.component.DaggerCalendarComponent;
import me.wwx.mvparms.demo.di.module.CalendarModule;
import me.wwx.mvparms.demo.mvp.contract.CalendarContract;
import me.wwx.mvparms.demo.mvp.presenter.CalendarPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 作者：wwx on 2017/5/25 0025 15:04
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class CalendarActivity extends BaseActivity<CalendarPresenter> implements CalendarContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCalendarComponent
                .builder()
                .appComponent(appComponent)
                .calendarModule(new CalendarModule(this)) //请将CalendarModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_calendar;
    }

    @Override
    public void initData() {
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
