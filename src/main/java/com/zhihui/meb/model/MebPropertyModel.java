package com.zhihui.meb.model;

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
@Table(name = "meb_property")
@Access(AccessType.FIELD)
public class MebPropertyModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mebPropertyId;
	private long mebId;
	private int mebPropertyTypeId;
	@Transient
	private String mebPropertyTypeName;
	private String value;
	private Integer flag;
	private Timestamp createTime;
	private Integer createOprtId;
	private Timestamp lastReviseTime;
	private Integer lastReviseOprtId;
	private String remark;

	public long getMebPropertyId() {
		return mebPropertyId;
	}

	public void setMebPropertyId(long mebPropertyId) {
		this.mebPropertyId = mebPropertyId;
	}

	public long getMebId() {
		return mebId;
	}

	public void setMebId(long mebId) {
		this.mebId = mebId;
	}

	public int getMebPropertyTypeId() {
		return mebPropertyTypeId;
	}

	public void setMebPropertyTypeId(int mebPropertyTypeId) {
		this.mebPropertyTypeId = mebPropertyTypeId;
	}

	public String getMebPropertyTypeName() {
		return mebPropertyTypeName;
	}

	public void setMebPropertyTypeName(String mebPropertyTypeName) {
		this.mebPropertyTypeName = mebPropertyTypeName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
