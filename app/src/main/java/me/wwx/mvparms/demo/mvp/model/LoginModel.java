package me.wwx.mvparms.demo.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import me.wwx.mvparms.demo.mvp.contract.LoginContract;
import me.wwx.mvparms.demo.mvp.model.api.cache.CacheManager;
import me.wwx.mvparms.demo.mvp.model.api.service.ServiceManager;
import me.wwx.mvparms.demo.mvp.model.entity.Login;
import rx.Observable;
import rx.functions.Func1;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * 作者：wwx on 2017/4/5 0005 15:27
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

@ActivityScope
public class LoginModel extends BaseModel<ServiceManager, CacheManager> implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Login> getLogin() {
        Observable<Login> users = mServiceManager.getUserService()
                .getUsers();
        Log.d("geek", "LoginModel getLogin: users="+users.toString());
        return  users;
//        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
//        return mCacheManager.getCommonCache()
//                .getUsers(users,new DynamicKey(lastIdQueried)
//                        , new EvictDynamicKey(update))
//                .flatMap(new Func1<Reply<List<Login>>, Observable<List<Login>>>() {
//                    @Override
//                    public Observable<List<Login>> call(Reply<List<Login>> listReply) {
//                        return Observable.just(listReply.getData());
//                    }
//                });
    }
}
