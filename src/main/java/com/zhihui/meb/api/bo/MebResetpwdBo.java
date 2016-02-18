package com.zhihui.meb.api.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyAlgorithmUtils;
import com.zhihui.meb.api.request.MebResetpwdResquest;
import com.zhihui.meb.api.response.MebResetpwdResponse;
import com.zhihui.meb.bo.MebBo;
import com.zhihui.meb.model.MebModel;

@Service
public class MebResetpwdBo extends ApiBo<MebResetpwdResquest> {
	@Autowired
	private MebBo mebBo;

	@Override
	public Class<MebResetpwdResquest> getRequestType() {
		return MebResetpwdResquest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebResetpwdResponse rsp = (MebResetpwdResponse) this.apiResponse;
			rsp.setSuccess(false);

			MebModel mebModel = this.mebBo.getById(this.apiRequest.getMebId());
			if (mebModel == null)
				throw new BusinessException("不存在此会员。");

			mebModel.setPassword(MyAlgorithmUtils.MD5("123456"));
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
