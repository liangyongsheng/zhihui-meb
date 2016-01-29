package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.meb.api.request.MebAssetOwnershipDeleteRequest;
import com.zhihui.meb.api.response.MebAssetOwnershipDeleteResponse;
import com.zhihui.meb.bo.MebAssetBo;
import com.zhihui.meb.bo.MebAssetOwnershipBo;
import com.zhihui.meb.model.MebAssetModel;
import com.zhihui.meb.model.MebAssetOwnershipModel;

@Service
public class MebAssetOwnershipDeleteBo extends ApiBo<MebAssetOwnershipDeleteRequest> {
	@Autowired
	private MebAssetBo mebAssetBo;

	@Autowired
	private MebAssetOwnershipBo mebAssetOwnershipBo;

	@Override
	public Class<MebAssetOwnershipDeleteRequest> getRequestType() {
		return MebAssetOwnershipDeleteRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetOwnershipDeleteResponse rsp = (MebAssetOwnershipDeleteResponse) this.apiResponse;
			rsp.setSuccess(false);

			MebAssetOwnershipModel mebAssetOwnershipModel = this.mebAssetOwnershipBo.getById(this.apiRequest.getMebAssetOwnershipId());
			if (mebAssetOwnershipModel == null)
				throw new BusinessException("不拥用此权益");
			if (mebAssetOwnershipModel.getFlag() == null || mebAssetOwnershipModel.getFlag() != 1)
				throw new BusinessException("此状态不允许删除");

			MebAssetModel mebAssetModel = this.mebAssetBo.getById(mebAssetOwnershipModel.getMebAssetId());
			if (mebAssetModel == null)
				throw new BusinessException("不存在此权益");
			if (mebAssetModel.getMebAssetTypeId() == 1)
				throw new BusinessException("积分权益是不通过此类方法操作的，请用meb.asset.point.ownership.*方法");

			mebAssetOwnershipModel.setFlag(0);
			mebAssetOwnershipModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebAssetOwnershipModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebAssetOwnershipModel.setRemark(this.apiRequest.getRemark());
			this.mebAssetOwnershipBo.update(mebAssetOwnershipModel);

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}

}
