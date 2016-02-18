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
import com.zhihui.core.exception.CheckException;
import com.zhihui.core.exception.CheckIllicitValueException;
import com.zhihui.core.jsonmapper.JsonStr2DateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DateSerializer;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;
import com.zhihui.meb.api.response.MebAssetPointOwnershipAddResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetPointOwnershipAddRequest extends ApiRequest<MebAssetPointOwnershipAddResponse> {
	private Long mebId;
	private Integer mebAssetPointTypeId;
	private Integer point;

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
	private String extraInfo;
	private String remark;

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

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
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

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Class<MebAssetPointOwnershipAddResponse> getResponseType() {
		return MebAssetPointOwnershipAddResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebId == null || this.mebId <= 0)
			throw new CheckIllicitValueException("field: mebId, value is illicit.");

		if (this.mebAssetPointTypeId == null || this.mebAssetPointTypeId <= 0)
			throw new CheckIllicitValueException("field: mebAssetPointTypeId, value is illicit.");

		if (this.point == null || this.point <= 0)
			throw new CheckIllicitValueException("field: point, value is illicit.");

		if (this.validBeginDate == null)
			throw new CheckIllicitValueException("field: validBeginDate, value is illicit.");

		if (this.validEndDate == null)
			throw new CheckIllicitValueException("field: validEndDate, value is illicit.");
	}
}
