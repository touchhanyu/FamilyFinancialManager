package com.ffm.wechat.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ffm.common.util.FreemakerUtil;
import com.ffm.common.util.IOUtil;
import com.ffm.wechat.entity.ArticlesMsg;
import com.ffm.wechat.entity.ImageMsg;
import com.ffm.wechat.entity.MsgType;
import com.ffm.wechat.entity.MusicMsg;
import com.ffm.wechat.entity.TextMsg;
import com.ffm.wechat.entity.VideoMsg;
import com.ffm.wechat.entity.VoiceMsg;
import com.ffm.wechat.qq.AesException;
import com.ffm.wechat.qq.WXBizMsgCrypt;
import com.ffm.wechat.util.WechatUtil;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class WechatMsgParse {
	public static WXBizMsgCrypt msgCrypt;
	public static final Template TEXTFTL;
	public static final Template IMAGEFTL;
	public static final Template VOICEFTL;
	public static final Template VIDEOFTL;
	public static final Template MUSICFTL;

	static {
		try {
			msgCrypt = new WXBizMsgCrypt(WechatUtil.TOKEN, WechatUtil.AESKEY, WechatUtil.APPID);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		}
		TEXTFTL = FreemakerUtil.readFtl(TextMsg.class);
		IMAGEFTL = FreemakerUtil.readFtl(ImageMsg.class);
		VOICEFTL = FreemakerUtil.readFtl(VoiceMsg.class);
		VIDEOFTL = FreemakerUtil.readFtl(VideoMsg.class);
		MUSICFTL = FreemakerUtil.readFtl(MusicMsg.class);
	}

	/**
	 * 处理密文格式的xml
	 * 
	 * @param msg_signature
	 * @param timestamp
	 * @param nonce
	 * @param is
	 * @param service
	 * @return
	 */
	public static String process(String msg_signature, String timestamp, String nonce, InputStream is, WechatMsgService service) {
		String encryptMsg = "";
		try {
			String xml = IOUtil.parseString(is);
			String decryptMsg = msgCrypt.decryptMsg(msg_signature, timestamp, nonce, xml);// 解密
			String newXml = parseWeChatXML(decryptMsg, service);// 解析xml，处理业务
			encryptMsg = msgCrypt.encryptMsg(newXml, timestamp, nonce);// 加密
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptMsg;
	}

	/**
	 * 解析接收的微信xml（明文）
	 * 
	 * @param is
	 * @param cls
	 * @return
	 */
	public static <E> E parseWeChatXML(InputStream is, Class<E> cls) {
		SAXReader reader = new SAXReader();
		E obj = null;
		try {
			org.dom4j.Document document = reader.read(is);
			org.dom4j.Element root = document.getRootElement();
			obj = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				com.ffm.common.annotation.Element element = field.getAnnotation(com.ffm.common.annotation.Element.class);
				org.dom4j.Element e = root.element(element.value());
				Object data = e.getData();
				field.setAccessible(true);
				Type type = field.getGenericType();
				if (type.toString().contains("String")) {
					field.set(obj, data);
				} else if ("BigDecimal".equals(type.toString())) {
					field.set(obj, new BigDecimal(data.toString()));
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 解析接收的微信xml（加密），回调服务类处理业务的方法
	 * 
	 * @param xml
	 * @param service
	 * @return
	 */
	public static String parseWeChatXML(String xml, WechatMsgService service) {
		String newXml = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xml);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			NodeList typeNode = root.getElementsByTagName("MsgType");
			String msgType = typeNode.item(0).getTextContent();
			if (MsgType.TEXT.getValue().equals(msgType)) {
				TextMsg textMsg = parseTextMsg(root);
				newXml = service.dealTextService(textMsg);
			} else if (MsgType.IMAGE.getValue().equals(msgType)) {
				ImageMsg imageMsg = parseImageMsg(root);
				newXml = service.dealImageService(imageMsg);
			} else if (MsgType.VOICE.getValue().equals(msgType)) {
				VoiceMsg voiceMsg = parseVoiceMsg(root);
				newXml = service.dealVoiceService(voiceMsg);
			} else if (MsgType.VIDEO.getValue().equals(msgType)) {
				VideoMsg videoMsg = parseVideoMsg(root);
				newXml = service.dealVideoService(videoMsg);
			} else if (MsgType.MUSIC.getValue().equals(msgType)) {
				MusicMsg musicMsg = parseMusicMsg(root);
				newXml = service.dealMusicService(musicMsg);
			} else if (MsgType.ARTICLE.getValue().equals(msgType)) {
				ArticlesMsg articlesMsg = parseArticlesMsg(root);
				newXml = service.dealArticlesService(articlesMsg);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newXml;
	}

	/**
	 * 根据给定参数，生成XML报文
	 * 
	 * @param hm
	 * @param template
	 * @return
	 */
	public static String generateXML(Map<String, Object> hm, String template) {
		String res = "";
		StringTemplateLoader loader = new StringTemplateLoader();
		String templateStr = "MsgType";// 写死为消息类型
		loader.putTemplate(templateStr, template);
		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(loader);
		try {
			Template temp = cfg.getTemplate(templateStr);
			StringWriter sw = new StringWriter();
			temp.process(hm, sw);
			res = sw.toString();
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 解析文本消息
	 * 
	 * @param root
	 * @return
	 */
	public static TextMsg parseTextMsg(Element root) {
		String toUserName = root.getElementsByTagName("ToUserName").item(0).getTextContent();
		String fromUserName = root.getElementsByTagName("FromUserName").item(0).getTextContent();
		String createTime = root.getElementsByTagName("CreateTime").item(0).getTextContent();
		String content = root.getElementsByTagName("Content").item(0).getTextContent();
		String msgId = root.getElementsByTagName("MsgId").item(0).getTextContent();
		TextMsg textMsg = new TextMsg();
		textMsg.setToUserName(toUserName);
		textMsg.setFromUserName(fromUserName);
		textMsg.setCreateTime(Integer.parseInt(createTime));
		textMsg.setContent(content);
		textMsg.setMsgId(new BigDecimal(msgId));
		textMsg.setMsgType(MsgType.TEXT.getValue());
		return textMsg;
	}

	/**
	 * 解析图片消息
	 * 
	 * @param root
	 * @return
	 */
	public static ImageMsg parseImageMsg(Element root) {
		String toUserName = root.getElementsByTagName("ToUserName").item(0).getTextContent();
		String fromUserName = root.getElementsByTagName("FromUserName").item(0).getTextContent();
		String createTime = root.getElementsByTagName("CreateTime").item(0).getTextContent();
		String msgId = root.getElementsByTagName("MsgId").item(0).getTextContent();
		root.getElementsByTagName("Image").item(0);
		ImageMsg imageMsg = new ImageMsg();
		imageMsg.setToUserName(toUserName);
		imageMsg.setFromUserName(fromUserName);
		imageMsg.setCreateTime(Integer.parseInt(createTime));
		imageMsg.setMsgId(new BigDecimal(msgId));
		imageMsg.setMsgType(MsgType.TEXT.getValue());
		return imageMsg;
	}

	/**
	 * 解析语音消息
	 * 
	 * @param root
	 * @return
	 */
	public static VoiceMsg parseVoiceMsg(Element root) {
		root.getElementsByTagName("ToUserName");
		root.getElementsByTagName("FromUserName");
		root.getElementsByTagName("CreateTime");
		VoiceMsg voiceMsg = new VoiceMsg();
		voiceMsg.setMsgType(MsgType.VOICE.getValue());
		return voiceMsg;
	}

	/**
	 * 解析视频消息
	 * 
	 * @param root
	 * @return
	 */
	public static VideoMsg parseVideoMsg(Element root) {
		root.getElementsByTagName("ToUserName");
		root.getElementsByTagName("FromUserName");
		root.getElementsByTagName("CreateTime");
		VideoMsg videoMsg = new VideoMsg();
		videoMsg.setMsgType(MsgType.VIDEO.getValue());
		return videoMsg;
	}

	/**
	 * 解析音乐消息
	 * 
	 * @param root
	 * @return
	 */
	public static MusicMsg parseMusicMsg(Element root) {
		root.getElementsByTagName("ToUserName");
		root.getElementsByTagName("FromUserName");
		root.getElementsByTagName("CreateTime");
		MusicMsg musicMsg = new MusicMsg();
		musicMsg.setMsgType(MsgType.MUSIC.getValue());
		return musicMsg;
	}

	/**
	 * 解析图文消息
	 * 
	 * @param root
	 * @return
	 */
	public static ArticlesMsg parseArticlesMsg(Element root) {
		root.getElementsByTagName("ToUserName");
		root.getElementsByTagName("FromUserName");
		root.getElementsByTagName("CreateTime");
		ArticlesMsg articlesMsg = new ArticlesMsg();
		articlesMsg.setMsgType(MsgType.ARTICLE.getValue());
		return articlesMsg;
	}
}