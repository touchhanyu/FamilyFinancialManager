package com.ffm.wechat.entity;

import java.math.BigDecimal;
import java.util.List;

import com.ffm.common.annotation.CDATA;
import com.ffm.common.annotation.Element;
import com.ffm.common.annotation.Ftl;
import com.ffm.common.annotation.ListElement;
import com.ffm.common.annotation.MsgType;

@Ftl("articlesmsg.ftl")
@MsgType("news")
public class ArticlesMsg {
	/**
	 * 接收方帐号（OpenID）
	 */
	@Element("ToUserName")
	@CDATA(true)
	private String toUserName;
	/**
	 * 发送方帐号（OpenID）
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
	 * news
	 */
	@Element("MsgType")
	@CDATA(true)
	private String msgType;
	/**
	 * 图文消息个数，限制为10条以内
	 */
	@Element("ArticleCount")
	private String articleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 */
	@ListElement("Articles")
	@Element("item")
	private List<Item> articles;
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

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public List<Item> getArticles() {
		return articles;
	}

	public void setArticles(List<Item> articles) {
		this.articles = articles;
	}

	public BigDecimal getMsgId() {
		return msgId;
	}

	public void setMsgId(BigDecimal msgId) {
		this.msgId = msgId;
	}

	public class Item extends MediaMsg {
		/**
		 * 图文消息标题
		 */
		@Element("Title")
		@CDATA(true)
		private String Title;
		/**
		 * 图文消息描述
		 */
		@Element("Description")
		@CDATA(true)
		private String Description;
		/**
		 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
		 */
		@Element("PicUrl")
		@CDATA(true)
		private String PicUrl;
		/**
		 * 点击图文消息跳转链接
		 */
		@Element("Url")
		@CDATA(true)
		private String Url;

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

		public String getPicUrl() {
			return PicUrl;
		}

		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}

		public String getUrl() {
			return Url;
		}

		public void setUrl(String url) {
			Url = url;
		}
	}
}