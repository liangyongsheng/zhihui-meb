package com.zhihui.meb.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebAssetOwnershipDao;
import com.zhihui.meb.model.MebAssetOwnershipModel;

@Service
public class MebAssetOwnershipBo extends BoBase {
	@Autowired
	private MebAssetOwnershipDao mebAssetOwnershipDao;

	@Override
	public DaoBase getDao() {
		return this.mebAssetOwnershipDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetOwnershipModel getById(long id) {
		return this.mebAssetOwnershipDao.getById(id);
	}

	public MebAssetOwnershipModel add(MebAssetOwnershipModel mebAssetOwnershipModel) {
		if (mebAssetOwnershipModel.getFlag() == null)
			mebAssetOwnershipModel.setFlag(1);
		if (mebAssetOwnershipModel.getLastReviseTime() == null)
			mebAssetOwnershipModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		if (mebAssetOwnershipModel.getLastReviseOprtId() == null)
			mebAssetOwnershipModel.setLastReviseOprtId(mebAssetOwnershipModel.getCreateOprtId());
		this.mebAssetOwnershipDao.add(mebAssetOwnershipModel);
		return mebAssetOwnershipModel;
	}

	public MebAssetOwnershipModel update(MebAssetOwnershipModel mebAssetOwnershipModel) {
		mebAssetOwnershipModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		this.mebAssetOwnershipDao.update(mebAssetOwnershipModel);
		return mebAssetOwnershipModel;
	}

	public List<MebAssetOwnershipModel> getByCondt(long mebId, Integer mebAssetId, Integer mebAssetTypeId, Integer faceValue, Date beginDate, Date endDate) {
		return this.mebAssetOwnershipDao.getByCondt(mebId, mebAssetId, mebAssetTypeId, faceValue, beginDate, endDate);
	}
}
