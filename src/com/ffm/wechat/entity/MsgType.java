package com.ffm.wechat.entity;

public enum MsgType {
	/**
	 * 明文
	 */
	CLEAR("clear"),
	/**
	 * 密文
	 */
	CIPHER("cipher"),
	/**
	 * 文本
	 */
	TEXT("text"),
	/**
	 * 图片
	 */
	IMAGE("image"),
	/**
	 * 语音
	 */
	VOICE("voice"),
	/**
	 * 视频
	 */
	VIDEO("video"),
	/**
	 * 音乐
	 */
	MUSIC("music"),
	/**
	 * 图文
	 */
	ARTICLE("news");
	private String value;

	private MsgType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}