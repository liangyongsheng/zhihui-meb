package com.zhihui.meb.api.bo;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.meb.api.entity.MebAssetCondition;
import com.zhihui.meb.api.request.MebAssetGetRequest;
import com.zhihui.meb.api.response.MebAssetGetResponse;
import com.zhihui.meb.bo.MebAssetBo;
import com.zhihui.meb.model.MebAssetModel;

@Service
public class MebAssetGetBo extends ApiBo<MebAssetGetRequest> {
	@Autowired
	private MebAssetBo mebAssetBo;

	@Override
	public Class<MebAssetGetRequest> getRequestType() {
		return MebAssetGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetGetResponse rsp = (MebAssetGetResponse) this.apiResponse;
			rsp.setSuccess(false);

			MebAssetModel mebAssetModel = this.mebAssetBo.getById(this.apiRequest.getMebAssetId());
			if (mebAssetModel != null) {
				rsp.setMebAssetId(mebAssetModel.getMebAssetId());
				rsp.setMebAssetTypeId(mebAssetModel.getMebAssetTypeId());
				rsp.setMebAssetTypeName(mebAssetModel.getMebAssetTypeName());
				rsp.setName(mebAssetModel.getName());
				rsp.setFaceValue(mebAssetModel.getFaceValue());
				rsp.setValidBeginDate(mebAssetModel.getValidBeginDate());
				rsp.setValidEndDate(mebAssetModel.getValidEndDate());

				if (!MyStringUtils.isEmpty(mebAssetModel.getConditionJson()) && !mebAssetModel.getConditionJson().equalsIgnoreCase("null")) {
					try {
						ObjectMapper om = new ObjectMapper();
						om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
						MebAssetCondition mebAssetCondition = om.readValue(mebAssetModel.getConditionJson(), MebAssetCondition.class);
						rsp.setMebAssetCondition(mebAssetCondition);
					} catch (Throwable e) {
					}
				}

				rsp.setCreateTime(mebAssetModel.getCreateTime());
				rsp.setCreateOprtId(mebAssetModel.getCreateOprtId());
				rsp.setLastReviseTime(mebAssetModel.getLastReviseTime());
				rsp.setLastReviseOprtId(mebAssetModel.getLastReviseOprtId());
				rsp.setRemark(mebAssetModel.getRemark());
				rsp.setSuccess(true);
			}
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
