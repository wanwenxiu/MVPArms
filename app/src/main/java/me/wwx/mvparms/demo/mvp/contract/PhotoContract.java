package me.wwx.mvparms.demo.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

/**
 * 作者：wwx on 2017/5/25 0025 16:23
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public interface PhotoContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

    }
}