package com.zhihui.meb.model;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meb_asset_point_ownership")
@Access(AccessType.FIELD)
public class MebAssetPointOwnershipModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mebAssetPointOwnershipId;
	private long mebId;
	private int mebAssetPointTypeId;
	@Transient
	private String mebAssetPointTypeName;
	private Integer initialPoint;
	private Integer actualPoint;
	@Column(nullable = false)
	private Integer flag;
	private Date validBeginDate;
	private Date validEndDate;
	private Integer sellerId;
	private Long origOrderId;
	private Long tarOrderId;
	private String innerDealInfo;
	private String extraInfo;
	private Timestamp createTime;
	private Integer createOprtId;
	private Timestamp lastReviseTime;
	private Integer lastReviseOprtId;
	private String remark;

	public long getMebAssetPointOwnershipId() {
		return mebAssetPointOwnershipId;
	}

	public void setMebAssetPointOwnershipId(long mebAssetPointOwnershipId) {
		this.mebAssetPointOwnershipId = mebAssetPointOwnershipId;
	}

	public long getMebId() {
		return mebId;
	}

	public void setMebId(long mebId) {
		this.mebId = mebId;
	}

	public int getMebAssetPointTypeId() {
		return mebAssetPointTypeId;
	}

	public void setMebAssetPointTypeId(int mebAssetPointTypeId) {
		this.mebAssetPointTypeId = mebAssetPointTypeId;
	}

	public String getMebAssetPointTypeName() {
		return mebAssetPointTypeName;
	}

	public void setMebAssetPointTypeName(String mebAssetPointTypeName) {
		this.mebAssetPointTypeName = mebAssetPointTypeName;
	}

	public Integer getInitialPoint() {
		return initialPoint;
	}

	public void setInitialPoint(Integer initialPoint) {
		this.initialPoint = initialPoint;
	}

	public Integer getActualPoint() {
		return actualPoint;
	}

	public void setActualPoint(Integer actualPoint) {
		this.actualPoint = actualPoint;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public String getInnerDealInfo() {
		return innerDealInfo;
	}

	public void setInnerDealInfo(String innerDealInfo) {
		this.innerDealInfo = innerDealInfo;
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
