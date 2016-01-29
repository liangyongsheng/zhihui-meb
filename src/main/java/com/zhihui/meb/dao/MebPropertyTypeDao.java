package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebPropertyTypeModel;

@Repository
public class MebPropertyTypeDao extends DaoBase {

	private String selectText = "select "//
			+ "Id, MebPropertyTypeId, Name, CreateTime, Remark "//
			+ "from meb_property_type ";

	@Override
	@SuppressWarnings("unchecked")
	public MebPropertyTypeModel getById(long id) {
		String sql = this.selectText + " where MebPropertyTypeId = " + id + "";
		List<MebPropertyTypeModel> tmp = this.executeFind(sql, MebPropertyTypeModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebPropertyTypeModel> getAll() {
		return this.executeFind(this.selectText, MebPropertyTypeModel.class);
	}

}
