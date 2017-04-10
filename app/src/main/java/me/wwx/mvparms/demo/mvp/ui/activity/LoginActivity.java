package me.wwx.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;
import com.jess.arms.utils.UiUtils;
import com.marlonmafra.android.widget.EditTextPassword;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.AppComponent;
import common.WEActivity;
import me.wwx.mvparms.demo.R;
import me.wwx.mvparms.demo.di.component.DaggerLoginComponent;
import me.wwx.mvparms.demo.di.module.LoginModule;
import me.wwx.mvparms.demo.mvp.contract.LoginContract;
import me.wwx.mvparms.demo.mvp.presenter.LoginPresenter;
import me.wwx.mvparms.demo.mvp.ui.view.DeleteEditText;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * 作者：wwx on 2017/4/5 0005 15:28
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class LoginActivity extends WEActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.username)
    DeleteEditText username;
    @BindView(R.id.password)
    EditTextPassword password;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this)) //请将LoginModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_login, null, false);
    }

    @Override
    protected void initData() {

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
        setStatusBar();
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW | localLayoutParams.flags);
        }
        initToolbar();
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("登录");
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.toolbar_color), 112);
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {
        mPresenter.requestTestData(username.getText().toString(), password.getText().toString(), loginButton);
    }

    @Override
    public void jumpMainActivity() {
        Intent login_main = new Intent(this,
                UserActivity.class);
        launchActivity(login_main);
    }

    @Override
    public void clickJumpActivity() {
        Snackbar.make(loginButton,"登陆成功",Snackbar.LENGTH_LONG).setAction("ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpMainActivity();
            }
        })
        .setActionTextColor(getResources().getColor(R.color.white))
        .show();
    }
}