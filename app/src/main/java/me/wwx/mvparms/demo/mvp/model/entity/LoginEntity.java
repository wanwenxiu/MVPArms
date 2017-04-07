package me.wwx.mvparms.demo.mvp.model.entity;


import java.util.List;

public class LoginEntity {
	public List<AppYezhuFangwu> house;
	
	public CxwyYezhu user;

	public String uuid;

	/** 默认地址*/
	private CxwyMallAdd addr;

	public List<AppYezhuFangwu> getHouse() {
		return house;
	}

	public void setHouse(List<AppYezhuFangwu> house) {
		this.house = house;
	}

	public CxwyYezhu getUser() {
		return user;
	}

	public void setUser(CxwyYezhu user) {
		this.user = user;
	}

	public CxwyMallAdd getAddr() {
		return addr;
	}

	public void setAddr(CxwyMallAdd addr) {
		this.addr = addr;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "LoginEntity{" +
				"house=" + house +
				", user=" + user +
				", uuid='" + uuid + '\'' +
				", addr=" + addr +
				'}';
	}
}
