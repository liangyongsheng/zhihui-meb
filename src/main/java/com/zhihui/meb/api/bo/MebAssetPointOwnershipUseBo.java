package com.zhihui.meb.api.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyObj2StrUtils;
import com.zhihui.meb.api.request.MebAssetPointOwnershipUseRequest;
import com.zhihui.meb.api.response.MebAssetPointOwnershipUseResponse;
import com.zhihui.meb.bo.MebAssetPointOwnershipBo;
import com.zhihui.meb.model.MebAssetPointOwnershipModel;
import com.zhihui.meb.model.MebAssetPointSummary;

@Service
public class MebAssetPointOwnershipUseBo extends ApiBo<MebAssetPointOwnershipUseRequest> {
	@Autowired
	private MebAssetPointOwnershipBo mebAssetPointOwnershipBo;

	@Override
	public Class<MebAssetPointOwnershipUseRequest> getRequestType() {
		return MebAssetPointOwnershipUseRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			MebAssetPointOwnershipUseResponse rsp = (MebAssetPointOwnershipUseResponse) this.apiResponse;
			rsp.setSuccess(false);

			if (this.apiRequest.getPoint() <= 0)
				throw new BusinessException("使用的积分值一定大于零");

			MebAssetPointSummary maps = this.mebAssetPointOwnershipBo.getSummaryByMebId(this.apiRequest.getMebId());
			if (maps.getCanUsePoint().longValue() < this.apiRequest.getPoint())
				throw new BusinessException("积分不够");

			int point = this.apiRequest.getPoint();
			Map<Long, Integer> mp = new HashMap<Long, Integer>();
			MebAssetPointOwnershipModel addModel = null;
			List<MebAssetPointOwnershipModel> mapoms = new ArrayList<MebAssetPointOwnershipModel>();
			List<MebAssetPointOwnershipModel> mebAssetPointOwnershipModels = this.mebAssetPointOwnershipBo.getByMebIdForUse(this.apiRequest.getMebId(), this.apiRequest.getBeginDate(), this.apiRequest.getEndDate());

			for (MebAssetPointOwnershipModel e : mebAssetPointOwnershipModels) {
				if (point <= 0)
					break;
				else {
					// note the order:注意顺序
					point = point - e.getActualPoint();
					mp.put(e.getMebAssetPointOwnershipId(), e.getActualPoint());
					e.setActualPoint(point >= 0 ? 0 : -point);
					mapoms.add(e);
				}
			}

			// 1、用去积分更新（上述已把积分设置相应的值了）
			for (MebAssetPointOwnershipModel e : mapoms) {
				e.setLastReviseTime(new Timestamp((new Date()).getTime()));
				e.setLastReviseOprtId(this.apiRequest.getOprtId());
				this.mebAssetPointOwnershipBo.update(e);
			}

			// 2、上面限定不会出现这种情况的，可以日后去除（垫付积分为负）
			if (point > 0) {
				addModel = new MebAssetPointOwnershipModel();
				addModel.setMebId(this.apiRequest.getMebId());
				addModel.setMebAssetPointTypeId(2);
				addModel.setInitialPoint(0);
				addModel.setActualPoint(-point);
				addModel.setValidBeginDate(new java.sql.Date((new Date()).getTime()));
				addModel.setValidEndDate(new java.sql.Date(253402185600000L));
				addModel.setOrigOrderId(this.apiRequest.getTarOrderId());
				addModel.setTarOrderId(this.apiRequest.getTarOrderId());
				addModel.setFlag(-2);// -1冻结垫付，-2使用垫付
				addModel.setExtraInfo("积分使用垫付，垫付额为：" + point);
				addModel.setCreateOprtId(this.apiRequest.getOprtId());
				addModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
				addModel.setLastReviseOprtId(this.apiRequest.getOprtId());
				addModel.setRemark(this.apiRequest.getRemark());
				this.mebAssetPointOwnershipBo.add(addModel);
				mp.put(addModel.getMebAssetPointOwnershipId(), point);
			}

			// 3、加入一条用记录
			addModel = new MebAssetPointOwnershipModel();
			addModel.setMebId(this.apiRequest.getMebId());
			addModel.setMebAssetPointTypeId(2);
			addModel.setInitialPoint(0);
			addModel.setActualPoint(this.apiRequest.getPoint());
			addModel.setValidBeginDate(new java.sql.Date((new Date()).getTime()));
			addModel.setValidEndDate(new java.sql.Date(253402185600000L));
			addModel.setOrigOrderId(this.apiRequest.getTarOrderId());
			addModel.setTarOrderId(this.apiRequest.getTarOrderId());
			addModel.setFlag(3); // 使用积分
			addModel.setInnerDealInfo(MyObj2StrUtils.toJson(mp, 0));
			addModel.setExtraInfo(this.apiRequest.getExtraInfo());
			addModel.setCreateOprtId(this.apiRequest.getOprtId());
			addModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			addModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			addModel.setRemark(this.apiRequest.getRemark());
			this.mebAssetPointOwnershipBo.add(addModel);

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
