package com.hwl.websocket.controller.v2;

import com.hwl.websocket.model.InMessage;
import com.hwl.websocket.model.OutMessage;
import com.hwl.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class GameInfoV2Controller {

	@Autowired
	WebSocketService ws;
	
	@MessageMapping("/v1/chat")  //是用于客户端发送数据到服务端的路由配置
//	@SendTo("/topic/game_chat")
	public void gameInfo(InMessage message){
		System.out.println("发送消息是:{"+message.getContent()+"} \n发给姓名是:{"+message.getTo()+"}");
//		"/topic/game_chat"
		ws.sendTopicMessage(message.getTo(), message);
	}

	@MessageMapping("/v1/chat2")
//	@SendTo("/topic/game_chat")
	public void gameInfo2(String message){
		System.out.println("发送消息是:{"+message+"} ");
//		"/topic/game_chat"
		ws.sendTopicMessage("/topic/game_chat", new InMessage(message));
	}

	//@MessageMapping("/v1/chat")  //是用于客户端发送数据到服务端的路由配置
	@Scheduled(fixedRate = 3000)  //方法不能加参数
	public void sendServerInfo(){
		System.out.println("helloWorld");
		ws.sendServerInfo();
	}

}



