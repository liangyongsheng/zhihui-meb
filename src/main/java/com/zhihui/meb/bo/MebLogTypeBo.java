package com.zhihui.meb.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebLogTypeDao;
import com.zhihui.meb.model.MebLogTypeModel;

@Service
public class MebLogTypeBo extends BoBase {
	@Autowired
	private MebLogTypeDao mebLogTypeDao;

	@Override
	public DaoBase getDao() {
		return this.mebLogTypeDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebLogTypeModel getById(long id) {
		return this.mebLogTypeDao.getById(id);
	}

	public List<MebLogTypeModel> getAll() {
		return this.mebLogTypeDao.getAll();
	}
}
