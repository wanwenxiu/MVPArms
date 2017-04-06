package me.wwx.mvparms.demo.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import me.wwx.mvparms.demo.mvp.contract.UserContract;
import me.wwx.mvparms.demo.mvp.model.api.cache.CacheManager;
import me.wwx.mvparms.demo.mvp.model.api.service.ServiceManager;
import me.wwx.mvparms.demo.mvp.model.entity.Login;
import me.wwx.mvparms.demo.mvp.model.entity.User;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jess on 9/4/16 10:56
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
public class UserModel extends BaseModel<ServiceManager, CacheManager> implements UserContract.Model {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public UserModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


    @Override
    public Observable<Login> getUsers(int lastIdQueried, boolean update) {
        Observable<Login> users = mServiceManager.getUserService()
                .getUsers();
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getUsers(users
                        , new DynamicKey(lastIdQueried)
                        , new EvictDynamicKey(update))
                .flatMap(new Func1<Reply<Login>, Observable<Login>>() {
                    @Override
                    public Observable<Login> call(Reply<Login> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }

}
