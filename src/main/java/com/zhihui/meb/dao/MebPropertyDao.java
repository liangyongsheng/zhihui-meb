package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebPropertyModel;

@Repository
public class MebPropertyDao extends DaoBase {

	private String selectText = "select "//
			+ "MebPropertyId, MebId, MebPropertyTypeId, "//
			+ "(select name from meb_property_type t where o.MebPropertyTypeId = t.MebPropertyTypeId)  as MebPropertyTypeName, "//
			+ "Value, Flag, CreateTime, CreateOprtId, LastReviseTime, LastReviseOprtId, Remark "//
			+ "from meb_property o ";

	@Override
	@SuppressWarnings("unchecked")
	public MebPropertyModel getById(long id) {
		String sql = this.selectText + " where Flag > 0 and MebPropertyId = " + id + "";
		List<MebPropertyModel> tmp = this.executeFind(sql, MebPropertyModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebPropertyModel> getByMebId(long mebId, Integer mebPropertyTypeId) {
		String sql = this.selectText + " where Flag > 0 and MebId = " + mebId + "";
		if (mebPropertyTypeId != null)
			sql = sql + " MebPropertyTypeId = " + mebPropertyTypeId + "";
		List<MebPropertyModel> rs = this.executeFind(sql, MebPropertyModel.class);
		return rs;
	}

	public List<MebPropertyModel> getByValue(int mebPropertTypeId, String value) {
		value = value == null ? "" : value;
		value = value.replace("'", "''");
		String sql = this.selectText + " where Flag > 0 and MebPropertyTypeId = " + mebPropertTypeId + " and Value = '" + value + "'";
		List<MebPropertyModel> rs = this.executeFind(sql, MebPropertyModel.class);
		return rs;
	}
}
