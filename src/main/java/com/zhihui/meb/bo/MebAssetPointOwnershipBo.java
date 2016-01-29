package com.zhihui.meb.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebAssetPointOwnershipDao;
import com.zhihui.meb.model.MebAssetPointOwnershipModel;
import com.zhihui.meb.model.MebAssetPointSummary;

@Service
public class MebAssetPointOwnershipBo extends BoBase {
	@Autowired
	private MebAssetPointOwnershipDao mebAssetPointOwnershipDao;

	@Override
	public DaoBase getDao() {
		return this.mebAssetPointOwnershipDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetPointOwnershipModel getById(long id) {
		return this.mebAssetPointOwnershipDao.getById(id);
	}

	public MebAssetPointOwnershipModel add(MebAssetPointOwnershipModel mebAssetPointOwnershipModel) {
		if (mebAssetPointOwnershipModel.getActualPoint() == null)
			mebAssetPointOwnershipModel.setActualPoint(mebAssetPointOwnershipModel.getInitialPoint());
		if (mebAssetPointOwnershipModel.getLastReviseTime() == null)
			mebAssetPointOwnershipModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		if (mebAssetPointOwnershipModel.getLastReviseOprtId() == null)
			mebAssetPointOwnershipModel.setLastReviseOprtId(mebAssetPointOwnershipModel.getCreateOprtId());
		this.mebAssetPointOwnershipDao.add(mebAssetPointOwnershipModel);
		return mebAssetPointOwnershipModel;
	}

	public MebAssetPointOwnershipModel update(MebAssetPointOwnershipModel mebAssetPointOwnershipModel) {
		if (mebAssetPointOwnershipModel.getLastReviseTime() == null)
			mebAssetPointOwnershipModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		this.mebAssetPointOwnershipDao.update(mebAssetPointOwnershipModel);
		return mebAssetPointOwnershipModel;
	}

	public List<MebAssetPointOwnershipModel> getByMebId(long mebId, Integer flag) {
		return this.mebAssetPointOwnershipDao.getByMebId(mebId, flag);
	}

	public MebAssetPointSummary getSummaryByMebId(long mebId) {
		return this.mebAssetPointOwnershipDao.getSummaryByMebId(mebId);
	}

	public List<MebAssetPointOwnershipModel> getByMebIdForUse(long mebId, Date beginDate, Date endDate) {
		return this.mebAssetPointOwnershipDao.getByMebIdForUse(mebId, beginDate, endDate);
	}

}
