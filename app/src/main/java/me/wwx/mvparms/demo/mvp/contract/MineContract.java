package me.wwx.mvparms.demo.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import me.wwx.mvparms.demo.mvp.ui.adapter.TextTagsAdapter;

/**
 * 作者：wwx on 2017/5/23 0023 10:57
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public interface MineContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setTagCloudAdapter(TextTagsAdapter adapter);

    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

    }
}