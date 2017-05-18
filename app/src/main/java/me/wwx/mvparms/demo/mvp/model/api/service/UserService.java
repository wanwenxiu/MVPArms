package me.wwx.mvparms.demo.mvp.model.api.service;

import java.util.List;

import io.reactivex.Observable;
import me.wwx.mvparms.demo.mvp.model.entity.LoginEntity;
import me.wwx.mvparms.demo.mvp.model.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 存放关于用户的一些api
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface UserService {

    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);

    //shouji=%1$s&pwd=%2$s&macAddr=%3$s&id=%4$s&app_version=%5$s&mobile_type=%6$s&mobile_brand=%7$s&mobile_version=%8$s"
    @GET("mall/androidUser_findUser?")
    Observable<LoginEntity> login(@Query("shouji") String username, @Query("pwd") String password,
                                            @Query("macAddr") String macAddr, @Query("id") String xmid,
                                            @Query("app_version") String app_version, @Query("mobile_type") String mobile_type,
                                            @Query("mobile_brand") String mobile_brand, @Query("mobile_version") String mobile_version);


}
