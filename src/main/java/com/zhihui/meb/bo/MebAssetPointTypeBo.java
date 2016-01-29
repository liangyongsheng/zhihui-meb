package com.zhihui.meb.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.meb.dao.MebAssetPointTypeDao;
import com.zhihui.meb.model.MebAssetPointTypeModel;

@Service
public class MebAssetPointTypeBo extends BoBase {
	@Autowired
	private MebAssetPointTypeDao mebAssetPointTypeDao;

	@Override
	public DaoBase getDao() {
		return this.mebAssetPointTypeDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MebAssetPointTypeModel getById(long id) {
		return this.mebAssetPointTypeDao.getById(id);
	}

	public List<MebAssetPointTypeModel> getAll() {
		return this.mebAssetPointTypeDao.getAll();
	}
}
