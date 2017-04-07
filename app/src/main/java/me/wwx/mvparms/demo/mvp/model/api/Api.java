package me.wwx.mvparms.demo.mvp.model.api;

/**
 * Created by jess on 8/5/16 11:25
 * contact with jess.yan.effort@gmail.com
 */
public interface Api {
    String APP_DOMAIN = "http://192.168.8.22:8080/wygl/";
//    String APP_DOMAIN = "https://api.github.com/";

    //请求成功
    String RequestSuccess = "0";

    //登录失效
    String RequestSuccess_LoginTimeout = "99";

}
