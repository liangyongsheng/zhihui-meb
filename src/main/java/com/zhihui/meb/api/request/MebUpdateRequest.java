package com.zhihui.meb.api.request;

import java.text.SimpleDateFormat;
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
import com.zhihui.core.util.MyIdCardUtils;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;
import com.zhihui.meb.api.response.MebUpdateResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebUpdateRequest extends ApiRequest<MebUpdateResponse> {
	private Long mebId;
	private String name;
	private Integer gender;
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date birthday;
	private String remark;

	public Long getMebId() {
		return mebId;
	}

	public void setMebId(Long mebId) {
		this.mebId = mebId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Class<MebUpdateResponse> getResponseType() {
		return MebUpdateResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebId != null && this.mebId <= 0)
			throw new CheckIllicitValueException("field: mebId, value is illicit");

		if (this.name != null && MyStringUtils.isEmpty(this.name))
			throw new CheckIllicitValueException("field: name, value is illicit");

		if (this.gender != null && (this.gender <= 0 || this.gender >= 4))
			throw new CheckIllicitValueException("field: gender, value is illicit");

		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		if (this.birthday != null) {
			try {
				int year = Integer.parseInt(df.format(this.birthday));
				int yearNow = Integer.parseInt(df.format(new Date()));
				if (year < MyIdCardUtils.MAINLAND_MIN_YEAR || year >= yearNow)
					throw new CheckIllicitValueException("field: birthday, value is illicit");
			} catch (Throwable e) {
				throw new CheckIllicitValueException(e);
			}
		}
	}
}
