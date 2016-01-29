package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebLogModel;

@Repository
public class MebLogDao extends DaoBase {

	private String selectText = "select "//
			+ "MebLogId, MebLogTypeId, "//
			+ "(select name from meb_log_type t where o.MebLogTypeId = t.MebLogTypeId)  as MebLogTypeName, "//
			+ "AfterContent, BeforeContent, CreateTime, CreateOprtId, Remark "//
			+ "from meb_log o ";

	@Override
	@SuppressWarnings("unchecked")
	public MebLogModel getById(long id) {
		String sql = this.selectText + " where MebLogId = " + id + "";
		List<MebLogModel> tmp = this.executeFind(sql, MebLogModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}

}
