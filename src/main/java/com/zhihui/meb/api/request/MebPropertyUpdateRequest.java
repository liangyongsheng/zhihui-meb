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
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.meb.api.response.MebPropertyUpdateResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebPropertyUpdateRequest extends ApiRequest<MebPropertyUpdateResponse> {
	private Long mebPropertyId;
	private String value;
	private String remark;

	public Long getMebPropertyId() {
		return mebPropertyId;
	}

	public void setMebPropertyId(Long mebPropertyId) {
		this.mebPropertyId = mebPropertyId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Class<MebPropertyUpdateResponse> getResponseType() {
		return MebPropertyUpdateResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebPropertyId == null || this.mebPropertyId <= 0)
			throw new CheckIllicitValueException("field: mebPropertyId, value is illicit.");

		if (MyStringUtils.isEmpty(this.value))
			throw new CheckEmptyException("field: value is empty.");
		this.value = this.value.trim();
	}
}
