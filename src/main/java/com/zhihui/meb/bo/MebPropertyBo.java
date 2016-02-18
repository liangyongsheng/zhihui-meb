package com.zhihui.meb.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebPropertyDao;
import com.zhihui.meb.model.MebPropertyModel;

@Service
public class MebPropertyBo extends BoBase {
	@Autowired
	private MebPropertyDao mebPropertyDao;

	@Override
	public DaoBase getDao() {
		return this.mebPropertyDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebPropertyModel getById(long id) {
		return this.mebPropertyDao.getById(id);
	}

	public MebPropertyModel add(MebPropertyModel mebPropertyModel) {
		if (mebPropertyModel.getFlag() == null)
			mebPropertyModel.setFlag(1);
		if (mebPropertyModel.getLastReviseTime() == null)
			mebPropertyModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		if (mebPropertyModel.getLastReviseOprtId() == null)
			mebPropertyModel.setLastReviseOprtId(mebPropertyModel.getCreateOprtId());
		this.mebPropertyDao.add(mebPropertyModel);
		return mebPropertyModel;
	}

	public MebPropertyModel update(MebPropertyModel mebPropertyModel) {
		mebPropertyModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		this.mebPropertyDao.update(mebPropertyModel);
		return mebPropertyModel;
	}

	public List<MebPropertyModel> getByMebId(long mebId, Integer mebPropertyTypeId) {
		return this.mebPropertyDao.getByMebId(mebId, mebPropertyTypeId);
	}

	public List<MebPropertyModel> getByValue(int mebPropertTypeId, String value) {
		return this.mebPropertyDao.getByValue(mebPropertTypeId, value);
	}
}
