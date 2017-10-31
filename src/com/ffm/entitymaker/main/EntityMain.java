package com.ffm.entitymaker.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ffm.entitymaker.entity.Colunm;
import com.ffm.entitymaker.entity.TableEntity;

/**
 * @author Hanyu
 * 
 */
public class EntityMain {
	/**
	 * 实体类所在包名
	 */
	public final static String SRCPATH = "cn.sunline.ssun.service.model.autofinance.loanretail";
	/**
	 * 继承框架类全包名
	 */
	public final static String BASEPATH = "cn.sunline.ssun.framework.entity.BaseEntity";
	/**
	 * 输出文件夹
	 */
	public final static String OUTPUTFOLDER = "E:/src/";

	private String[][] data = null;

	private static Properties prop = new Properties();
	static {
		InputStream is = null;
		try {
			is = EntityMain.class.getResourceAsStream("/com/hy/entitymaker/cfg/cfg.properties");
			prop.load(is);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public EntityMain() {
		// 懒汉式
		Set<Entry<Object, Object>> entrySet = prop.entrySet();
		Iterator<Entry<Object, Object>> it = entrySet.iterator();
		this.data = new String[entrySet.size()][2];
		int i = 0;
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			this.data[i][0] = (String) entry.getKey();
			this.data[i++][1] = (String) entry.getValue();
		}
	}

	@SuppressWarnings("unchecked")
	public void readXML(File file) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			List<Element> rootObjs = root.elements();
			Element rootObj = rootObjs.get(0);
			List<Element> childrens = rootObj.elements();
			Element children = childrens.get(0);
			List<Element> models = children.elements();
			Element model = models.get(0);
			Element tables = model.element("Tables");
			List<Element> lTables = tables.elements();
			Iterator<Element> it = lTables.iterator();// 遍历所有table
			ArrayList<TableEntity> tableEntitys = new ArrayList<TableEntity>();
			while (it.hasNext()) {
				Element table = it.next();
				Element tColunms = table.element("Columns");// 获取Colunms
				ArrayList<Colunm> tableColunms = null;
				if (tColunms != null) {
					List<Element> colunms = tColunms.elements("Column");// 获取每个Colunm
					tableColunms = new ArrayList<Colunm>();
					for (int i = 0; i < colunms.size(); i++) {
						Element colunm = colunms.get(i);
						String cName = colunm.element("Name").getText();
						String cCode = colunm.element("Code").getText();
						String cDataType = colunm.element("DataType").getText();
						Colunm tableColunm = new Colunm();
						tableColunm.setName(cName);
						tableColunm.setCode(cCode);
						tableColunm.setDataType(cDataType);
						Element eComment = colunm.element("Comment");
						if (eComment != null) {
							String cComment = eComment.getText();
							tableColunm.setComments(cComment);
						}
						tableColunms.add(tableColunm);
					}
				}
				String tName = table.element("Name").getText();
				String tCode = table.element("Code").getText();
				TableEntity tableEntity = new TableEntity();
				tableEntity.setName(tName);
				tableEntity.setCode(tCode);
				tableEntity.setColunms(tableColunms);
				Element tComment = table.element("Comment");
				if (tComment != null) {
					tableEntity.setComments(tComment.getText());
				}
				tableEntitys.add(tableEntity);
				this.packageEntity(tableEntitys);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void packageEntity(List<TableEntity> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			StringBuffer sb = new StringBuffer();
			TableEntity entity = entitys.get(i);
			sb.append("\r\n/**\r\n * ").append(entity.getName()).append("\r\n * \r\n */\r\n");
			sb.append("@Table(name = \"").append(entity.getCode().toUpperCase()).append("\")\r\n");
			sb.append("public class ").append(entity.getCode().replace("_", "")).append(" extends BaseEntity {\r\n");
			List<Colunm> colunms = entity.getColunms();
			String temp = "";
			if (colunms != null) {
				for (int j = 0; j < colunms.size(); j++) {
					Colunm colunm = colunms.get(j);
					sb.append("\t/** ").append(colunm.getName()).append(" **/\r\n");
					String dataType = colunm.getDataType();
					String classType = this.findClass(dataType);
					if ("Date".equals(classType)) {
						temp = "\r\nimport java.util.Date;\r\n";
					}
					sb.append("\t@Column(name = \"").append(colunm.getCode().toUpperCase()).append("\")\r\n");
					sb.append("\tprivate ").append(classType).append(" ").append(colunm.getCode()).append(";\r\n\r\n");
					colunm.getName();
				}
			}
			sb.append("\t@Override\r\n\tpublic Object getId() {\r\n\t\treturn null;\r\n\t}\r\n\r\n");
			sb.append("}");
			// 类所在包，及相关导入包
			StringBuffer title = new StringBuffer();
			title.append("package ").append(EntityMain.SRCPATH).append(";\r\n");
			title.append(temp);
			title.append("\r\nimport cn.sunline.ssun.common.db.annotation.Column;\r\n");
			title.append("import cn.sunline.ssun.common.db.annotation.Table;\r\n");
			title.append("import ").append(EntityMain.BASEPATH).append(";\r\n");
			this.outputEntity(entity.getCode().replace("_", ""), title, sb);
		}
	}

	/**
	 * 输出java文件
	 * 
	 * @param fileName：输出文件名
	 * @param title
	 * @param body
	 */
	public void outputEntity(String fileName,StringBuffer title, StringBuffer body) {
		File file = new File(EntityMain.OUTPUTFOLDER + fileName + ".java");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.print(title.toString());
			pw.print(body.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 根据DataType匹配数据类型
	 * 
	 * @param dataType
	 * @return
	 */
	public String findClass(String dataType) {
		String classType = null;
		for (int i = 0; i < this.data.length; i++) {
			String key = this.data[i][0];
			String reg = "^" + key + "$";
			if (dataType.matches(reg)) {
				classType = this.data[i][1];
				break;
			}
		}
		return classType;
	}

	public static void main(String[] args) {
		URL url = EntityMain.class.getResource("/");
		@SuppressWarnings("deprecation")
		String path = URLDecoder.decode(url.toString());
		File file = new File(path + "\\com\\hy\\entitymaker\\cfg\\schema\\schema.xml");
		EntityMain main = new EntityMain();
		main.readXML(file);
	}
}