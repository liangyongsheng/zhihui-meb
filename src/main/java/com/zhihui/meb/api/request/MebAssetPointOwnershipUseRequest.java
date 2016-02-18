package com.zhihui.meb.api.request;

import java.util.Date;

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
import com.zhihui.meb.api.response.MebAssetPointOwnershipUseResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetPointOwnershipUseRequest extends ApiRequest<MebAssetPointOwnershipUseResponse> {
	private Long mebId;
	private Integer point;
	private Date beginDate;
	private Date endDate;
	private Long tarOrderId;
	private String extraInfo;
	private String remark;

	public Long getMebId() {
		return mebId;
	}

	public void setMebId(Long mebId) {
		this.mebId = mebId;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
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

	public Long getTarOrderId() {
		return tarOrderId;
	}

	public void setTarOrderId(Long tarOrderId) {
		this.tarOrderId = tarOrderId;
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
	public Class<MebAssetPointOwnershipUseResponse> getResponseType() {
		return MebAssetPointOwnershipUseResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebId == null || this.mebId <= 0)
			throw new CheckIllicitValueException("field: mebId, value is illicit.");

		if (this.point == null || this.point <= 0)
			throw new CheckIllicitValueException("field: point, value is illicit.");

		if (this.beginDate == null)
			throw new CheckIllicitValueException("field: beginDate, value is illicit.");

		if (this.endDate == null)
			throw new CheckIllicitValueException("field: endDate, value is illicit.");

		if (this.tarOrderId == null || this.tarOrderId <= 0)
			throw new CheckIllicitValueException("field: tarOrderId, value is illicit.");

		if (MyStringUtils.isEmpty(this.remark))
			throw new CheckEmptyException("field: remark, value is empty.");
	}

}
