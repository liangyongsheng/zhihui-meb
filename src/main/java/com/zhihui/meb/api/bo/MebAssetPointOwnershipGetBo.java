package com.zhihui.meb.api.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.meb.api.entity.MebAssetPointOwnership;
import com.zhihui.meb.api.request.MebAssetPointOwnershipGetRequest;
import com.zhihui.meb.api.response.MebAssetPointOwnershipGetResponse;
import com.zhihui.meb.bo.MebAssetPointOwnershipBo;
import com.zhihui.meb.model.MebAssetPointOwnershipModel;

@Service
public class MebAssetPointOwnershipGetBo extends ApiBo<MebAssetPointOwnershipGetRequest> {
	@Autowired
	private MebAssetPointOwnershipBo mebAssetPointOwnershipGetBo;

	@Override
	public Class<MebAssetPointOwnershipGetRequest> getRequestType() {
		return MebAssetPointOwnershipGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {

			MebAssetPointOwnershipGetResponse rsp = (MebAssetPointOwnershipGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setMebAssetPointOwnerships(new ArrayList<MebAssetPointOwnership>());

			List<MebAssetPointOwnershipModel> mebAssetPointOwnershipModels = this.mebAssetPointOwnershipGetBo.getByMebId(this.apiRequest.getMebId(), this.apiRequest.getFlag());
			for (MebAssetPointOwnershipModel e : mebAssetPointOwnershipModels) {
				MebAssetPointOwnership mapo = new MebAssetPointOwnership();
				mapo.setMebAssetPointOwnershipId(e.getMebAssetPointOwnershipId());
				mapo.setMebId(e.getMebId());
				mapo.setMebAssetPointTypeId(e.getMebAssetPointTypeId());
				mapo.setMebAssetPointTypeName(e.getMebAssetPointTypeName());
				mapo.setInitialPoint(e.getInitialPoint());
				mapo.setActualPoint(e.getActualPoint());
				mapo.setValidBeginDate(e.getValidBeginDate());
				mapo.setValidEndDate(e.getValidEndDate());
				mapo.setSellerId(e.getSellerId());
				mapo.setOrigOrderId(e.getOrigOrderId());
				mapo.setTarOrderId(e.getTarOrderId());
				mapo.setFlag(e.getFlag());
				mapo.setExtraInfo(e.getExtraInfo());
				mapo.setCreateTime(e.getCreateTime());
				mapo.setCreateOprtId(e.getCreateOprtId());
				mapo.setLastReviseTime(e.getLastReviseTime());
				mapo.setLastReviseOprtId(e.getLastReviseOprtId());
				mapo.setRemark(e.getRemark());
				rsp.getMebAssetPointOwnerships().add(mapo);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
