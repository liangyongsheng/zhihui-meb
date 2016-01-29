package com.zhihui.meb.api.response;

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

import com.zhihui.core.api.ApiResponse;
import com.zhihui.core.jsonmapper.JsonStr2DateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DateSerializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeSerializer;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;
import com.zhihui.core.xmladapter.XmlStr2DatetimeAdapter;
import com.zhihui.meb.api.entity.MebAssetCondition;

@XmlRootElement(name = "apiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetGetResponse extends ApiResponse {
	private Integer mebAssetId;
	private Integer mebAssetTypeId;
	private String mebAssetTypeName;
	private String name;
	private Integer faceValue;
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date validBeginDate;
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date validEndDate;
	private MebAssetCondition mebAssetCondition;
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

	public Integer getMebAssetId() {
		return mebAssetId;
	}

	public void setMebAssetId(Integer mebAssetId) {
		this.mebAssetId = mebAssetId;
	}

	public Integer getMebAssetTypeId() {
		return mebAssetTypeId;
	}

	public void setMebAssetTypeId(Integer mebAssetTypeId) {
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

	public MebAssetCondition getMebAssetCondition() {
		return mebAssetCondition;
	}

	public void setMebAssetCondition(MebAssetCondition mebAssetCondition) {
		this.mebAssetCondition = mebAssetCondition;
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
