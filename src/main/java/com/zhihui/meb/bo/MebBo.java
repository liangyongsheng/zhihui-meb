package com.zhihui.meb.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebDao;
import com.zhihui.meb.model.MebModel;

@Service
public class MebBo extends BoBase {
	@Autowired
	private MebDao mebDao;

	@Override
	public DaoBase getDao() {
		return this.mebDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebModel getById(long id) {
		return this.mebDao.getById(id);
	}

	public MebModel add(MebModel mebModel) {
		if (mebModel.getMebTypeId() == null)
			mebModel.setMebTypeId(1);
		if (mebModel.getGender() == null)
			mebModel.setGender(1);
		if (mebModel.getFlag() == null)
			mebModel.setFlag(1);
		if (mebModel.getLastReviseTime() == null)
			mebModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		if (mebModel.getLastReviseOprtId() == null)
			mebModel.setLastReviseOprtId(mebModel.getCreateOprtId());
		this.mebDao.add(mebModel);
		return mebModel;
	}

	public MebModel update(MebModel mebModel) {
		mebModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		this.mebDao.update(mebModel);
		return mebModel;
	}

}
