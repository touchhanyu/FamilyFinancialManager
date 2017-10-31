package com.ffm.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	public static <E> List<E> parseJSON(String json, Class<E> cls) {
		if (json == null)
			return null;
		ArrayList<E> list = new ArrayList<E>();
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				@SuppressWarnings("unchecked")
				Map<String, Object> data = (Map<String, Object>) jsonArray.get(i);
				E instance = cls.newInstance();
				Field[] fields = cls.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					String name = field.getName();
					Object value = data.get(name);
					if (value != null) {
						field.setAccessible(true);
						field.set(instance, value);
					}
				}
				list.add(instance);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}