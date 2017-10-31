package com.ffm.wechat.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.ffm.common.util.FreemakerUtil;
import com.ffm.common.util.XMLUtil;
import com.ffm.wechat.entity.ArticlesMsg;
import com.ffm.wechat.entity.ImageMsg;
import com.ffm.wechat.entity.MsgType;
import com.ffm.wechat.entity.MusicMsg;
import com.ffm.wechat.entity.TextMsg;
import com.ffm.wechat.entity.VideoMsg;
import com.ffm.wechat.entity.VoiceMsg;
import com.ffm.wechat.service.WechatMsgParse;
import com.ffm.wechat.service.WechatMsgService;

public class WechatMsgServiceImpl implements WechatMsgService {
	private static HashMap<String, Integer> hm = new HashMap<String, Integer>();

	@Override
	public String dealService(MsgType msgType, InputStream is, OutputStream os, Map<String, String> hm) {
		// TODO Auto-generated method stub
		String res = null;
		if (MsgType.CLEAR.equals(msgType)) {// 明文
			this.parseMsgClear(is, os);
		} else if (MsgType.CIPHER.equals(msgType)) {// 密文
			res = this.parseMsgCipher(is, hm);
		} else {// 兼容
		}
		return res;
	}

	@Override
	public void parseMsgClear(InputStream is, OutputStream os) {
		TextMsg msg = WechatMsgParse.parseWeChatXML(is, TextMsg.class);
		FreemakerUtil.process(new OutputStreamWriter(os), msg);
	}

	@Override
	public String parseMsgCipher(InputStream is, Map<String, String> hm) {
		String msg_signature = hm.get("msg_signature");
		String timestamp = hm.get("timestamp");
		String nonce = hm.get("nonce");
		return WechatMsgParse.process(msg_signature, timestamp, nonce, is, this);
	}

	@Override
	public String dealTextService(TextMsg textMsg) {
		// TODO Auto-generated method stub
		String user = textMsg.getFromUserName();
		textMsg.setFromUserName(textMsg.getToUserName());
		textMsg.setToUserName(user);
		String content = textMsg.getContent();
		if ("我爱你".equals(content)) {
			content = "我也爱你，么么哒" + String.valueOf(Character.toChars(0x1F618));
		} else {
			Integer count = hm.get(user);
			if (count != null && count > 2) {
				if (content.contains("不爱")) {
					content = "为什么不爱我/::'(";
				} else {
					content = "你爱我吗/:8*";
				}
			} else {
				if (count == null)
					count = 0;
				content = "感受不到诚意，再发一次";
			}
			count++;
			hm.put(user, count);
		}
		textMsg.setContent(content);
		return FreemakerUtil.process(WechatMsgParse.TEXTFTL, textMsg);// 生成文字格式的xml
	}

	@Override
	public String dealImageService(ImageMsg imageMsg) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String dealVoiceService(VoiceMsg voiceMsg) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String dealVideoService(VideoMsg videoMsg) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String dealMusicService(MusicMsg musicMsg) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String dealArticlesService(ArticlesMsg articlesMsg) {
		// TODO Auto-generated method stub
		return XMLUtil.generateXMLSimple(articlesMsg);// 生成图文格式的xml
	}
}