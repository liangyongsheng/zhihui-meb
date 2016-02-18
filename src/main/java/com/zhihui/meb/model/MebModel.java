package com.zhihui.meb.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meb")
@Access(AccessType.FIELD)
public class MebModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mebId;
	private Integer mebTypeId;
	@Transient
	private String mebTypeName;
	private String name;
	private String password;
	private Integer gender;
	private Date birthday;
	private Integer channelSellerId;
	private Integer sellerId;
	private Integer flag;
	private Timestamp createTime;
	private Integer createOprtId;
	private Timestamp lastReviseTime;
	private Integer lastReviseOprtId;
	private String remark;

	public long getMebId() {
		return mebId;
	}

	public void setMebId(long mebId) {
		this.mebId = mebId;
	}

	public Integer getMebTypeId() {
		return mebTypeId;
	}

	public void setMebTypeId(Integer mebTypeId) {
		this.mebTypeId = mebTypeId;
	}

	public String getMebTypeName() {
		return mebTypeName;
	}

	public void setMebTypeName(String mebTypeName) {
		this.mebTypeName = mebTypeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getChannelSellerId() {
		return channelSellerId;
	}

	public void setChannelSellerId(Integer channelSellerId) {
		this.channelSellerId = channelSellerId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public Timestamp getLastReviseTime() {
		return lastReviseTime;
	}

	public void setLastReviseTime(Timestamp lastReviseTime) {
		this.lastReviseTime = lastReviseTime;
	}

	public Integer getLastReviseOprtId() {
		return lastReviseOprtId;
	}

	public void setLastReviseOprtId(Integer lastReviseOprtId) {
		this.lastReviseOprtId = lastReviseOprtId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}