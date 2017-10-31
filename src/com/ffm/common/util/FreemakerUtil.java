package com.ffm.common.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.ffm.common.annotation.Element;
import com.ffm.common.annotation.Ftl;
import com.ffm.wechat.entity.MediaMsg;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemakerUtil {
	private static final String FILEPATH = "/com/ffm/wechat";

	public static Template readFtl(Class<?> cls) {
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		String path = COMUtil.classPath() + FILEPATH;
		File file = new File(path);
		Template template = null;
		try {
			cfg.setDirectoryForTemplateLoading(file);
			Ftl ftl = cls.getAnnotation(Ftl.class);
			template = cfg.getTemplate(ftl.value());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return template;
	}

	public static String process(Template template, Object obj) {
		String res = "";
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			Map<String, Object> root = new HashMap<String, Object>();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				Object value = field.get(obj);
				if (value != null) {
					if (value instanceof MediaMsg) {
						root = parseObject(root, value);
					} else {
						Element element = field.getAnnotation(Element.class);
						String key = element.value();
						root.put(key, value);
					}
				}
			}
			StringWriter sw = new StringWriter();
			template.process(root, sw);
			res = sw.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static void process(OutputStreamWriter os, Object obj) {
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		String path = COMUtil.classPath() + FILEPATH;
		File file = new File(path);
		try {
			cfg.setDirectoryForTemplateLoading(file);
			Ftl ftl = obj.getClass().getAnnotation(Ftl.class);
			Template template = cfg.getTemplate(ftl.value());
			HashMap<String, String> root = new HashMap<String, String>();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				Object value = field.get(obj);
				if (value == null) {
					value = "";
				}
				Element element = field.getAnnotation(Element.class);
				root.put(element.value(), value + "");
			}
			template.process(root, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, Object> parseObject(Map<String, Object> root, Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Element element = field.getAnnotation(Element.class);
			try {
				field.setAccessible(true);// 设置访问权限
				Object value = field.get(obj);
				if (value != null && element != null) {// 成员内部类属性存在this
					root.put(element.value(), value);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return root;
	}
}