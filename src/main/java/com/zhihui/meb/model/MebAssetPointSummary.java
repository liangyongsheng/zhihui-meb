package com.zhihui.meb.model;

import java.math.BigInteger;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
public class MebAssetPointSummary {
	private BigInteger historicPoint;
	private BigInteger canUsePoint;
	private BigInteger haveUsedPoint;
	private BigInteger frozenPoint;
	private BigInteger expiredPoint;

	public BigInteger getHistoricPoint() {
		return historicPoint;
	}

	public void setHistoricPoint(BigInteger historicPoint) {
		this.historicPoint = historicPoint;
	}

	public BigInteger getCanUsePoint() {
		return canUsePoint;
	}

	public void setCanUsePoint(BigInteger canUsePoint) {
		this.canUsePoint = canUsePoint;
	}

	public BigInteger getHaveUsedPoint() {
		return haveUsedPoint;
	}

	public void setHaveUsedPoint(BigInteger haveUsedPoint) {
		this.haveUsedPoint = haveUsedPoint;
	}

	public BigInteger getFrozenPoint() {
		return frozenPoint;
	}

	public void setFrozenPoint(BigInteger frozenPoint) {
		this.frozenPoint = frozenPoint;
	}

	public BigInteger getExpiredPoint() {
		return expiredPoint;
	}

	public void setExpiredPoint(BigInteger expiredPoint) {
		this.expiredPoint = expiredPoint;
	}

}
