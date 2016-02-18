package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyObj2StrUtils;
import com.zhihui.meb.api.request.MebPropertyUpdateRequest;
import com.zhihui.meb.api.response.MebPropertyUpdateResponse;
import com.zhihui.meb.bo.MebLogBo;
import com.zhihui.meb.bo.MebPropertyBo;
import com.zhihui.meb.model.MebLogModel;
import com.zhihui.meb.model.MebPropertyModel;

@Service
public class MebPropertyUpdateBo extends ApiBo<MebPropertyUpdateRequest> {
	@Autowired
	private MebPropertyBo mebPropertyBo;
	@Autowired
	private MebLogBo mebLogBo;

	@Override
	public Class<MebPropertyUpdateRequest> getRequestType() {
		return MebPropertyUpdateRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebPropertyUpdateResponse rsp = (MebPropertyUpdateResponse) this.apiResponse;
			rsp.setSuccess(false);

			MebPropertyModel mebPropertyModel = this.mebPropertyBo.getById(this.apiRequest.getMebPropertyId());
			if (mebPropertyModel == null)
				throw new BusinessException("do not exist this property.");

			// log original objStr
			String beforeLog = MyObj2StrUtils.toJson(mebPropertyModel, 0);

			// update
			mebPropertyModel.setValue(this.apiRequest.getValue());
			mebPropertyModel.setFlag(1);
			mebPropertyModel.setLastReviseTime(new Timestamp((new Date().getTime())));
			mebPropertyModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			this.mebPropertyBo.update(mebPropertyModel);

			// log
			MebLogModel mebLogModel = new MebLogModel();
			mebLogModel.setMebLogTypeId(2);
			mebLogModel.setTitle("会员属性信息修改");
			mebLogModel.setBeforeContentJson(beforeLog);
			mebLogModel.setAfterContentJson(MyObj2StrUtils.toJson(mebPropertyModel, 0));
			mebLogModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebLogModel.setRemark(this.apiRequest.getRemark());
			this.mebLogBo.add(mebLogModel);

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
