package com.zhihui.meb.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebPropertyTypeDao;
import com.zhihui.meb.model.MebPropertyTypeModel;

@Service
public class MebPropertyTypeBo extends BoBase {
	@Autowired
	private MebPropertyTypeDao mebPropertyTypeDao;

	@Override
	public DaoBase getDao() {
		return this.mebPropertyTypeDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebPropertyTypeModel getById(long id) {
		return this.mebPropertyTypeDao.getById(id);
	}

	public List<MebPropertyTypeModel> getAll() {
		return this.mebPropertyTypeDao.getAll();
	}

}
