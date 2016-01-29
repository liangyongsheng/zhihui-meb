package com.zhihui.meb.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebLogDao;
import com.zhihui.meb.model.MebLogModel;

@Service
public class MebLogBo extends BoBase {
	@Autowired
	private MebLogDao mebLogDao;

	@Override
	public DaoBase getDao() {
		return this.mebLogDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebLogModel getById(long id) {
		return this.mebLogDao.getById(id);
	}

	public MebLogModel add(MebLogModel mebLogModel) {
		this.mebLogDao.add(mebLogModel);
		return mebLogModel;
	}
}
