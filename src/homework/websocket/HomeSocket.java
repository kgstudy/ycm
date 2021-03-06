package homework.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class HomeSocket extends TextWebSocketHandler{
	Map<WebSocketSession, String> idMap;
	Map<String, WebSocketSession> sMap;
	List<String> rankList;
	String nick;
	
	@PostConstruct
	public void init(){
		idMap = new HashMap<>();
		sMap = new HashMap<>();
		rankList = new ArrayList();
	}
	
	public void setRank(String id){
		rankList.add(id);
		sendRank(id);
	}
	
	public void sendRank(String id){
		Iterator<WebSocketSession> it = idMap.keySet().iterator();
		System.out.println("sendRank: "+id);
		System.out.println(idMap.size());
		while(it.hasNext()){			
			WebSocketSession wss = it.next();			
			try {			
					String payLoad = "{"+"\""+rankList.size()+"\":"+id+"}";
					wss.sendMessage(new TextMessage(payLoad));				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId()+"로 연결");
		idMap.put(session, session.getId());		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {		
		String payLoad = (String)message.getPayload();
		System.out.println(payLoad);		
		if(payLoad.equals("init")){			
			if(rankList.size()>0){
				String rankMap = "";
				int i = 0;
				for(i = 0; i < rankList.size()-1; i++){					
					rankMap += "\""+(i+1)+"\":"+rankList.get(i)+",";
				}
				rankMap = "{"+rankMap+"\""+(i+1)+"\":"+rankList.get(i)+"}";
				session.sendMessage(new TextMessage(rankMap));
			}
		}
		session.sendMessage(message);
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		sMap.remove(idMap.remove(session));		
		System.out.println(session.getId()+"이 종료");
	}
}
