package com.zhihui.meb.model;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meb_asset")
@Access(AccessType.FIELD)
public class MebAssetModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mebAssetId;
	private int mebAssetTypeId;
	@Transient
	private String mebAssetTypeName;
	private String name;
	private Integer faceValue;
	private Date validBeginDate;
	private Date validEndDate;
	private Integer sellerId;
	private String conditionJson;
	private Timestamp createTime;
	private Integer createOprtId;
	private Timestamp lastReviseTime;
	private Integer lastReviseOprtId;
	private String remark;

	public int getMebAssetId() {
		return mebAssetId;
	}

	public void setMebAssetId(int mebAssetId) {
		this.mebAssetId = mebAssetId;
	}

	public int getMebAssetTypeId() {
		return mebAssetTypeId;
	}

	public void setMebAssetTypeId(int mebAssetTypeId) {
		this.mebAssetTypeId = mebAssetTypeId;
	}

	public String getMebAssetTypeName() {
		return mebAssetTypeName;
	}

	public void setMebAssetTypeName(String mebAssetTypeName) {
		this.mebAssetTypeName = mebAssetTypeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}

	public Date getValidBeginDate() {
		return validBeginDate;
	}

	public void setValidBeginDate(Date validBeginDate) {
		this.validBeginDate = validBeginDate;
	}

	public Date getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getConditionJson() {
		return conditionJson;
	}

	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
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