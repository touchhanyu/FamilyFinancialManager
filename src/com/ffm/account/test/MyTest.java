package com.ffm.account.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.entity.Editor;
import com.ffm.common.entity.PropertyGrid;
import com.ffm.common.entity.PropertyGridRow;
import com.google.gson.Gson;

public class MyTest {
	@Test
	public void test1() {
		Editor editor = new Editor();
		editor.setType("numberbox");
		Map<String, String> op = new HashMap<String, String>();
		op.put("precision", "2");
		op.put("groupSeparator", ",");
		editor.setOptions(op);
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (select DICTCODE,DICTVALUE NAME,'账户明细信息' \"GROUP\" from FFM_SYS_DICTIONARYDETAIL ");
		sb.append("where DID=(select ID from FFM_SYS_DICTIONARY where DICTCODE='moneytype') and STATE='1' order by DORDER");
		sb.append(")info left join (select ID,TYPE,WORTH||'' VALUE from FFM_ACCOUNT_USERBALANCE where USERID=").append("2");
		sb.append(")asset on info.DICTCODE=asset.TYPE");
		PropertyGridRow row = new PropertyGridRow();
		row.setEditor(editor);
		List<PropertyGridRow> list = SQLUtil.query(sb.toString(), row);
		PropertyGrid grid = new PropertyGrid();
		grid.setTotal(2);
		grid.setRows(list);
		Gson gson = new Gson();
		String json = gson.toJson(grid);
		System.out.println(json);
	}
}