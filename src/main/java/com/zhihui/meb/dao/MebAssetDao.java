package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebAssetModel;

@Repository
public class MebAssetDao extends DaoBase {

	private String selectText = "select "//
			+ "MebAssetId, MebAssetTypeId, "//
			+ "ifnull((select Name from asset_type t where o.MebAssetTypeId = t.MebAssetTypeId),'') as MebAssetTypeName, "//
			+ "Name, FaceValue, ValidBeginDate, "//
			+ "ValidEndDate, Condition, "//
			+ "CreateTime, CreateOprtId, LastReviseTime, LastReviseOprtId, Remark "//
			+ "from asset o ";

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetModel getById(long id) {
		String sql = this.selectText + " where MebAssetId = " + id + "";
		List<MebAssetModel> tmp = this.executeFind(sql, MebAssetModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebAssetModel> getByIds(List<Long> ids) {
		String idStr = "";
		if (ids == null || ids.size() <= 0)
			idStr = "0";
		else {
			for (int i = 0; i < ids.size(); i++) {
				if (i == 0)
					idStr = idStr + String.valueOf(ids.get(i));
				else
					idStr = idStr + "," + String.valueOf(ids.get(i));
			}
		}
		String sql = this.selectText + " where MebAssetId in (" + idStr + ")";
		return this.executeFind(sql, MebAssetModel.class);
	}

	public List<MebAssetModel> getAll() {
		return this.executeFind(this.selectText, MebAssetModel.class);
	}

}
