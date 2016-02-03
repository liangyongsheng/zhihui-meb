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
@Table(name = "meb_asset_ownership")
@Access(AccessType.FIELD)
public class MebAssetOwnershipModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mebAssetOwnershipId;
	private long mebId;
	private int mebAssetId;
	private Integer flag;
	private Long origOrderId;
	private Long tarOrderId;
	private String extraInfo;
	private Timestamp createTime;
	private Integer createOprtId;
	private Timestamp lastReviseTime;
	private Integer lastReviseOprtId;
	private String remark;

	public long getMebAssetOwnershipId() {
		return mebAssetOwnershipId;
	}

	public void setMebAssetOwnershipId(long mebAssetOwnershipId) {
		this.mebAssetOwnershipId = mebAssetOwnershipId;
	}

	public long getMebId() {
		return mebId;
	}

	public void setMebId(long mebId) {
		this.mebId = mebId;
	}

	public int getMebAssetId() {
		return mebAssetId;
	}

	public void setMebAssetId(int mebAssetId) {
		this.mebAssetId = mebAssetId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getOrigOrderId() {
		return origOrderId;
	}

	public void setOrigOrderId(Long origOrderId) {
		this.origOrderId = origOrderId;
	}

	public Long getTarOrderId() {
		return tarOrderId;
	}

	public void setTarOrderId(Long tarOrderId) {
		this.tarOrderId = tarOrderId;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
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
