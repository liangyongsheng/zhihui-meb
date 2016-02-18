package com.zhihui.meb.api.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.api.ApiRequest;
import com.zhihui.core.exception.AsignException;
import com.zhihui.core.exception.CheckException;
import com.zhihui.core.exception.CheckIllicitValueException;
import com.zhihui.meb.api.response.MebAssetOwnershipAddResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetOwnershipAddRequest extends ApiRequest<MebAssetOwnershipAddResponse> {
	private Long mebId;
	private Integer mebAssetId;
	private Long origOrderId;
	private Integer sellerId;
	private String extraInfo;
	private String remark;

	public Long getMebId() {
		return mebId;
	}

	public void setMebId(Long mebId) {
		this.mebId = mebId;
	}

	public Integer getMebAssetId() {
		return mebAssetId;
	}

	public void setMebAssetId(Integer mebAssetId) {
		this.mebAssetId = mebAssetId;
	}

	public Long getOrigOrderId() {
		return origOrderId;
	}

	public void setOrigOrderId(Long origOrderId) {
		this.origOrderId = origOrderId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
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
	public Class<MebAssetOwnershipAddResponse> getResponseType() {
		return MebAssetOwnershipAddResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebId == null || this.mebId <= 0)
			throw new CheckIllicitValueException("field: mebId, value is illicit.");

		if (this.mebAssetId == null || this.mebAssetId <= 0)
			throw new CheckIllicitValueException("field: mebAssetId, value is illicit.");
	}
}
