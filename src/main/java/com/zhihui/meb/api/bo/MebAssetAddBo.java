package com.zhihui.meb.api.bo;

import java.util.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyObj2StrUtils;
import com.zhihui.meb.api.request.MebAssetAddRequest;
import com.zhihui.meb.api.response.MebAssetAddResponse;
import com.zhihui.meb.bo.MebAssetBo;
import com.zhihui.meb.bo.MebAssetTypeBo;
import com.zhihui.meb.model.MebAssetModel;
import com.zhihui.meb.model.MebAssetTypeModel;

@Service
public class MebAssetAddBo extends ApiBo<MebAssetAddRequest> {
	@Autowired
	private MebAssetBo mebAssetBo;
	@Autowired
	private MebAssetTypeBo mebAssetTypeBo;

	@Override
	public Class<MebAssetAddRequest> getRequestType() {
		return MebAssetAddRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetAddResponse rsp = (MebAssetAddResponse) this.apiResponse;
			rsp.setSuccess(false);

			// check: mebAssetTypeId
			MebAssetTypeModel mebAssetTypeModel = this.mebAssetTypeBo.getById(this.apiRequest.getMebAssetTypeId());
			if (mebAssetTypeModel == null)
				throw new BusinessException("field: mebAssetTypeId, the value is not existed.");

			MebAssetModel mebAssetModel = new MebAssetModel();
			mebAssetModel.setMebAssetTypeId(this.apiRequest.getMebAssetTypeId());
			mebAssetModel.setName(this.apiRequest.getName());
			mebAssetModel.setFaceValue(this.apiRequest.getFaceValue());
			mebAssetModel.setValidBeginDate(new java.sql.Date(this.apiRequest.getValidBeginDate().getTime()));
			mebAssetModel.setValidEndDate(new java.sql.Date(this.apiRequest.getValidEndDate().getTime()));
			if (this.apiRequest.getMebAssetCondition() != null)
				mebAssetModel.setConditionJson(MyObj2StrUtils.toJson(this.apiRequest.getMebAssetCondition(), 0));
			mebAssetModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebAssetModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebAssetModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebAssetModel.setRemark(this.apiRequest.getRemark());
			this.mebAssetBo.add(mebAssetModel);

			rsp.setMebAssetId(mebAssetModel.getMebAssetId());
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
