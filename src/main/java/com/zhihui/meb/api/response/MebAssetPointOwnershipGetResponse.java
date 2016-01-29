package com.zhihui.meb.api.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.api.ApiResponse;
import com.zhihui.meb.api.entity.MebAssetPointOwnership;

@XmlRootElement(name = "apiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MebAssetPointOwnershipGetResponse extends ApiResponse {

	@XmlElementWrapper(name = "mebAssetPointOwnerships")
	@XmlElement(name = "mebAssetPointOwnership")
	private List<MebAssetPointOwnership> mebAssetPointOwnerships;

	public List<MebAssetPointOwnership> getMebAssetPointOwnerships() {
		return mebAssetPointOwnerships;
	}

	public void setMebAssetPointOwnerships(List<MebAssetPointOwnership> mebAssetPointOwnerships) {
		this.mebAssetPointOwnerships = mebAssetPointOwnerships;
	}

}
