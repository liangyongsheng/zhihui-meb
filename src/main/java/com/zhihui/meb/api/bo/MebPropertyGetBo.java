package com.zhihui.meb.api.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.meb.api.entity.MebProperty;
import com.zhihui.meb.api.request.MebPropertyGetRequest;
import com.zhihui.meb.api.response.MebPropertyGetResponse;
import com.zhihui.meb.bo.MebPropertyBo;
import com.zhihui.meb.model.MebPropertyModel;

@Service
public class MebPropertyGetBo extends ApiBo<MebPropertyGetRequest> {
	@Autowired
	private MebPropertyBo mebPropertyBo;

	@Override
	public Class<MebPropertyGetRequest> getRequestType() {
		return MebPropertyGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebPropertyGetResponse rsp = (MebPropertyGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setMebProperties(new ArrayList<MebProperty>());
			
			List<MebPropertyModel> mebPropertyModels = this.mebPropertyBo.getByMebId(this.apiRequest.getMebId(), this.apiRequest.getMebPropertyTypeId());
			for (int i = 0; i < mebPropertyModels.size(); i++) {
				MebProperty mp = new MebProperty();
				mp.setMebPropertyId(mebPropertyModels.get(i).getMebPropertyId());
				mp.setMebId(mebPropertyModels.get(i).getMebId());
				mp.setMebPropertyTypeId(mebPropertyModels.get(i).getMebPropertyTypeId());
				mp.setMebPropertyTypeName(mebPropertyModels.get(i).getMebPropertyTypeName());
				mp.setValue(mebPropertyModels.get(i).getValue());
				mp.setCreateTime(mebPropertyModels.get(i).getCreateTime());
				mp.setCreateOprtId(mebPropertyModels.get(i).getCreateOprtId());
				mp.setLastReviseTime(mebPropertyModels.get(i).getLastReviseTime());
				mp.setLastReviseOprtId(mebPropertyModels.get(i).getLastReviseOprtId());
				mp.setRemark(mebPropertyModels.get(i).getRemark());
				rsp.getMebProperties().add(mp);
			}
			
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
