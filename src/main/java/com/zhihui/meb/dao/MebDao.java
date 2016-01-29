package com.zhihui.meb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.model.MebModel;

@Repository
public class MebDao extends DaoBase {

	private String selectText = "select "//
			+ "MebId, MebTypeId, "//
			+ "ifnull((select Name from meb_type t where o.MebTypeId = t.MebTypeId),'') as MebTypeName, "//
			+ "Name, Gender, Birthday, "//
			+ "ChannelSellerId, "//
			+ "SellerId, "//
			+ "Flag, CreateTime, CreateOprtId, LastReviseTime, LastReviseOprtId, Remark "//
			+ "from meb o ";

	@Override
	@SuppressWarnings("unchecked")
	public MebModel getById(long id) {
		String sql = this.selectText + " where Flag > 0 and MebId = " + id + "";
		List<MebModel> tmp = this.executeFind(sql, MebModel.class);
		if (tmp.size() <= 0)
			return null;
		else
			return tmp.get(0);
	}
}
