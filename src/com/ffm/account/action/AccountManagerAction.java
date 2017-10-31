package com.ffm.account.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ParameterAware;

import com.ffm.account.entity.UserBalance;
import com.ffm.account.service.UserBalanceService;
import com.ffm.common.action.BaseAction;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.entity.Editor;
import com.ffm.common.entity.PropertyGrid;
import com.ffm.common.entity.PropertyGridRow;
import com.ffm.common.util.COMUtil;
import com.ffm.sys.entity.SysUser;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class AccountManagerAction extends BaseAction implements ParameterAware {
	@SuppressWarnings("unused")
	private Map<String, String[]> param;
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void accountInfo() throws IOException {
		SysUser sysUser = super.getSysUser();// 当前用户
		/** 设置格式 **/
		Editor editor = new Editor();
		editor.setType("numberbox");
		Map<String, String> op = new HashMap<String, String>();
		op.put("precision", "2");
		op.put("groupSeparator", ",");
		editor.setOptions(op);
		PropertyGridRow row = new PropertyGridRow();
		row.setEditor(editor);
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (select DICTCODE,DICTVALUE NAME,'账户明细信息' \"GROUP\" from FFM_SYS_DICTIONARYDETAIL ");
		sb.append("where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneytype') and STATE='1' order by DORDER");
		sb.append(")info left join (select ID,TYPE,WORTH||'' VALUE from FFM_ACCOUNT_USERBALANCE where USERID=").append(sysUser.getId());
		sb.append(")asset on info.DICTCODE=asset.TYPE");
		List<PropertyGridRow> list = SQLUtil.query(sb.toString(), row);
		PropertyGrid grid = new PropertyGrid();
		grid.setTotal(2);
		grid.setRows(list);
		Gson gson = new Gson();
		String json = gson.toJson(grid);
		super.outputJSON(json);
	}

	public void accountChk() throws IOException {
		SysUser sysUser = super.getSysUser();
		Integer id = sysUser.getId();
		String sql = "select ID from FFM_ACCOUNT_USERBALANCE where USERID=" + id + " and TYPE='" + this.data + "'";
		int count = SQLUtil.queryCount(sql);
		if (count == 0) {
			super.outputJSON("error");
		}
	}

	private UserBalanceService service;

	public void setService(UserBalanceService service) {
		this.service = service;
	}

	public String saveAccountInfo() {
		SysUser user = super.getSysUser();
		Date date = new Date();
		String time = COMUtil.currentTime();
		JSONObject jsonObject = JSONObject.fromObject(this.data);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		for (int i = 0; i < jsonArray.size(); i++) {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) jsonArray.get(i);
			String id = map.get("id").toString();
			String dictCode = map.get("dictCode");
			String value = map.get("value");
			Double worth = 0d;
			if (!"".equals(value)) {
				worth = Double.parseDouble(value);
			}
			if (id == null || "".equals(id)) {
				UserBalance userBalance = new UserBalance();
				userBalance.setType(dictCode);
				userBalance.setWorth(worth);
				userBalance.setUserId(user.getId());
				userBalance.setMakeDate(date);
				userBalance.setMakeTime(time);
				userBalance.setOperId(user.getId());
				this.service.save(userBalance);
			} else {
				String sql = "update FFM_ACCOUNT_USERBALANCE set WORTH=" + worth + " where id=" + id;
				SQLUtil.update(sql, null);
			}
		}
		return SUCCESS;
	}

	@Override
	public void setParameters(Map<String, String[]> param) {
		// TODO Auto-generated method stub
		this.param = param;
	}
}