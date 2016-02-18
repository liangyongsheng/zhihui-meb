package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.meb.api.request.MebAssetPointOwnershipAddRequest;
import com.zhihui.meb.api.response.MebAssetPointOwnershipAddResponse;
import com.zhihui.meb.bo.MebAssetPointOwnershipBo;
import com.zhihui.meb.bo.MebAssetPointTypeBo;
import com.zhihui.meb.model.MebAssetPointOwnershipModel;
import com.zhihui.meb.model.MebAssetPointTypeModel;

@Service
public class MebAssetPointOwnershipAddBo extends ApiBo<MebAssetPointOwnershipAddRequest> {
	@Autowired
	private MebAssetPointTypeBo mebAssetPointTypeBo;
	@Autowired
	private MebAssetPointOwnershipBo mebAssetPointOwnershipBo;

	@Override
	public Class<MebAssetPointOwnershipAddRequest> getRequestType() {
		return MebAssetPointOwnershipAddRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetPointOwnershipAddResponse rsp = (MebAssetPointOwnershipAddResponse) this.apiResponse;
			rsp.setSuccess(false);

			MebAssetPointTypeModel mebAssetPointTypeModel = this.mebAssetPointTypeBo.getById(this.apiRequest.getMebAssetPointTypeId());
			if (mebAssetPointTypeModel == null)
				throw new BusinessException("不存在此积分权益类型。");
			if (mebAssetPointTypeModel.getInnerOnly() != null && mebAssetPointTypeModel.getInnerOnly() == true)
				throw new BusinessException("此积分权益类型，只用于内部程序处理。");

			MebAssetPointOwnershipModel mebAssetPointOwnershipModel = new MebAssetPointOwnershipModel();
			mebAssetPointOwnershipModel.setMebId(this.apiRequest.getMebId());
			mebAssetPointOwnershipModel.setMebAssetPointTypeId(this.apiRequest.getMebAssetPointTypeId());
			mebAssetPointOwnershipModel.setInitialPoint(this.apiRequest.getPoint());
			mebAssetPointOwnershipModel.setActualPoint(this.apiRequest.getPoint());
			mebAssetPointOwnershipModel.setValidBeginDate(new java.sql.Date(this.apiRequest.getValidBeginDate().getTime()));
			mebAssetPointOwnershipModel.setValidEndDate(new java.sql.Date(this.apiRequest.getValidEndDate().getTime()));
			mebAssetPointOwnershipModel.setSellerId(this.apiRequest.getSellerId());
			mebAssetPointOwnershipModel.setOrigOrderId(this.apiRequest.getOrigOrderId());
			mebAssetPointOwnershipModel.setFlag(1);
			mebAssetPointOwnershipModel.setExtraInfo(this.apiRequest.getExtraInfo());
			mebAssetPointOwnershipModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebAssetPointOwnershipModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebAssetPointOwnershipModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebAssetPointOwnershipModel.setRemark(this.apiRequest.getRemark());
			this.mebAssetPointOwnershipBo.add(mebAssetPointOwnershipModel);

			rsp.setMebAssetPointOwnershipId(mebAssetPointOwnershipModel.getMebAssetPointOwnershipId());
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
