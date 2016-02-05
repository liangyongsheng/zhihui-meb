package com.zhihui.meb.api.bo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.meb.api.entity.MebAsset;
import com.zhihui.meb.api.entity.MebAssetCondition;
import com.zhihui.meb.api.entity.MebAssetOwnership;
import com.zhihui.meb.api.request.MebAssetOwnershipGetRequest;
import com.zhihui.meb.api.response.MebAssetOwnershipGetResponse;
import com.zhihui.meb.bo.MebAssetBo;
import com.zhihui.meb.bo.MebAssetOwnershipBo;
import com.zhihui.meb.model.MebAssetModel;
import com.zhihui.meb.model.MebAssetOwnershipModel;

@Service
public class MebAssetOwnershipGetBo extends ApiBo<MebAssetOwnershipGetRequest> {
	@Autowired
	private MebAssetBo mebAssetBo;
	@Autowired
	private MebAssetOwnershipBo mebAssetOwnershipBo;

	@Override
	public Class<MebAssetOwnershipGetRequest> getRequestType() {
		return MebAssetOwnershipGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetOwnershipGetResponse rsp = (MebAssetOwnershipGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setMebAssetOwnerships(new ArrayList<MebAssetOwnership>());

			if (this.apiRequest.getMebAssetTypeId() != null && this.apiRequest.getMebAssetTypeId() == 1)
				throw new BusinessException("积分权益是不通过此类方法操作的，请用meb.asset.point.ownership.*方法");

			List<MebAssetOwnershipModel> mebAssetOwnershipModels = this.mebAssetOwnershipBo.getByCondt(this.apiRequest.getMebId(), this.apiRequest.getMebAssetId(), this.apiRequest.getMebAssetTypeId(), this.apiRequest.getFaceValue(), this.apiRequest.getBeginDate(), this.apiRequest.getEndDate());
			List<Long> mebAssetIds = new ArrayList<Long>();
			for (MebAssetOwnershipModel e : mebAssetOwnershipModels)
				mebAssetIds.add(new Long(e.getMebAssetId()));
			List<MebAssetModel> mebAssetModels = this.mebAssetBo.getByIds(mebAssetIds);

			for (MebAssetOwnershipModel e : mebAssetOwnershipModels) {
				MebAssetOwnership mao = new MebAssetOwnership();
				mao.setMebAssetOwnershipId(e.getMebAssetOwnershipId());
				mao.setMebId(e.getMebId());
				mao.setMebAssetId(e.getMebAssetId());
				mao.setFlag(e.getFlag());
				mao.setExtraInfo(e.getExtraInfo());
				mao.setCreatTime(e.getCreateTime());
				mao.setCreateOprtId(e.getCreateOprtId());
				mao.setLastReviseTime(e.getLastReviseTime());
				mao.setLastReviseOprtId(e.getLastReviseOprtId());
				mao.setRemark(e.getRemark());

				for (MebAssetModel me : mebAssetModels) {
					if (e.getMebAssetId() == me.getMebAssetId()) {
						MebAsset ma = new MebAsset();
						ma.setMebAssetId(me.getMebAssetId());
						ma.setMebAssetTypeId(me.getMebAssetTypeId());
						ma.setName(me.getName());
						ma.setFaceValue(me.getFaceValue());
						ma.setValidBeginDate(me.getValidBeginDate());
						ma.setValidEndDate(me.getValidEndDate());
						ma.setCreateTime(me.getCreateTime());
						ma.setCreateOprtId(me.getCreateOprtId());
						ma.setLastReviseTime(me.getLastReviseTime());
						ma.setLastReviseOprtId(me.getLastReviseOprtId());
						ma.setRemark(me.getRemark());
						if (!MyStringUtils.isEmpty(me.getConditionJson()) && !me.getConditionJson().equalsIgnoreCase("null")) {
							try {
								ObjectMapper om = new ObjectMapper();
								om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
								MebAssetCondition t = om.readValue(me.getConditionJson(), MebAssetCondition.class);
								ma.setMebAssetCondition(t);
							} catch (Throwable te) {
							}
						}

						mao.setMebAsset(ma);
						break;
					}
				}

				if (mao.getMebAsset() != null && mao.getMebAsset().getMebAssetCondition() != null) {
					MebAssetCondition t = mao.getMebAsset().getMebAssetCondition();
					boolean f = true;
					f = f == false ? f : t.checkChainId(this.apiRequest.getChainId());
					f = f == false ? f : t.checkChannelSellerId(this.apiRequest.getChannelSellerId());
					f = f == false ? f : t.checkDate(this.apiRequest.getBeginDate(), this.apiRequest.getEndDate());
					if (f)
						rsp.getMebAssetOwnerships().add(mao);
				} else
					rsp.getMebAssetOwnerships().add(mao);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
