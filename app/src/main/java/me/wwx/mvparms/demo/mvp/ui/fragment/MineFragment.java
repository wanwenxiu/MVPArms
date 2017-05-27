package me.wwx.mvparms.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.moxun.tagcloudlib.view.TagCloudView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.wwx.mvparms.demo.R;
import me.wwx.mvparms.demo.di.component.DaggerMineComponent;
import me.wwx.mvparms.demo.di.module.MineModule;
import me.wwx.mvparms.demo.mvp.contract.MineContract;
import me.wwx.mvparms.demo.mvp.presenter.MinePresenter;
import me.wwx.mvparms.demo.mvp.ui.adapter.TextTagsAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 作者：wwx on 2017/5/23 0023 10:56
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：我的首页
 */

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {


    @BindView(R.id.fragment_tagcloud)
    TagCloudView fragmentTagcloud;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .mineModule(new MineModule(this))//请将MineModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void initData() {

    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onActivityCreated
     * 还没执行,setData里调用presenter的方法时,是会报空的,因为dagger注入是在onActivityCreated
     * 方法中执行的,如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter.requestData();
        return rootView;
    }

    @Override
    public void setTagCloudAdapter(TextTagsAdapter adapter) {
        fragmentTagcloud.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}