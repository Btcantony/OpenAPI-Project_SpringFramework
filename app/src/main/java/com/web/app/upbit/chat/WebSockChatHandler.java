package com.web.app.upbit.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.web.app.upbit.run.Api;
import com.web.app.upbit.vo.CashMemory;
import com.web.app.upbit.vo.ChatMsg;
import com.web.app.upbit.vo.UpbitClient;

@Component
public class WebSockChatHandler extends TextWebSocketHandler {
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	// 클라이언트가 서버로 연결 처리
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		// 채팅방에 접속한 사용자 세션을 리스트에 저장
		sessionList.add(session);

		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage("{\"message\":\""+s.getId()+" 가 입장 하였습니다.\"}"));			
		}
	}

	// 클라이언트가 서버로 메세지 전송 처리
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		ChatMsg chatMsg = new Gson().fromJson(msg, ChatMsg.class);
		
		if(chatMsg.getType().equals("market")) {
			Api api = new Api();
			String ret = new Gson().toJson(api.markets());
			for (int i = 0; i < sessionList.size(); i++) {
				WebSocketSession s = sessionList.get(i);
				s.sendMessage(new TextMessage(ret));
			}
		}
		
		UpbitClient upbitClient = new UpbitClient();
		if(chatMsg.getType().equals("price")) {
			upbitClient.setKrw_money(CashMemory.krw_money);
			upbitClient.setAll_money(CashMemory.all_money);
			upbitClient.setCashMemoryListTickerVo(CashMemory.CashMemoryListTickerVo);
			
			for (int i = 0; i < sessionList.size(); i++) {
				WebSocketSession s = sessionList.get(i);
				s.sendMessage(new TextMessage(new Gson().toJson(upbitClient)));				
			}
		}
		// 모든 세션에 채팅 전달
		/*for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			//s.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
			s.sendMessage(new TextMessage(new Gson().toJson(CashMemory.CashMemoryListTickerVo)));
		}*/
	}

	// 클라이언트가 연결을 끊음 처리
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		// 채팅방에서 퇴장한 사용자 세션을 리스트에서 제거
		sessionList.remove(session);

		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			//s.sendMessage(new TextMessage(session.getId() + "님이 퇴장 했습니다."));
		}
	}
}
