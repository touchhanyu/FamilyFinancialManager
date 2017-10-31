package com.ffm.websocket.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.ffm.sys.entity.SysUser;

@ServerEndpoint(value = "/websocket", configurator = WebSocketHttpSessionCfg.class)
public class WebSocketService {
	private Logger logger = Logger.getLogger(WebSocketService.class);
	private static Map<Integer, Session> sessions = new HashMap<Integer, Session>();

	@OnOpen
	public void onOpen(Session session, EndpointConfig sec) {
		Object object = sec.getUserProperties().get("session");
		if (object != null) {
			HttpSession hs = (HttpSession) object;
			SysUser user = (SysUser) hs.getAttribute("user");
			sessions.put(user.getId(), session);
			logger.debug(user.getName() + "建立连接...");
		}
	}

	@OnMessage
	public void onMessage(String msg) {
		logger.debug(msg);
	}

	@OnClose
	public void onClose() {
		logger.debug("连接关闭...");
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		throw t;
	}
}