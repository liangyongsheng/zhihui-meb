package com.zhihui.meb.api.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyIdCardUtils;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.meb.api.entity.MebProperty;
import com.zhihui.meb.api.request.MebGetRequest;
import com.zhihui.meb.api.response.MebGetResponse;
import com.zhihui.meb.bo.MebBo;
import com.zhihui.meb.bo.MebPropertyBo;
import com.zhihui.meb.model.MebModel;
import com.zhihui.meb.model.MebPropertyModel;

@Service
public class MebGetBo extends ApiBo<MebGetRequest> {
	@Autowired
	private MebBo mebBo;
	@Autowired
	private MebPropertyBo mebPropertyBo;

	@Override
	public Class<MebGetRequest> getRequestType() {
		return MebGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebGetResponse rsp = (MebGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setMebProperties(new ArrayList<MebProperty>());

			long mebId = 0;
			int mebPropertTypeId = 0;
			// group-1:mebId
			if (this.apiRequest.getMebId() != null && this.apiRequest.getMebId() > 0)
				mebId = this.apiRequest.getMebId();
			// group-2:idStr
			else if (!MyStringUtils.isEmpty(this.apiRequest.getIdStr())) {
				boolean next = true;
				String idStr = null;

				// 一、大陆手机号（没+86）
				if (next == true) {
					idStr = this.apiRequest.getIdStr();
					if (idStr.length() == 11 && idStr.matches("1[3458][0-9]{9}")) {
						mebPropertTypeId = 101; // 手机号
						next = false;
					}
				}

				// 二、邮箱
				if (next == true) {
					idStr = this.apiRequest.getIdStr();
					if (idStr.matches("[a-zA-Z\\.@]+") && idStr.matches("[^@]+@[^@]+")) {
						mebPropertTypeId = 111;// 邮箱
						next = false;
					}
				}

				// 三、身份证
				if (next == true) {
					// 1、身份证
					idStr = this.apiRequest.getIdStr().toUpperCase();
					if (idStr.length() == 15 && MyIdCardUtils.validateIdCard15(idStr)) {
						String tmp = MyIdCardUtils.conver15CardTo18(idStr);
						if (tmp != null) {
							idStr = tmp;
							mebPropertTypeId = 11; // 身份证号
							next = false;
						}
					}
					if (idStr.length() == 18 && MyIdCardUtils.validateIdCard18(idStr)) {
						mebPropertTypeId = 11;// 身份证号
						next = false;
					}
					// 2、港澳台身份证
					if (next == true) {
						idStr = this.apiRequest.getIdStr().toUpperCase().replace("（", "(").replace("（", ")");
						if (idStr.length() == 10) {
							String[] infos = MyIdCardUtils.validateIdCard10(idStr);
							if (infos != null) {
								mebPropertTypeId = infos[1].equals("1") ? 12 : (infos[1].equals("2") ? 13 : 14); // 港澳台身份证
								next = false;
							}
						}
					}
				}

				// 四、10位会员卡（若不是请修改）
				if (next == true) {
					idStr = this.apiRequest.getIdStr();
					if (idStr.matches("[2-9][0-9]{9}")) {
						mebPropertTypeId = 1;// 会员卡
						next = false;
					}
				}

				if (mebPropertTypeId > 0) {
					List<MebPropertyModel> mebPropertyModels = this.mebPropertyBo.getByValue(mebPropertTypeId, idStr);
					mebId = mebPropertyModels.size() <= 0 ? mebId : mebPropertyModels.get(0).getMebId();
					for (MebPropertyModel e : mebPropertyModels) {
						if (e.getMebId() != mebId)
							throw new BusinessException("More than one member owns the idStr, can not identify which one.");
					}
				}
			}

			if (mebId <= 0)
				return;

			MebModel mebModel = this.mebBo.getById(mebId);
			if (mebModel == null)
				return;

			rsp.setMebId(mebModel.getMebId());
			rsp.setMebTypeId(mebModel.getMebTypeId());
			rsp.setMebTypeName(mebModel.getMebTypeName());
			rsp.setName(mebModel.getName());
			rsp.setPassword(mebModel.getPassword());
			rsp.setGender(mebModel.getGender());
			rsp.setBirthday(mebModel.getBirthday() == null ? null : new Date(mebModel.getBirthday().getTime()));
			rsp.setFlag(mebModel.getFlag());
			rsp.setChannelSellerId(mebModel.getChannelSellerId());
			rsp.setSellerId(mebModel.getSellerId());
			rsp.setCreateTime(mebModel.getCreateTime());
			rsp.setCreateOprtId(mebModel.getCreateOprtId());
			rsp.setLastReviseTime(mebModel.getLastReviseTime());
			rsp.setLastReviseOprtId(mebModel.getLastReviseOprtId());
			rsp.setRemark(mebModel.getRemark());

			if (this.apiRequest.getSimple() == null || !this.apiRequest.getSimple()) {
				List<MebPropertyModel> mebPropertyModels = this.mebPropertyBo.getByMebId(mebModel.getMebId(), null);
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
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
