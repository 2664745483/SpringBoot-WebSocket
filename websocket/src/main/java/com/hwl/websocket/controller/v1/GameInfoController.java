package com.hwl.websocket.controller.v1;

import com.hwl.websocket.model.InMessage;
import com.hwl.websocket.model.OutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameInfoController {

	
//	@MessageMapping("/v1/chat")
//	@SendTo("/topic/game_chat")
	public OutMessage gameInfo(InMessage message){
		System.out.println("简单发送一个消息给客户端"+message.toString());
		return new OutMessage(message.getContent());
	}


}



