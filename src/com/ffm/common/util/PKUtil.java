package com.ffm.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.JDBCUtil;
import com.ffm.common.db.SQLUtil;

public class PKUtil {
	/**
	 * 根据给定Code生成对应主键
	 * 
	 * @param code
	 * @return
	 */
	public static Integer generatePK(String code) {
		Integer maxno = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Connection conn = JDBCUtil.getConn();
		try {
			conn.setAutoCommit(true);
			pstm = conn.prepareStatement("select MAXNO+1 from FFM_SYS_SYSSEQ where SEQCODE=? for update");
			pstm.setString(1, code);
			rs = pstm.executeQuery();
			PreparedStatement pstm2 = null;
			if (rs.next()) {
				maxno = rs.getInt(1);
				pstm2 = conn.prepareStatement("update FFM_SYS_SYSSEQ set MAXNO=MAXNO+1 where SEQCODE=?");
				pstm2.setString(1, code);
			} else {
				maxno = 1;
				pstm2 = conn.prepareStatement("insert into FFM_SYS_SYSSEQ values(?,1)");
				pstm2.setString(1, code);
			}
			pstm2.executeUpdate();
			JDBCUtil.realse(null, pstm2, null);
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.realse(conn, pstm, rs);
		}
		return maxno;
	}

	public static String generateD_PK(String code, int len) {
		String pk = null;
		List<Object[]> list = SQLUtil.query("select VALUE from FFM_SYS_SYSVAR where VARTYPE='sysdate'");
		if (list.size() == 1) {
			Object[] obj = list.get(0);
			String sysdate = (String) obj[0];
			Integer id = generatePK(code);
			pk = sysdate + COMUtil.fillStr(id, len, "0");
		}
		return pk;
	}
}