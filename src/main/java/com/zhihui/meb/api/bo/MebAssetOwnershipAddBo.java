package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.context.MyContext;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.meb.api.request.MebAssetOwnershipAddRequest;
import com.zhihui.meb.api.request.MebAssetPointOwnershipAddRequest;
import com.zhihui.meb.api.response.MebAssetOwnershipAddResponse;
import com.zhihui.meb.api.response.MebAssetPointOwnershipAddResponse;
import com.zhihui.meb.bo.MebAssetBo;
import com.zhihui.meb.bo.MebAssetOwnershipBo;
import com.zhihui.meb.model.MebAssetModel;
import com.zhihui.meb.model.MebAssetOwnershipModel;

@Service
public class MebAssetOwnershipAddBo extends ApiBo<MebAssetOwnershipAddRequest> {
	@Autowired
	private MebAssetBo mebAssetBo;
	@Autowired
	private MebAssetOwnershipBo mebAssetOwnershipBo;

	@Override
	public Class<MebAssetOwnershipAddRequest> getRequestType() {
		return MebAssetOwnershipAddRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetOwnershipAddResponse rsp = (MebAssetOwnershipAddResponse) this.apiResponse;
			rsp.setSuccess(false);

			Long businessId = null;
			// check: mebAssetId
			MebAssetModel mebAssetModel = this.mebAssetBo.getById(this.apiRequest.getMebAssetId());
			if (mebAssetModel == null)
				throw new BusinessException("不存在此权益。");

			// 非积分权益添加
			if (mebAssetModel.getMebAssetTypeId() != 1) {
				MebAssetOwnershipModel mebAssetOwnershipModel = new MebAssetOwnershipModel();
				mebAssetOwnershipModel.setMebId(this.apiRequest.getMebId());
				mebAssetOwnershipModel.setMebAssetId(this.apiRequest.getMebAssetId());
				mebAssetOwnershipModel.setOrigOrderId(this.apiRequest.getOrigOrderId());
				mebAssetOwnershipModel.setFlag(1);
				mebAssetOwnershipModel.setExtraInfo(this.apiRequest.getExtraInfo());
				mebAssetOwnershipModel.setCreateOprtId(this.apiRequest.getOprtId());
				mebAssetOwnershipModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
				mebAssetOwnershipModel.setLastReviseOprtId(this.apiRequest.getOprtId());
				mebAssetOwnershipModel.setRemark(this.apiRequest.getRemark());
				this.mebAssetOwnershipBo.add(mebAssetOwnershipModel);
				businessId = mebAssetOwnershipModel.getMebAssetOwnershipId();
			} else {
				// 积分权益添加
				MebAssetPointOwnershipAddRequest apiRequest = new MebAssetPointOwnershipAddRequest();
				apiRequest.setMebId(this.apiRequest.getMebId());
				apiRequest.setMebAssetPointTypeId(11);
				apiRequest.setPoint(mebAssetModel.getFaceValue());
				apiRequest.setValidBeginDate(mebAssetModel.getValidBeginDate());
				apiRequest.setValidEndDate(mebAssetModel.getValidEndDate());
				apiRequest.setSellerId(mebAssetModel.getSellerId());
				apiRequest.setExtraInfo("{\"mebAssetId\":" + mebAssetModel.getMebAssetId() + "}");
				apiRequest.setOprtId(this.apiRequest.getOprtId());
				apiRequest.setRemark(this.apiRequest.getRemark());
				MebAssetPointOwnershipAddBo bo = (MebAssetPointOwnershipAddBo) MyContext.getRootApplicationContext().getBean("meb.asset.point.ownership.add");
				bo.doInit(apiRequest);
				bo.doAsign();
				bo.doCheck();
				bo.doBusiness();
				businessId = ((MebAssetPointOwnershipAddResponse) bo.getApiResponse()).getMebAssetPointOwnershipId();
			}

			rsp.setBusiness(businessId);
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}

}
