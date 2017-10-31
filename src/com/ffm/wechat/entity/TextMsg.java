package com.ffm.wechat.entity;

import java.math.BigDecimal;

import com.ffm.common.annotation.CDATA;
import com.ffm.common.annotation.Element;
import com.ffm.common.annotation.Ftl;
import com.ffm.common.annotation.MsgType;

@Ftl("textmsg.ftl")
@MsgType("text")
public class TextMsg {
	/**
	 * 开发者微信号
	 */
	@Element("ToUserName")
	@CDATA(true)
	private String toUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	@Element("FromUserName")
	@CDATA(true)
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	@Element("CreateTime")
	private int createTime;
	/**
	 * text
	 */
	@Element("MsgType")
	@CDATA(true)
	private String msgType;
	/**
	 * 文本消息内容
	 */
	@Element("Content")
	@CDATA(true)
	private String content;
	/**
	 * 消息id，64位整型
	 */
	@Element("MsgId")
	private BigDecimal msgId;

	@Override
	public String toString() {
		return "TextMsg [toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + ", content=" + content + ", msgId=" + msgId + "]";
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getMsgId() {
		return msgId;
	}

	public void setMsgId(BigDecimal msgId) {
		this.msgId = msgId;
	}
}