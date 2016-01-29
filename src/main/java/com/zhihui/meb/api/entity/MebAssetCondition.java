package com.zhihui.meb.api.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.jsonmapper.JsonStr2ListDateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2ListDateSerializer;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;

@XmlRootElement(name = "mebAssetCondition")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebAssetCondition {
	@XmlElementWrapper(name = "justOnlyUseChainIds")
	@XmlElement(name = "justOnlyUseChainId")
	private List<Integer> justOnlyUseChainIds;

	@XmlElementWrapper(name = "justOnlyUseChannelSellerIds")
	@XmlElement(name = "justOnlyUseChannelSellerId")
	private List<Integer> justOnlyUseChannelSellerIds;

	@XmlElementWrapper(name = "justOnlyCanUseDates")
	@XmlElement(name = "justOnlyCanUseDate")
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class, type = Date.class)
	@JsonSerialize(using = JsonStr2ListDateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2ListDateDeserializer.class)
	private List<Date> justOnlyCanUseDates;

	@XmlElementWrapper(name = "cantUseChainIds")
	@XmlElement(name = "cantUseChainId")
	private List<Integer> cantUseChainIds;

	@XmlElementWrapper(name = "cantUseChannelSellerIds")
	@XmlElement(name = "cantUseChannelSellerId")
	private List<Integer> cantUseChannelSellerIds;

	@XmlElementWrapper(name = "cantUseDates")
	@XmlElement(name = "cantUseDate")
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class, type = Date.class)
	@JsonSerialize(using = JsonStr2ListDateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2ListDateDeserializer.class)
	private List<Date> cantUseDates;

	// --get,set start here----

	public List<Integer> getJustOnlyUseChainIds() {
		return justOnlyUseChainIds;
	}

	public void setJustOnlyUseChainIds(List<Integer> justOnlyUseChainIds) {
		this.justOnlyUseChainIds = justOnlyUseChainIds;
	}

	public List<Integer> getJustOnlyUseChannelSellerIds() {
		return justOnlyUseChannelSellerIds;
	}

	public void setJustOnlyUseChannelSellerIds(List<Integer> justOnlyUseChannelSellerIds) {
		this.justOnlyUseChannelSellerIds = justOnlyUseChannelSellerIds;
	}

	public List<Date> getJustOnlyCanUseDates() {
		return justOnlyCanUseDates;
	}

	public void setJustOnlyCanUseDates(List<Date> justOnlyCanUseDates) {
		this.justOnlyCanUseDates = justOnlyCanUseDates;
	}

	public List<Integer> getCantUseChainIds() {
		return cantUseChainIds;
	}

	public void setCantUseChainIds(List<Integer> cantUseChainIds) {
		this.cantUseChainIds = cantUseChainIds;
	}

	public List<Integer> getCantUseChannelSellerIds() {
		return cantUseChannelSellerIds;
	}

	public void setCantUseChannelSellerIds(List<Integer> cantUseChannelSellerIds) {
		this.cantUseChannelSellerIds = cantUseChannelSellerIds;
	}

	public List<Date> getCantUseDates() {
		return cantUseDates;
	}

	public void setCantUseDates(List<Date> cantUseDates) {
		this.cantUseDates = cantUseDates;
	}

	// --------funtions
	public boolean checkChainId(Integer chainId) {
		if (chainId == null)
			return true;

		// FALSEEEEEEEEE;
		if (this.justOnlyUseChainIds != null && this.justOnlyUseChainIds.size() > 0) {
			if (!this.justOnlyUseChainIds.contains(chainId))
				return false;
		}
		if (this.cantUseChainIds != null && this.cantUseChainIds.size() > 0) {
			if (!this.cantUseChainIds.contains(chainId))
				return false;
		}

		return true;
	}

	public boolean checkChannelSellerId(Integer channelSellerId) {
		if (channelSellerId == null)
			return true;

		// FALSEEEEEEEEE;
		if (this.justOnlyUseChannelSellerIds != null && this.justOnlyUseChannelSellerIds.size() > 0) {
			if (!this.justOnlyUseChannelSellerIds.contains(channelSellerId))
				return false;
		}
		if (this.cantUseChannelSellerIds != null && this.cantUseChannelSellerIds.size() > 0) {
			if (!this.cantUseChannelSellerIds.contains(channelSellerId))
				return false;
		}

		return true;
	}

	public boolean checkDate(Date beginDate, Date endDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (beginDate == null && endDate == null)
			return true;
		if (beginDate == null)
			beginDate = endDate;
		if (endDate == null)
			endDate = beginDate;

		long bd = beginDate.getTime();
		long ed = endDate.getTime();

		try {
			long checkBd = df.parse(df.format(beginDate)).getTime();
			long checkEd = df.parse(df.format(endDate)).getTime();
			if (checkBd > checkEd)
				return false;
		} catch (Throwable e) {
		}

		// FALSEEEEEEEEE;
		while (bd <= ed) {
			if (this.justOnlyCanUseDates != null && this.justOnlyCanUseDates.size() > 0) {
				boolean have = false;
				for (Date e : this.justOnlyCanUseDates) {
					if (df.format(bd).equalsIgnoreCase(df.format(e))) {
						have = true;
						break;
					}
				}
				if (!have)
					return false;
			}
			if (this.cantUseDates != null && this.cantUseDates.size() > 0) {
				boolean have = false;
				for (Date e : this.cantUseDates) {
					if (df.format(bd).equalsIgnoreCase(df.format(e))) {
						have = true;
						break;
					}
				}
				if (have)
					return false;
			}
			bd = bd + 24 * 60 * 60 * 1000;
		}

		return true;
	}

}
