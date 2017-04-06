package me.wwx.mvparms.demo.mvp.model.api.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import me.wwx.mvparms.demo.mvp.model.entity.Login;
import me.wwx.mvparms.demo.mvp.model.entity.User;
import rx.Observable;

/**
 * Created by jess on 8/30/16 13:53
 * Contact with jess.yan.effort@gmail.com
 */
public interface CommonCache {



    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Login>> getUsers(Observable<Login> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);

}
