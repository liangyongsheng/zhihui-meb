package com.zhihui.meb.api.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.api.ApiResponse;


@XmlRootElement(name = "apiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetPointOwnershipSummaryResponse extends ApiResponse {
	private Long mebId;
	private Long historicPoint;
	private Long canUsePoint;
	private Long haveUsedPoint;
	private Long frozenPoint;
	private Long expiredPoint;

	public Long getMebId() {
		return mebId;
	}

	public void setMebId(Long mebId) {
		this.mebId = mebId;
	}

	public Long getHistoricPoint() {
		return historicPoint;
	}

	public void setHistoricPoint(Long historicPoint) {
		this.historicPoint = historicPoint;
	}

	public Long getCanUsePoint() {
		return canUsePoint;
	}

	public void setCanUsePoint(Long canUsePoint) {
		this.canUsePoint = canUsePoint;
	}

	public Long getHaveUsedPoint() {
		return haveUsedPoint;
	}

	public void setHaveUsedPoint(Long haveUsedPoint) {
		this.haveUsedPoint = haveUsedPoint;
	}

	public Long getFrozenPoint() {
		return frozenPoint;
	}

	public void setFrozenPoint(Long frozenPoint) {
		this.frozenPoint = frozenPoint;
	}

	public Long getExpiredPoint() {
		return expiredPoint;
	}

	public void setExpiredPoint(Long expiredPoint) {
		this.expiredPoint = expiredPoint;
	}

}
