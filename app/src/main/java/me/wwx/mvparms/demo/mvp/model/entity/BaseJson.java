package me.wwx.mvparms.demo.mvp.model.entity;

import java.io.Serializable;

import me.wwx.mvparms.demo.mvp.model.api.Api;

/**
 * 如果你服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * 替换范型即可重用BaseJson
 * Created by jess on 26/09/2016 15:19
 * Contact with jess.yan.effort@gmail.com
 */

public class BaseJson<T> implements Serializable{
    private T data;
    private String status;
    private String MSG;

    public T getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (status.equals(Api.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
