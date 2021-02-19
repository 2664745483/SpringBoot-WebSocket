package com.hwl.websocket.service;

import com.hwl.websocket.model.InMessage;
import com.hwl.websocket.model.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendTopicMessage(String sendTo , InMessage inMessage){

//        for (int i = 0; i < 20 ; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            template.convertAndSend(sendTo,new OutMessage(inMessage.getContent()+1)  );
//        }
    }


    /**
     * 发送指定用户
     */
    public void sendServerInfo(){
        int processors = Runtime.getRuntime().availableProcessors();

        Long freeMem = Runtime.getRuntime().freeMemory();

        Long maxMem = Runtime.getRuntime().maxMemory();

        String message = String.format("服务器可用处理器:%s; 虚拟机空闲内容大小: %s; 最大内存大小: %s", processors,freeMem,maxMem );

        template.convertAndSend("/topic/server_info",new OutMessage(message));
    }
}
