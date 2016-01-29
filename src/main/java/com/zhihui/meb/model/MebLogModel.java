package com.zhihui.meb.model;

import java.security.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meb_log")
@Access(AccessType.FIELD)
public class MebLogModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mebLogId;
	private int mebLogTypeId;
	@Transient
	private String mebLogTypeName;
	private String title;
	private String beforeContentJson;
	private String afterContentJson;
	private Timestamp createTime;
	private Integer createOprtId;
	private String remark;

	public long getMebLogId() {
		return mebLogId;
	}

	public void setMebLogId(long mebLogId) {
		this.mebLogId = mebLogId;
	}

	public int getMebLogTypeId() {
		return mebLogTypeId;
	}

	public void setMebLogTypeId(int mebLogTypeId) {
		this.mebLogTypeId = mebLogTypeId;
	}

	public String getMebLogTypeName() {
		return mebLogTypeName;
	}

	public void setMebLogTypeName(String mebLogTypeName) {
		this.mebLogTypeName = mebLogTypeName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBeforeContentJson() {
		return beforeContentJson;
	}

	public void setBeforeContentJson(String beforeContentJson) {
		this.beforeContentJson = beforeContentJson;
	}

	public String getAfterContentJson() {
		return afterContentJson;
	}

	public void setAfterContentJson(String afterContentJson) {
		this.afterContentJson = afterContentJson;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateOprtId() {
		return createOprtId;
	}

	public void setCreateOprtId(Integer createOprtId) {
		this.createOprtId = createOprtId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
