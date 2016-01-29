package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyObj2StrUtils;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.meb.api.request.MebUpdateRequest;
import com.zhihui.meb.api.response.MebUpdateResponse;
import com.zhihui.meb.bo.MebBo;
import com.zhihui.meb.bo.MebLogBo;
import com.zhihui.meb.model.MebLogModel;
import com.zhihui.meb.model.MebModel;

@Service
public class MebUpdateBo extends ApiBo<MebUpdateRequest> {
	@Autowired
	private MebBo mebBo;

	@Autowired
	private MebLogBo mebLogBo;

	@Override
	public Class<MebUpdateRequest> getRequestType() {
		return MebUpdateRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebUpdateResponse rsp = (MebUpdateResponse) this.apiResponse;
			rsp.setSuccess(false);

			MebModel mebModel = this.mebBo.getById(this.apiRequest.getMebId());
			if (mebModel == null)
				throw new BusinessException("meb is not existed");

			// log original objStr
			String beforeLog = MyObj2StrUtils.toJson(mebModel, 0);

			// update
			if (!MyStringUtils.isEmpty(this.apiRequest.getName()))
				mebModel.setName(this.apiRequest.getName());
			if (this.apiRequest.getGender() != null)
				mebModel.setGender(this.apiRequest.getGender());
			if (this.apiRequest.getBirthday() != null)
				mebModel.setBirthday(new java.sql.Date(this.apiRequest.getBirthday().getTime()));
			mebModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			this.mebBo.update(mebModel);

			// log
			MebLogModel mebLogModel = new MebLogModel();
			mebLogModel.setMebLogTypeId(1);
			mebLogModel.setTitle("会员信息修改");
			mebLogModel.setBeforeContentJson(beforeLog);
			mebLogModel.setAfterContentJson(MyObj2StrUtils.toJson(mebModel, 0));
			mebLogModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebLogModel.setRemark(this.apiRequest.getRemark());
			this.mebLogBo.add(mebLogModel);

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
