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
import com.zhihui.core.exception.CheckEmptyException;
import com.zhihui.core.exception.CheckException;
import com.zhihui.core.exception.CheckIllicitValueException;
import com.zhihui.core.util.MyIdCardUtils;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.meb.api.response.MebAddResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAddRequest extends ApiRequest<MebAddResponse> {
	private String name;
	private Integer channelSellerId;
	private Integer sellerId;
	private String idCard; // only for mainland
	private String mobile; // only for mainland
	private String email;
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChannelSellerId() {
		return channelSellerId;
	}

	public void setChannelSellerId(Integer channelSellerId) {
		this.channelSellerId = channelSellerId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Class<MebAddResponse> getResponseType() {
		return MebAddResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (MyStringUtils.isEmpty(this.name))
			throw new CheckEmptyException("field: name is empty");

		if (this.sellerId == null || this.sellerId <= 0)
			throw new CheckIllicitValueException("field: sellerId is illicit");

		if (MyStringUtils.isEmpty(this.idCard))
			throw new CheckEmptyException("field: idCard is empty");
		if (!MyIdCardUtils.validateCard(this.idCard.trim().toUpperCase()))
			throw new CheckIllicitValueException("field: idCard is illicit");
		this.idCard = this.idCard.trim().toUpperCase();

		if (MyStringUtils.isEmpty(this.mobile))
			throw new CheckEmptyException("field: mobile is empty");
		if (!this.mobile.trim().matches("1[3458][0-9]{9}"))
			throw new CheckIllicitValueException("field: mobile is illicit");
		this.mobile = this.mobile.trim();

		if (!MyStringUtils.isEmpty(this.email)) {
			if (!(this.email.trim().matches("[a-zA-Z\\.@]+") && this.email.trim().matches("[^@]+@[^@]+")))
				throw new CheckIllicitValueException("field: email is illicit");
		}

		this.email = this.email == null ? this.email : this.email.trim();
		if (this.channelSellerId == null || this.channelSellerId <= 0)
			this.channelSellerId = this.sellerId;
	}
}
