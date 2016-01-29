package com.zhihui.meb.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebAssetPointOwnershipModel;
import com.zhihui.meb.model.MebAssetPointSummary;

@Repository
public class MebAssetPointOwnershipDao extends DaoBase {

	private String selectText = "select "//
			+ "MebAssetPointOwnershipId, mebId, MebAssetPointTypeId, "//
			+ "ifnull((select Name from meb_asset_point_type t where o.MebAssetPointTypeId = t.MebAssetPointTypeId),'') as mebAssetPointTypeName, "//
			+ "InitialPoint, ActualPoint, Flag, "//
			+ "ValidBeginDate, ValidEndDate, SellerId, "//
			+ "origPartnerId, origChainId, origOrderId, "//
			+ "tarPartnerId, tarChainId, tarOrderId, "//
			+ "InnerDealInfo, ExtraInfo, CreateTime, CreateOprtId, LastReviseTime, LastReviseOprtId, Remark "//
			+ "from meb_asset_point_ownership o";

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetPointOwnershipModel getById(long id) {
		String sql = this.selectText + " where Flag > 0 and MebAssetPointOwnershipId = " + id + "";
		List<MebAssetPointOwnershipModel> tmps = this.executeFind(sql, MebAssetPointOwnershipModel.class);
		if (tmps.size() <= 0)
			return null;
		else
			return tmps.get(0);
	}

	public List<MebAssetPointOwnershipModel> getByMebId(long mebId, Integer flag) {
		String sql = null;
		// 操作过期SQL
		try {
			sql = "update meb_asset_point_ownership set flag = 4 where flag = 1 and validEndDate < curdate() and mebId = " + mebId + "";
			this.executeUpdate(sql);
		} catch (Throwable e) {
		}
		sql = this.selectText + " where flag > 0 and mebId = " + mebId + "";
		if (flag != null)
			sql = sql + " and flag = " + flag + "";
		return this.executeFind(sql, MebAssetPointOwnershipModel.class);
	}

	/**
	 * 积分合计<br/>
	 * 1、原始记录；2、冻结积分；2、已用积分；4、过期积分<br/>
	 * 垫付冻结Flag=-1，垫付使用Flag=-2才出现负数，其它不会，且垫付记录enddate为9999-12-31不会过期<br/>
	 * flag=1转为flag=4是要程序触发
	 * 
	 * @param mebId
	 * @return
	 */
	public MebAssetPointSummary getSummaryByMebId(long mebId) {
		String sql = "select "//
				+ "cast(ifnull(sum((case when Flag = 1 then InitialPoint else 0 end)),0) as signed) as historicPoint, "//
				+ "cast(ifnull(sum((case when Flag in (1,-1,-2) and ValidEndDate >= curdate() then ActualPoint else 0 end)),0) as signed) as canUsePoint, "//
				+ "cast(ifnull(sum((case when Flag = 2 then ActualPoint else 0 end)),0) as signed) as frozenPoint, "//
				+ "cast(ifnull(sum((case when Flag = 3 then ActualPoint else 0 end)),0) as signed) as haveUsedPoint, "//
				+ "cast(ifnull(sum((case when Flag = 4 or (Flag = 1 and ValidEndDate < curdate()) then ActualPoint else 0 end)),0) as signed) as expiredPoint "//
				+ "from meb_asset_point_ownership "//
				+ "where mebId = " + mebId + "";
		List<MebAssetPointSummary> tmps = this.executeFind(sql, MebAssetPointSummary.class);
		if (tmps.size() <= 0)
			return null;
		else
			return tmps.get(0);
	}

	/**
	 * 使用积分
	 * 
	 * @param mebId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<MebAssetPointOwnershipModel> getByMebIdForUse(long mebId, Date beginDate, Date endDate) {
		// actualPoint < 0 为垫付记录
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 消垫付记录
		String sql = this.selectText + " where flag in (1,-2) and actualPoint <> 0 and mebId = " + mebId + " and validEndDate >= curdate() ";

		if (beginDate != null)
			sql = sql + " and '" + df.format(beginDate) + "' >= validBeginDate ";
		if (endDate != null)
			sql = sql + " and validEndDate >= '" + df.format(endDate) + "' ";

		// 排序
		sql = sql + " order by (case when actualPoint < 0 then -999 else unix_timestamp(validEndDate) - unix_timestamp(curdate()) end) ";
		return this.executeFind(sql, MebAssetPointOwnershipModel.class);
	}
}
