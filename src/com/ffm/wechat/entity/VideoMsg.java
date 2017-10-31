package com.ffm.wechat.entity;

import java.math.BigDecimal;

import com.ffm.common.annotation.Element;
import com.ffm.common.annotation.Ftl;
import com.ffm.common.annotation.MsgType;

@Ftl("videomsg.ftl")
@MsgType("video")
public class VideoMsg {
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
	 * video
	 */
	@Element("MsgType")
	private String msgType;
	/**
	 * 
	 */
	@Element("Video")
	private Video video;
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

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public BigDecimal getMsgId() {
		return msgId;
	}

	public void setMsgId(BigDecimal msgId) {
		this.msgId = msgId;
	}

	public class Video extends MediaMsg {
		/**
		 * 通过素材管理中的接口上传多媒体文件，得到的id。
		 */
		@Element("MediaId")
		private String MediaId;
		/**
		 * 视频消息的标题
		 */
		@Element("Title")
		private String Title;
		/**
		 * 视频消息的描述
		 */
		@Element("Description")
		private String Description;

		public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getDescription() {
			return Description;
		}

		public void setDescription(String description) {
			Description = description;
		}
	}
}