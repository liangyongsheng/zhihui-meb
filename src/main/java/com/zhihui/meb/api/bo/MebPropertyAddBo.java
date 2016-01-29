package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.meb.api.request.MebPropertyAddRequest;
import com.zhihui.meb.api.response.MebPropertyAddResponse;
import com.zhihui.meb.bo.MebBo;
import com.zhihui.meb.bo.MebPropertyBo;
import com.zhihui.meb.bo.MebPropertyTypeBo;
import com.zhihui.meb.model.MebModel;
import com.zhihui.meb.model.MebPropertyModel;
import com.zhihui.meb.model.MebPropertyTypeModel;

@Service
public class MebPropertyAddBo extends ApiBo<MebPropertyAddRequest> {
	@Autowired
	private MebBo mebBo;

	@Autowired
	private MebPropertyBo mebPropertyBo;

	@Autowired
	private MebPropertyTypeBo mebPropertyTypeBo;

	@Override
	public Class<MebPropertyAddRequest> getRequestType() {
		return MebPropertyAddRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebPropertyAddResponse rsp = (MebPropertyAddResponse) this.apiResponse;
			rsp.setSuccess(false);

			MebModel mebModel = this.mebBo.getById(this.apiRequest.getMebId());
			if (mebModel == null)
				throw new BusinessException("field: mebId, the value is not existed");

			boolean isLicit = false;
			List<MebPropertyTypeModel> mebPropertyTypeModels = this.mebPropertyTypeBo.getAll();
			for (MebPropertyTypeModel e : mebPropertyTypeModels) {
				if (e.getMebPropertyTypeId() == this.apiRequest.getMebPropertyTypeId().intValue()) {
					isLicit = true;
					break;
				}
			}
			if (isLicit == false)
				throw new BusinessException("field: mebPropertyTypeId, value is illicit");

			MebPropertyModel mebPropertyModel = new MebPropertyModel();
			mebPropertyModel.setMebId(this.apiRequest.getMebId());
			mebPropertyModel.setMebPropertyId(this.apiRequest.getMebPropertyTypeId());
			mebPropertyModel.setValue(this.apiRequest.getValue());
			mebPropertyModel.setFlag(1);
			mebPropertyModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebPropertyModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebPropertyModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebPropertyModel.setRemark(this.apiRequest.getRemark());
			this.mebPropertyBo.add(mebPropertyModel);

			rsp.setMebPropertyId(mebPropertyModel.getMebPropertyId());
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
