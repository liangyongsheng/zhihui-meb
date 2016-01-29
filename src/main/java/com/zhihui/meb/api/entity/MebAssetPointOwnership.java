package com.zhihui.meb.api.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.jsonmapper.JsonStr2DateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DateSerializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeSerializer;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;
import com.zhihui.core.xmladapter.XmlStr2DatetimeAdapter;

@XmlRootElement(name = "mebAssetPointOwnership")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetPointOwnership {
	private Long mebAssetPointOwnershipId;
	private Long mebId;
	private Integer mebAssetPointTypeId;
	private String mebAssetPointTypeName;
	private Integer initialPoint;
	private Integer actualPoint;

	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date validBeginDate;

	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date validEndDate;

	private Integer sellerId;
	private Integer origPartnerId;
	private Integer origChainId;
	private Long origOrderId;
	private Integer tarPartnerId;
	private Integer tarChainId;
	private Long tarOrderId;
	private Integer flag;
	private String extraInfo;

	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date createTime;

	private Integer createOprtId;

	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date lastReviseTime;

	private Integer lastReviseOprtId;
	private String remark;

	public Long getMebAssetPointOwnershipId() {
		return mebAssetPointOwnershipId;
	}

	public void setMebAssetPointOwnershipId(Long mebAssetPointOwnershipId) {
		this.mebAssetPointOwnershipId = mebAssetPointOwnershipId;
	}

	public Long getMebId() {
		return mebId;
	}

	public void setMebId(Long mebId) {
		this.mebId = mebId;
	}

	public Integer getMebAssetPointTypeId() {
		return mebAssetPointTypeId;
	}

	public void setMebAssetPointTypeId(Integer mebAssetPointTypeId) {
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

	public Integer getOrigPartnerId() {
		return origPartnerId;
	}

	public void setOrigPartnerId(Integer origPartnerId) {
		this.origPartnerId = origPartnerId;
	}

	public Integer getOrigChainId() {
		return origChainId;
	}

	public void setOrigChainId(Integer origChainId) {
		this.origChainId = origChainId;
	}

	public Long getOrigOrderId() {
		return origOrderId;
	}

	public void setOrigOrderId(Long origOrderId) {
		this.origOrderId = origOrderId;
	}

	public Integer getTarPartnerId() {
		return tarPartnerId;
	}

	public void setTarPartnerId(Integer tarPartnerId) {
		this.tarPartnerId = tarPartnerId;
	}

	public Integer getTarChainId() {
		return tarChainId;
	}

	public void setTarChainId(Integer tarChainId) {
		this.tarChainId = tarChainId;
	}

	public Long getTarOrderId() {
		return tarOrderId;
	}

	public void setTarOrderId(Long tarOrderId) {
		this.tarOrderId = tarOrderId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateOprtId() {
		return createOprtId;
	}

	public void setCreateOprtId(Integer createOprtId) {
		this.createOprtId = createOprtId;
	}

	public Date getLastReviseTime() {
		return lastReviseTime;
	}

	public void setLastReviseTime(Date lastReviseTime) {
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
