package com.zhihui.meb.api.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.meb.api.request.MebAssetPointOwnershipSummaryRequest;
import com.zhihui.meb.api.response.MebAssetPointOwnershipSummaryResponse;
import com.zhihui.meb.bo.MebAssetPointOwnershipBo;
import com.zhihui.meb.model.MebAssetPointSummary;

public class MebAssetPointOwnershipSummaryBo extends ApiBo<MebAssetPointOwnershipSummaryRequest> {
	@Autowired
	private MebAssetPointOwnershipBo mebAssetPointOwnershipBo;

	@Override
	public Class<MebAssetPointOwnershipSummaryRequest> getRequestType() {
		return MebAssetPointOwnershipSummaryRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetPointOwnershipSummaryResponse rsp = (MebAssetPointOwnershipSummaryResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setMebId(this.apiRequest.getMebId());
			rsp.setHaveUsedPoint(0L);
			rsp.setCanUsePoint(0L);
			rsp.setFrozenPoint(0L);
			rsp.setHaveUsedPoint(0L);
			rsp.setExpiredPoint(0L);

			MebAssetPointSummary mebAssetPointSummary = this.mebAssetPointOwnershipBo.getSummaryByMebId(this.apiRequest.getMebId());
			if (mebAssetPointSummary != null) {
				rsp.setHistoricPoint(mebAssetPointSummary.getHistoricPoint().longValue());
				rsp.setCanUsePoint(mebAssetPointSummary.getCanUsePoint().longValue());
				rsp.setFrozenPoint(mebAssetPointSummary.getFrozenPoint().longValue());
				rsp.setHaveUsedPoint(mebAssetPointSummary.getHaveUsedPoint().longValue());
				rsp.setExpiredPoint(mebAssetPointSummary.getExpiredPoint().longValue());
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}

}
