package me.wwx.mvparms.demo.mvp.model.api.service;

import java.util.List;
import java.util.Map;

import me.wwx.mvparms.demo.mvp.model.entity.Login;
import me.wwx.mvparms.demo.mvp.model.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 存放关于用户的一些api
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface UserService {

//    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";
//    @Headers({HEADER_API_VERSION})
    @GET("mall/androidUser_loginTimeOutApp?")
    Observable<Login> getUsers();



}
