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
import com.zhihui.meb.api.response.MebAssetOwnershipDeleteResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetOwnershipDeleteRequest extends ApiRequest<MebAssetOwnershipDeleteResponse> {
	private Long mebAssetOwnershipId;
	private String remark;

	public Long getMebAssetOwnershipId() {
		return mebAssetOwnershipId;
	}

	public void setMebAssetOwnershipId(Long mebAssetOwnershipId) {
		this.mebAssetOwnershipId = mebAssetOwnershipId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Class<MebAssetOwnershipDeleteResponse> getResponseType() {
		return MebAssetOwnershipDeleteResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebAssetOwnershipId == null || this.mebAssetOwnershipId <= 0)
			throw new CheckIllicitValueException("field: mebAssetOwnershipId, value is illicit.");

		if (MyStringUtils.isEmpty(this.remark))
			throw new CheckEmptyException("field: remark, value is empty.");
	}
}
