package com.ffm.websocket.service;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class WebSocketHttpSessionCfg extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// TODO Auto-generated method stub
		Object object = request.getHttpSession();
		if (object != null) {
			HttpSession session = (HttpSession) object;
			sec.getUserProperties().put("session", session);
		}
	}
}