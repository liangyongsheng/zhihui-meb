package com.zhihui.meb.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebAssetOwnershipModel;

@Repository
public class MebAssetOwnershipDao extends DaoBase {

	private String selectText = "select "//
			+ "mebAssetOwnershipId, mebId, mebAssetId, "//
			+ "flag, "//
			+ "origPartnerId, origChainId, origOrderId, "//
			+ "tarPartnerId, tarChainId, tarOrderId, "//
			+ "extraInfo, "//
			+ "createTime, createOprtId, lastReviseTime, lastReviseOprtId, remark "//
			+ "from meb_asset_ownership ";

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetOwnershipModel getById(long id) {
		String sql = this.selectText + " where flag > 0 and mebAssetOwnershipId = " + id + "";
		List<MebAssetOwnershipModel> tmp = this.executeFind(sql, MebAssetOwnershipModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebAssetOwnershipModel> getByCondt(long mebId, Integer mebAssetId, Integer mebAssetTypeId, Integer faceValue, Date beginDate, Date endDate) {
		String sql = this.selectText + " where flag > 0 and mebId = " + mebId + "";
		if (mebAssetId != null)
			sql = sql + " and mebAssetId = " + mebAssetId + "";
		if (mebAssetTypeId != null)
			sql = sql + " and mebAssetId in (select mebAssetId from meb_asset where mebAssetTypeId not in(1) and MebAssetTypeId = " + mebAssetTypeId + ")";
		if (faceValue != null)
			sql = sql + " and mebAssetId in (select mebAssetId from meb_asset where mebAssetTypeId not in(1) and FaceValue = " + faceValue + ")";
		if (beginDate != null)
			sql = sql + " and mebAssetId in (select mebAssetId from meb_asset where mebAssetTypeId not in(1) and unix_timestamp(ValidBeginDate) * 1000 >= " + beginDate.getTime() + ")";
		if (endDate != null)
			sql = sql + " and mebAssetId in (select mebAssetId from meb_asset where mebAssetTypeId not in(1) and unix_timestamp(ValidBeginDate) * 1000 <= " + endDate.getTime() + ")";
		return this.executeFind(sql, MebAssetOwnershipModel.class);
	}
}
