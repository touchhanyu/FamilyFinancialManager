package com.ffm.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.ffm.common.annotation.CDATA;
import com.ffm.common.annotation.ListElement;

public class XMLUtil {

	/**
	 * 生成不带xml头信息的xml文件
	 * 
	 * @param obj
	 * @return
	 */
	public static String generateXMLSimple(Object obj) {
		String xml = generateXML(obj);
		String reg = "[<][?][xml].*[?][>]\\n\\n";
		return xml.replaceAll(reg, "");
	}

	/**
	 * 生成正常的xml文件
	 * 
	 * @param obj
	 * @return
	 */
	public static String generateXML(Object obj) {
		String res = "";
		Document document = DocumentHelper.createDocument();
		Element xmlNode = document.addElement("xml");
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				generateElement(xmlNode, field, obj);
			}
			StringWriter sw = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(sw, format);
			writer.write(document);
			res = sw.toString();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 根据类的属性信息，生成xml节点
	 * 
	 * @param element
	 * @param field
	 * @param obj
	 * @return
	 */
	public static Element generateElement(Element node, Field field, Object obj) {
		field.setAccessible(true);
		try {
			Object object = field.get(obj);
			com.ffm.common.annotation.Element element = field.getAnnotation(com.ffm.common.annotation.Element.class);
			if (object instanceof List) {// 处理多节点
				ListElement listElement = field.getAnnotation(ListElement.class);
				Element subElements = node.addElement(listElement.value());
				@SuppressWarnings("rawtypes")
				List list = (List) object;
				for (int j = 0; j < list.size(); j++) {
					Element subElement = subElements.addElement(element.value());// 依次生成子节点
					Object subobj = list.get(j);
					Field[] subFields = subobj.getClass().getDeclaredFields();
					for (int k = 0; k < subFields.length; k++) {
						Field subField = subFields[k];
						generateElement(subElement, subField, subobj);// 循环生成子节点
					}
				}
			} else {
				CDATA cdata = field.getAnnotation(CDATA.class);
				Object value = field.get(obj);
				if (node != null && value != null && element != null) {
					Element valueNode = node.addElement(element.value());
					if (cdata != null && cdata.value()) {// CDATA为true
						valueNode.addCDATA(value.toString());
					} else {
						valueNode.addText(value.toString());
					}
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return node;
	}
}