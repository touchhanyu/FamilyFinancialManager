package com.ffm.wechat.entity;

import java.math.BigDecimal;

import com.ffm.common.annotation.Element;
import com.ffm.common.annotation.Ftl;
import com.ffm.common.annotation.MsgType;

@Ftl("voicemsg.ftl")
@MsgType("voice")
public class VoiceMsg {
	/**
	 * 接收方帐号（OpenID）
	 */
	@Element("ToUserName")
	private String toUserName;
	/**
	 * 发送方帐号（OpenID）
	 */
	@Element("FromUserName")
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	@Element("CreateTime")
	private int createTime;
	/**
	 * voice
	 */
	@Element("MsgType")
	private String msgType;
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id。
	 */
	@Element("Voice")
	private Voice voice;
	/**
	 * 消息id，64位整型
	 */
	@Element("MsgId")
	private BigDecimal msgId;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public BigDecimal getMsgId() {
		return msgId;
	}

	public void setMsgId(BigDecimal msgId) {
		this.msgId = msgId;
	}

	public class Voice extends MediaMsg {
		@Element("MediaId")
		private String MediaId;

		public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
	}
}