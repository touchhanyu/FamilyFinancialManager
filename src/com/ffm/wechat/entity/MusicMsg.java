package com.ffm.wechat.entity;

import java.math.BigDecimal;

import com.ffm.common.annotation.Element;
import com.ffm.common.annotation.Ftl;
import com.ffm.common.annotation.MsgType;

@Ftl("musicmsg.ftl")
@MsgType("music")
public class MusicMsg {
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
	 * music
	 */
	@Element("MsgType")
	private String msgType;
	/**
	 * 
	 */
	@Element("Music")
	private Music music;
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

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public BigDecimal getMsgId() {
		return msgId;
	}

	public void setMsgId(BigDecimal msgId) {
		this.msgId = msgId;
	}

	public class Music extends MediaMsg {
		/**
		 * 音乐标题
		 */
		@Element("Title")
		private String Title;
		/**
		 * 音乐描述
		 */
		@Element("Description")
		private String Description;
		/**
		 * 音乐链接
		 */
		@Element("MusicUrl")
		private String MusicUrl;
		/**
		 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 */
		@Element("HQMusicUrl")
		private String HQMusicUrl;
		/**
		 * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
		 */
		@Element("ThumbMediaId")
		private String ThumbMediaId;

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

		public String getMusicUrl() {
			return MusicUrl;
		}

		public void setMusicUrl(String musicUrl) {
			MusicUrl = musicUrl;
		}

		public String getHQMusicUrl() {
			return HQMusicUrl;
		}

		public void setHQMusicUrl(String hQMusicUrl) {
			HQMusicUrl = hQMusicUrl;
		}

		public String getThumbMediaId() {
			return ThumbMediaId;
		}

		public void setThumbMediaId(String thumbMediaId) {
			ThumbMediaId = thumbMediaId;
		}
	}
}