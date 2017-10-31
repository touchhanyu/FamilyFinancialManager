package com.ffm.wechat.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ffm.common.action.BaseAction;
import com.ffm.wechat.entity.MsgType;
import com.ffm.wechat.service.WechatMsgService;

@SuppressWarnings("serial")
public class WetchatAction extends BaseAction {
	/* 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数 */
	private String signature;// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
	private String timestamp;// 时间戳
	private String nonce;// 随机数
	private String echostr;// 随机字符串
	private String encrypt_type;
	private String msg_signature;

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}

	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}

	private WechatMsgService service;

	public void setService(WechatMsgService service) {
		this.service = service;
	}

	public void receiveInfo() throws IOException {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("signature", this.signature);
		hm.put("timestamp", this.timestamp);
		hm.put("nonce", this.nonce);
		hm.put("encrypt_type", this.encrypt_type);
		hm.put("msg_signature", this.msg_signature);
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream is = request.getInputStream();
		String xml = this.service.dealService(MsgType.CIPHER, is, null, hm);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(xml);
	}
}