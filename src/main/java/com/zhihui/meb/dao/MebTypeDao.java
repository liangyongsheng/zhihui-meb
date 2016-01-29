package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebTypeModel;

@Repository
public class MebTypeDao extends DaoBase {

	private String selectText = "select "//
			+ "Id, MebTypeId, Name, CreateTime, Remark "//
			+ "from meb_type ";

	@Override
	@SuppressWarnings("unchecked")
	public MebTypeModel getById(long id) {
		String sql = this.selectText + " where MebTypeId = " + id + "";
		List<MebTypeModel> tmp = this.executeFind(sql, MebTypeModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebTypeModel> getAll() {
		return this.executeFind(this.selectText, MebTypeModel.class);
	}

}
