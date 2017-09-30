package me.wwx.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import butterknife.BindView;
import me.wwx.mvparms.demo.R;
import me.wwx.mvparms.demo.di.component.DaggerMainComponent;
import me.wwx.mvparms.demo.di.module.MainModule;
import me.wwx.mvparms.demo.mvp.contract.MainContract;
import me.wwx.mvparms.demo.mvp.presenter.MainPresenter;
import me.wwx.mvparms.demo.mvp.ui.adapter.MainButtonAdapter;
import me.wwx.mvparms.demo.mvp.ui.util.BottomNavigationViewHelper;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 作者：wwx on 2017/5/22 0022 14:49
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：主界面
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.maintoolbar)
    Toolbar maintoolbar;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.viewpages)
    ViewPager viewpages;

    private MenuItem menuItem;

    /** 设置按钮 */
    private MenuItem menuItemSetting;

    private boolean isShowSettngPop = false;

    private CustomPopWindow popWindow;

    private static final int REQUEST_CODE_CHOOSE = 23;

    private RxPermissions mRxPermissions;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        mRxPermissions = new RxPermissions(this);
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this)) //请将MainModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(maintoolbar);

        StatusBarUtil.setColorForDrawerLayout(this,drawerLayout, ContextCompat.getColor(this, R.color.toolbar_color), 0);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, maintoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        setViewPageAdapter(viewpages);

        View popAddView = LayoutInflater.from(this).inflate(R.layout.popup_add,null);
        handleLogic(popAddView);
        popWindow= new CustomPopWindow.PopupWindowBuilder(this)
                .setView(popAddView)//显示的布局，还可以通过设置一个View
                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT) //设置显示的大小，不设置就默认包裹内容
                .setFocusable(false)//是否获取焦点，默认为ture
                .setOutsideTouchable(false)//是否PopupWindow 以外触摸dissmiss
                .create();//创建PopupWindow
    }

    /**
     * 设置ViewPage适配器
     * @param viewpage
     */
    protected void setViewPageAdapter(ViewPager viewpage) {
        viewpage.setAdapter(new MainButtonAdapter(getSupportFragmentManager()));
        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);

                if(position == 0){
                    settingMenuShow(true);
                    setFabShow(true);
                }else{
                    settingMenuShow(false);
                    setFabShow(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        viewpage.setOffscreenPageLimit(2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_right_icon, menu);
        menuItemSetting = menu.findItem(R.id.setting);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.setting){
           setPopShow(isShowSettngPop,item);
        }
        return super.onOptionsItemSelected(item);
    }

      @Override
        public void initData () {

        }

        @Override
        public void showLoading () {

        }

        @Override
        public void hideLoading () {

        }

        @Override
        public void showMessage (@NonNull String message){
            checkNotNull(message);
            UiUtils.SnackbarText(message);
        }

        @Override
        public void launchActivity (@NonNull Intent intent){
            checkNotNull(intent);
            UiUtils.startActivity(intent);
        }

        @Override
        public void killMyself () {
            finish();
        }

        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            switch (item.getItemId()) {
                case R.id.navigation_mine:
                    setViewPagesItem(0);
                    settingMenuShow(true);
                    setFabShow(true);
                    break;
                case R.id.navigation_jilv:
                    setViewPagesItem(1);
                    settingMenuShow(false);
                    setFabShow(false);
                    break;
                case R.id.navigation_vedio:
                    setViewPagesItem(2);
                    settingMenuShow(false);
                    setFabShow(false);
                    break;
                case R.id.navigation_image:
                    setViewPagesItem(3);
                    settingMenuShow(false);
                    setFabShow(false);
                    break;
                case R.id.nav_calendar:
                    launchActivity(new Intent(this,CalendarActivity.class));
                    break;
                case R.id.nav_version:
                    break;
            }
            return false;
        }

    /**
     * 设置viewpage页面
     * @param position
     */
    public void setViewPagesItem(int position){
        viewpages.setCurrentItem(position);
    }

    /**
     * 打开侧滑菜单
     */
    public void  openDrawer(){
            if(drawerLayout != null){
                drawerLayout.openDrawer(GravityCompat.START);
            }
     }

    /**
     * 关闭侧滑菜单
     */
    public void closeDrawer(){
         if(drawerLayout != null){
             drawerLayout.closeDrawer(GravityCompat.START);
         }
     }

    /**
     * 显示或隐藏设置菜单
     */
    public void settingMenuShow(boolean isshow){
        if(isshow)  menuItemSetting.setVisible(true);
        else{
            menuItemSetting.setVisible(false);
        }
     }

    /**
     * 控制浮动按钮是否显示
     * @param isshow
     */
     public void setFabShow(boolean isshow){
         if(isshow)  fab.setVisibility(View.VISIBLE);
         else{
             fab.setVisibility(View.GONE);
         }
     }

     public void setPopShow(boolean isshow,MenuItem item){
         if(isshow) {
             item.setIcon(R.mipmap.menu_icon_add_nomal);
             popWindow.dissmiss();
         }
         else{
             item.setIcon(R.mipmap.menu_icon_add_select);
             popWindow.showAsDropDown(maintoolbar);
         }
         isShowSettngPop = !isShowSettngPop;
     }


    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popWindow!=null){
                    setPopShow(true,menuItemSetting);
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.addText:
                        showContent = "文字";
                        break;
                    case R.id.addPhoto:
                        mPresenter.goCamarePhoto();
                        break;
                    case R.id.addVedio:
                        showContent = "视频";
                        break;
                }
                Toast.makeText(MainActivity.this,showContent,Toast.LENGTH_SHORT).show();
            }
        };
        contentView.findViewById(R.id.addText).setOnClickListener(listener);
        contentView.findViewById(R.id.addPhoto).setOnClickListener(listener);
        contentView.findViewById(R.id.addVedio).setOnClickListener(listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            Log.d("geek", "onActivityResult: 1="+Matisse.obtainResult(data));
            Log.d("geek", "onActivityResult: 2="+Matisse.obtainPathResult(data));
        }
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }

    @Override
    public void goCAMARE() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "me.wwx.mvparms.demo.mvp.ui.activity.fileprovider"))
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }
}
