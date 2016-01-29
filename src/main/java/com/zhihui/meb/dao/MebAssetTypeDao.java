package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebAssetTypeModel;

@Repository
public class MebAssetTypeDao extends DaoBase {

	private String selectText = "select "//
			+ "Id, MebAssetTypeId, Name, CreateTime, Remark "//
			+ "from meb_asset_type ";

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetTypeModel getById(long id) {
		String sql = this.selectText + " where MebAssetTypeId = " + id + "";
		List<MebAssetTypeModel> tmp = this.executeFind(sql, MebAssetTypeModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebAssetTypeModel> getAll() {
		return this.executeFind(this.selectText, MebAssetTypeModel.class);
	}

}
