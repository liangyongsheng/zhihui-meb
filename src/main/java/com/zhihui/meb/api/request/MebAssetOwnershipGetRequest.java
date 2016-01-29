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
import com.zhihui.meb.api.response.MebAssetOwnershipGetResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetOwnershipGetRequest extends ApiRequest<MebAssetOwnershipGetResponse> {
	private Long mebId;
	private Integer mebAssetId;
	private Integer mebAssetTypeId;
	private Integer faceValue;
	private Integer chainId;
	private Integer channelSellerId;
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date beginDate;
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date endDate;

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

	public Integer getMebAssetTypeId() {
		return mebAssetTypeId;
	}

	public void setMebAssetTypeId(Integer mebAssetTypeId) {
		this.mebAssetTypeId = mebAssetTypeId;
	}

	public Integer getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getChainId() {
		return chainId;
	}

	public void setChainId(Integer chainId) {
		this.chainId = chainId;
	}

	public Integer getChannelSellerId() {
		return channelSellerId;
	}

	public void setChannelSellerId(Integer channelSellerId) {
		this.channelSellerId = channelSellerId;
	}

	@Override
	public Class<MebAssetOwnershipGetResponse> getResponseType() {
		return MebAssetOwnershipGetResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebId == null || this.mebId <= 0)
			throw new CheckIllicitValueException("field: mebId, value is illicit");

		if (this.beginDate != null && this.endDate != null && this.beginDate.getTime() > this.endDate.getTime())
			throw new CheckIllicitValueException("beginDate should be larger than endDate");
	}
}
