package com.ffm.wechat.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

import com.ffm.common.util.XMLUtil;
import com.ffm.wechat.entity.ArticlesMsg;
import com.ffm.wechat.entity.ArticlesMsg.Item;

public class WeChatTest {

	@Test
	public void test() {
	}

	@Test
	public void test2() {
		ArticlesMsg msg = new ArticlesMsg();
		msg.setCreateTime(123456);
		msg.setFromUserName("hy");
		msg.setMsgId(new BigDecimal("1"));
		msg.setMsgType("music");
		msg.setToUserName("xy");
		ArrayList<Item> list = new ArrayList<Item>();
		Item item1 = msg.new Item();
		item1.setTitle("title1");
		item1.setUrl("url1");
		item1.setPicUrl("picUrl1");
		item1.setDescription("description1");
		list.add(item1);
		Item item2 = msg.new Item();
		item2.setTitle("title2");
		item2.setUrl("url2");
		item2.setPicUrl("picUrl2");
		item2.setDescription("description2");
		list.add(item2);
		msg.setArticles(list);
		msg.setArticleCount(list.size() + "");
		System.out.print(XMLUtil.generateXMLSimple(msg));
//		String xml = FreemakerUtil.process(msg);
//		System.out.println(xml);
	}

	@Test
	public void test3() {
	}
}