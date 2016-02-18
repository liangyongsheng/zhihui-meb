package com.zhihui.meb.api.request;

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

import com.zhihui.core.api.ApiRequest;
import com.zhihui.core.exception.AsignException;
import com.zhihui.core.exception.CheckEmptyException;
import com.zhihui.core.exception.CheckException;
import com.zhihui.core.exception.CheckIllicitValueException;
import com.zhihui.core.jsonmapper.JsonStr2DateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DateSerializer;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;
import com.zhihui.meb.api.entity.MebAssetCondition;
import com.zhihui.meb.api.response.MebAssetAddResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetAddRequest extends ApiRequest<MebAssetAddResponse> {
	private Integer mebAssetTypeId;
	private String name;
	private Integer faceValue;
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date validBeginDate;
	private Date validEndDate;
	private MebAssetCondition mebAssetCondition;
	private String remark;

	public Integer getMebAssetTypeId() {
		return mebAssetTypeId;
	}

	public void setMebAssetTypeId(Integer mebAssetTypeId) {
		this.mebAssetTypeId = mebAssetTypeId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Class<MebAssetAddResponse> getResponseType() {
		return MebAssetAddResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (MyStringUtils.isEmpty(this.name))
			throw new CheckEmptyException("field: name is empty.");

		if (this.mebAssetTypeId == null || this.mebAssetTypeId <= 0)
			throw new CheckIllicitValueException("field: mebAssetTypeId is illicit.");

		if (this.faceValue == null || this.faceValue <= 0)
			throw new CheckIllicitValueException("field: faceValue is illicit.");

		if (this.validBeginDate == null)
			throw new CheckIllicitValueException("field: validBeginDate is illicit.");

		if (this.validEndDate == null)
			throw new CheckIllicitValueException("field: validEndDate is illicit.");

		if (this.validBeginDate.getTime() > this.validEndDate.getTime())
			throw new CheckIllicitValueException("validBeginDate should be larger than validEndDate.");
	}
}
