package com.zhihui.meb.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebAssetTypeDao;
import com.zhihui.meb.model.MebAssetTypeModel;

@Service
public class MebAssetTypeBo extends BoBase {
	@Autowired
	private MebAssetTypeDao mebAssetTypeDao;

	@Override
	public DaoBase getDao() {
		return this.mebAssetTypeDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetTypeModel getById(long id) {
		return this.mebAssetTypeDao.getById(id);
	}

}
