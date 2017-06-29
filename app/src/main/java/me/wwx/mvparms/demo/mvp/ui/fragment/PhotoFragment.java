package me.wwx.mvparms.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.wwx.mvparms.demo.R;
import me.wwx.mvparms.demo.di.component.DaggerPhotoComponent;
import me.wwx.mvparms.demo.di.module.PhotoModule;
import me.wwx.mvparms.demo.mvp.contract.PhotoContract;
import me.wwx.mvparms.demo.mvp.presenter.PhotoPresenter;
import me.wwx.mvparms.demo.mvp.ui.adapter.PicturePreviewAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 作者：wwx on 2017/5/25 0025 16:43
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：我的图片
 */

public class PhotoFragment extends BaseFragment<PhotoPresenter> implements PhotoContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    public PicturePreviewAdapter adapter;

    public static PhotoFragment newInstance() {
        PhotoFragment fragment = new PhotoFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerPhotoComponent
                .builder()
                .appComponent(appComponent)
                .photoModule(new PhotoModule(this))//请将PhotoModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.public_swiperefresh_recylerview, container, false);
    }

    @Override
    public void initData() {
        List<String> mdata = new ArrayList<>();
        mdata.add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        mdata.add("http://img3.imgtn.bdimg.com/it/u=3242709860,2221903223&fm=214&gp=0.jpg");
        mdata.add("http://pic1.cxtuku.com/00/06/78/b9903ad9ea2b.jpg");
        mdata.add("http://img15.3lian.com/2015/h1/280/d/5.jpg");
        mdata.add("http://img.sc115.com/uploads/sc/jpgs/1409/apic6104_sc115.com.jpg");
        mdata.add("http://pic36.nipic.com/20131220/12708292_140812650116_2.jpg");

        adapter = new PicturePreviewAdapter(mdata);
        recyclerView.setLayoutManager(new OverLayCardLayoutManager());
        recyclerView.setAdapter(adapter);
        CardConfig.initConfig(getActivity());
        ItemTouchHelper.Callback callback = new RenRenCallback(recyclerView, adapter, mdata);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

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
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}