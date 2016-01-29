package com.zhihui.meb.model;

import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meb_asset_point_type")
@Access(AccessType.FIELD)
public class MebAssetPointTypeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int mebAssetPointTypeId;
	private String name;
	private Boolean innerOnly;
	private Timestamp createTime;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMebAssetPointTypeId() {
		return mebAssetPointTypeId;
	}

	public void setMebAssetPointTypeId(int mebAssetPointTypeId) {
		this.mebAssetPointTypeId = mebAssetPointTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getInnerOnly() {
		return innerOnly;
	}

	public void setInnerOnly(Boolean innerOnly) {
		this.innerOnly = innerOnly;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
