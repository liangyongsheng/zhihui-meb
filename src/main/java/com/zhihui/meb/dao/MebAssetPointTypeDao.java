package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebAssetPointTypeModel;

@Repository
public class MebAssetPointTypeDao extends DaoBase {

	private String selectText = "select "//
			+ "Id, MebAssetPointTypeId, Name, InnerOnly, CreateTime, Remark "//
			+ "from meb_asset_point_type ";

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetPointTypeModel getById(long id) {
		String sql = this.selectText + " where MebAssetPointTypeId = " + id + "";
		List<MebAssetPointTypeModel> tmp = this.executeFind(sql, MebAssetPointTypeModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebAssetPointTypeModel> getAll() {
		return this.executeFind(this.selectText, MebAssetPointTypeModel.class);
	}

}
