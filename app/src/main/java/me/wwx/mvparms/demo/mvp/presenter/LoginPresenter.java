package me.wwx.mvparms.demo.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import me.wwx.mvparms.demo.mvp.contract.LoginContract;
import me.wwx.mvparms.demo.mvp.model.entity.LoginEntity;
import me.wwx.mvparms.demo.mvp.ui.util.PhoneUtil;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


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

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    /**
     * 请求登录数据
     */
    public void requestTestData(String username,String pwd){
        Subscription subscribe = mModel.getLogin(username, PhoneUtil.getMD5(pwd), PhoneUtil.getDeviceId(mApplication), "321", PhoneUtil.getVersion(mApplication), "A", PhoneUtil.getPhoneBrand() + PhoneUtil.getPhoneModel(), PhoneUtil.getPhoneSysVersion())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 1))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d("geek", "doOnSubscribe call: 进入");
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        Log.d("geek", " doAfterTerminate call: 进入");
                    }
                })
                .compose(RxUtils.<LoginEntity>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<LoginEntity>(mErrorHandler) {
                    @Override
                    public void onNext(LoginEntity users) {
                        Log.d("geek", "subscribe onNext: users" + users.toString());
                        if (users != null) {
                            mRootView.jumpMainActivity();
                            mRootView.killMyself();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}