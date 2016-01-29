package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyObj2StrUtils;
import com.zhihui.meb.api.request.MebAssetUpdateRequest;
import com.zhihui.meb.api.response.MebAssetUpdateResponse;
import com.zhihui.meb.bo.MebAssetBo;
import com.zhihui.meb.model.MebAssetModel;

@Service
public class MebAssetUpdateBo extends ApiBo<MebAssetUpdateRequest> {
	@Autowired
	private MebAssetBo mebAssetBo;

	@Override
	public Class<MebAssetUpdateRequest> getRequestType() {
		return MebAssetUpdateRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetUpdateResponse rsp = (MebAssetUpdateResponse) this.apiResponse;
			rsp.setSuccess(false);
			// check: mebAssetId
			MebAssetModel mebAssetModel = this.mebAssetBo.getById(this.apiRequest.getMebAssetId());
			if (mebAssetModel == null)
				throw new BusinessException("field: mebAssetId, the value is not existed");

			mebAssetModel.setName(this.apiRequest.getName());
			mebAssetModel.setValidBeginDate(new java.sql.Date(this.apiRequest.getValidBeginDate().getTime()));
			mebAssetModel.setValidEndDate(new java.sql.Date(this.apiRequest.getValidEndDate().getTime()));
			if (this.apiRequest.getMebAssetCondition() != null)
				mebAssetModel.setConditionJson(MyObj2StrUtils.toJson(this.apiRequest.getMebAssetCondition(), 0));
			mebAssetModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebAssetModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebAssetModel.setRemark(this.apiRequest.getRemark());
			this.mebAssetBo.update(mebAssetModel);

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
