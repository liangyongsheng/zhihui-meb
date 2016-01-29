package com.zhihui.meb.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebTypeDao;
import com.zhihui.meb.model.MebTypeModel;

@Service
public class MebTypeBo extends BoBase {
	@Autowired
	private MebTypeDao mebTypeDao;

	@Override
	public DaoBase getDao() {
		return this.mebTypeDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebTypeModel getById(long id) {
		return this.mebTypeDao.getById(id);
	}

	public List<MebTypeModel> getAll() {
		return this.mebTypeDao.getAll();
	}

}
