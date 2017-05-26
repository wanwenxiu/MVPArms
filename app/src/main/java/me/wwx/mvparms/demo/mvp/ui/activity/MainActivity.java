package me.wwx.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;

import com.jaeger.library.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

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

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
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
                settingMenuShow(position);
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
        menuItemSetting.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.setting){
            showMessage("上传");
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
                    break;
                case R.id.navigation_jilv:
                    setViewPagesItem(1);
                    break;
                case R.id.navigation_vedio:
                    setViewPagesItem(2);
                    break;
                case R.id.navigation_image:
                    setViewPagesItem(3);
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
        settingMenuShow(position);
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
    public void settingMenuShow(int position){
        if(position == 0) menuItemSetting.setVisible(false);
        else{
            menuItemSetting.setVisible(true);
        }
     }

    }
