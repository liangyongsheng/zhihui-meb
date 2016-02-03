package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyAlgorithmUtils;
import com.zhihui.core.util.MyIdCardUtils;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.meb.api.request.MebAddRequest;
import com.zhihui.meb.api.response.MebAddResponse;
import com.zhihui.meb.bo.MebBo;
import com.zhihui.meb.bo.MebPropertyBo;
import com.zhihui.meb.model.MebModel;
import com.zhihui.meb.model.MebPropertyModel;

@Service
public class MebAddBo extends ApiBo<MebAddRequest> {
	@Autowired
	private MebBo mebBo;
	@Autowired
	private MebPropertyBo mebPropertyBo;

	private SimpleDateFormat dfymd = new SimpleDateFormat("yyyyMMdd");

	@Override
	public Class<MebAddRequest> getRequestType() {
		return MebAddRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			// test the idCard
			List<MebPropertyModel> tmps = this.mebPropertyBo.getByValue(11, this.apiRequest.getIdCard());
			if (tmps.size() > 0)
				throw new BusinessException("idCard is existed");
			tmps = this.mebPropertyBo.getByValue(101, this.apiRequest.getMobile());
			if (tmps.size() > 0)
				throw new BusinessException("mobile is existed");
			if (!MyStringUtils.isEmpty(this.apiRequest.getEmail())) {
				tmps = this.mebPropertyBo.getByValue(111, this.apiRequest.getEmail());
				if (tmps.size() > 0)
					throw new BusinessException("email is existed");
			}

			String gender = MyIdCardUtils.getGenderByIdCard(this.apiRequest.getIdCard());
			String birthday = MyIdCardUtils.getBirthdayByIdCard(this.apiRequest.getIdCard());
			MebAddResponse rsp = (MebAddResponse) this.getApiResponse();
			rsp.setSuccess(false);

			MebModel mebModel = new MebModel();
			mebModel.setName(this.apiRequest.getName());
			mebModel.setPassword(MyAlgorithmUtils.MD5(this.apiRequest.getPassword()));
			mebModel.setGender(gender.equals("M") ? 2 : gender.equals("F") ? 3 : 1);
			mebModel.setBirthday(birthday == null ? null : new java.sql.Date(dfymd.parse(birthday).getTime()));
			mebModel.setChannelSellerId(this.apiRequest.getChannelSellerId());
			mebModel.setSellerId(this.apiRequest.getSellerId());
			mebModel.setFlag(1);
			mebModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebModel.setRemark(this.apiRequest.getRemark());
			this.mebBo.add(mebModel);
			// idCard
			MebPropertyModel mebPropertyModel = new MebPropertyModel();
			mebPropertyModel.setMebId(mebModel.getMebId());
			mebPropertyModel.setMebPropertyTypeId(11);
			mebPropertyModel.setValue(this.apiRequest.getIdCard());
			mebPropertyModel.setFlag(1);
			mebPropertyModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebPropertyModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebPropertyModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebPropertyModel.setRemark(this.apiRequest.getRemark());
			this.mebPropertyBo.add(mebPropertyModel);
			// mobile
			mebPropertyModel = new MebPropertyModel();
			mebPropertyModel.setMebId(mebModel.getMebId());
			mebPropertyModel.setMebPropertyTypeId(101);
			mebPropertyModel.setValue(this.apiRequest.getMobile());
			mebPropertyModel.setFlag(1);
			mebPropertyModel.setCreateOprtId(this.apiRequest.getOprtId());
			mebPropertyModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			mebPropertyModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			mebPropertyModel.setRemark(this.apiRequest.getRemark());
			this.mebPropertyBo.add(mebPropertyModel);
			// email
			if (!MyStringUtils.isEmpty(this.apiRequest.getEmail())) {
				mebPropertyModel = new MebPropertyModel();
				mebPropertyModel.setMebId(mebModel.getMebId());
				mebPropertyModel.setMebPropertyTypeId(111);
				mebPropertyModel.setValue(this.apiRequest.getEmail());
				mebPropertyModel.setFlag(1);
				mebPropertyModel.setCreateOprtId(this.apiRequest.getOprtId());
				mebPropertyModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
				mebPropertyModel.setLastReviseOprtId(this.apiRequest.getOprtId());
				mebPropertyModel.setRemark(this.apiRequest.getRemark());
				this.mebPropertyBo.add(mebPropertyModel);
			}

			rsp.setMebId(mebModel.getMebId());
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
