package com.zhihui.meb.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebAssetDao;
import com.zhihui.meb.model.MebAssetModel;

@Service
public class MebAssetBo extends BoBase {
	@Autowired
	private MebAssetDao mebAssetDao;

	@Override
	public DaoBase getDao() {
		return this.mebAssetDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetModel getById(long id) {
		return this.mebAssetDao.getById(id);
	}

	public List<MebAssetModel> getByIds(List<Long> ids) {
		return this.mebAssetDao.getByIds(ids);
	}

	public List<MebAssetModel> getAll() {
		return this.mebAssetDao.getAll();
	}

	public MebAssetModel add(MebAssetModel mebAssetModel) {
		if (mebAssetModel.getLastReviseTime() == null)
			mebAssetModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		if (mebAssetModel.getLastReviseOprtId() == null)
			mebAssetModel.setLastReviseOprtId(mebAssetModel.getCreateOprtId());
		this.mebAssetDao.add(mebAssetModel);
		return mebAssetModel;
	}

	public MebAssetModel update(MebAssetModel mebAssetModel) {
		mebAssetModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		this.mebAssetDao.update(mebAssetModel);
		return mebAssetModel;
	}

}
