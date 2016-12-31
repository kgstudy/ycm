package homework.websocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ChatSocket extends TextWebSocketHandler{
	Map<WebSocketSession, String> idMap;
	Map<String, WebSocketSession> sMap;
	String nick;
	@PostConstruct
	public void init(){
		idMap = new HashMap<>();
		sMap = new HashMap<>();		
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		System.out.println(session.getId()+"�씠(媛�) �젒�냽");
		System.out.println("shake:"+session.getHandshakeHeaders());
		System.out.println("uri:"+session.getUri());		
		List exl = session.getExtensions();
		System.out.println("ext:"+exl.toString());
		System.out.println("attr:"+session.getAttributes());		
		idMap.put(session, session.getId());	
	}
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		super.handleMessage(session, message);		
		System.out.println("Message:"+message);	
		String payLoad = (String)message.getPayload();
		System.out.println("payLoad: "+payLoad);		
		String[] ar = payLoad.split("#@");
		String nick = ar[0];
		String command = ar[1];
		String target = ar[2];
		System.out.println("command: "+command);		
		String content = payLoad.split("#@##")[1];
		System.out.println(content);
		switch(command){
		case "/all":
			System.out.println("�쟾泥� 硫붿꽭吏�/"+idMap.size());
			
			Iterator<WebSocketSession> it = idMap.keySet().iterator();
			while(it.hasNext()){
				
				WebSocketSession wss = it.next();			
				wss.sendMessage(new TextMessage(nick+" : "+content));
			}
			break;
		case "/w":			
			System.out.println(target+"]�뿉寃� 洹볥쭚以�");						
			WebSocketSession targetSession = sMap.get(target);
			targetSession.sendMessage(new TextMessage(nick+"�떂�쓽 洹볦냽留�: "+content));
			session.sendMessage(new TextMessage(nick+"�떂�뿉寃�: "+content));	
			break;
		case "/nick":
			if(idMap.containsValue(nick)){
				idMap.put(session, target);
				sMap.put(target, sMap.remove(nick));
				session.sendMessage(new TextMessage("�땳�꽕�엫 蹂�寃� �꽦怨�"));
			}else if(idMap.containsValue(target)){
				session.sendMessage(new TextMessage("議댁옱�븯�뒗 nick�엯�땲�떎."));
			}else{
				idMap.put(session, target);
				if(sMap.containsKey(nick)){
					sMap.put(target, sMap.remove(nick));
				}else{
					sMap.put(nick, session);
				}			
				session.sendMessage(new TextMessage("�땳�꽕�엫 蹂�寃� �꽦怨�"));
			}		
			break;
		default :
			break;
		}		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("梨꾪똿 �빐�젣");
		sMap.remove(idMap.remove(session));
		
		
		System.out.println("idMap:"+idMap.toString());
		System.out.println("sMap:"+idMap.toString());
	}	
	
}
