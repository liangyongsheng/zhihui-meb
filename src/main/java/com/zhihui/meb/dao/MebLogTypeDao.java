package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebLogTypeModel;

@Repository
public class MebLogTypeDao extends DaoBase {

	private String selectText = "select "//
			+ "Id, MebLogTypeId, Name, CreateTime, Remark "//
			+ "from meb_log_type ";

	@Override
	@SuppressWarnings("unchecked")
	public MebLogTypeModel getById(long id) {
		String sql = this.selectText + " where MebLogTypeId = " + id + "";
		List<MebLogTypeModel> tmp = this.executeFind(sql, MebLogTypeModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

	public List<MebLogTypeModel> getAll() {
		return this.executeFind(this.selectText, MebLogTypeModel.class);
	}

}
