package com.ffm.wechat.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.ffm.wechat.entity.ArticlesMsg;
import com.ffm.wechat.entity.ImageMsg;
import com.ffm.wechat.entity.MsgType;
import com.ffm.wechat.entity.MusicMsg;
import com.ffm.wechat.entity.TextMsg;
import com.ffm.wechat.entity.VideoMsg;
import com.ffm.wechat.entity.VoiceMsg;

public interface WechatMsgService {
	String dealService(MsgType msgType, InputStream is, OutputStream os, Map<String, String> hm);

	/**
	 * 解析明文报文
	 * 
	 * @param is
	 * @param os
	 */
	void parseMsgClear(InputStream is, OutputStream os);

	/**
	 * 解析密文报文
	 * 
	 * @param is
	 * @param hm
	 * @return
	 */
	String parseMsgCipher(InputStream is, Map<String, String> hm);

	String dealTextService(TextMsg textMsg);

	String dealImageService(ImageMsg imageMsg);

	String dealVoiceService(VoiceMsg voiceMsg);

	String dealVideoService(VideoMsg videoMsg);

	String dealMusicService(MusicMsg musicMsg);

	String dealArticlesService(ArticlesMsg articlesMsg);
}