package com.zhihui.meb.api.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.api.ApiResponse;
import com.zhihui.meb.api.entity.MebAssetOwnership;

@XmlRootElement(name = "apiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetOwnershipGetResponse extends ApiResponse {
	@XmlElementWrapper(name = "mebAssetOwnerships")
	@XmlElement(name = "mebAssetOwnership")
	private List<MebAssetOwnership> mebAssetOwnerships;

	public List<MebAssetOwnership> getMebAssetOwnerships() {
		return mebAssetOwnerships;
	}

	public void setMebAssetOwnerships(List<MebAssetOwnership> mebAssetOwnerships) {
		this.mebAssetOwnerships = mebAssetOwnerships;
	}

}
