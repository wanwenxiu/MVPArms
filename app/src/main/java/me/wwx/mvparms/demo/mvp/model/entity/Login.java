package me.wwx.mvparms.demo.mvp.model.entity;

/**
 * 作者：wwx on 2017/4/5 0005 16:07
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class Login {
    private String MSG;
    private int status;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Login{" +
                "MSG='" + MSG + '\'' +
                ", status=" + status +
                '}';
    }
}
