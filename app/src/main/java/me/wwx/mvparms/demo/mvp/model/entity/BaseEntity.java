package me.wwx.mvparms.demo.mvp.model.entity;

/**
 * @ClassName: BaseEntity 
 * @Description: 公共实体�?
 * @author wwx
 * @date 2015�?1�?6�?下午1:38:22 
 *
 */
public class BaseEntity {
	/**
	 * 状态码
	 */
	public int status;
	/**
	 * 详细信息
	 */
	public String MSG;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String MSG) {
		this.MSG = MSG;
	}

}
