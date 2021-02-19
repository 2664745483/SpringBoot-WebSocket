package com.hwl.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class ConnectEventListener  implements ApplicationListener<SessionConnectEvent> {

    /**
     * 在事件触发的时候执行此方法  连接成功后执行此方法
     * @param sessionSubscribeEvent
     */
    @Override
    public void onApplicationEvent(SessionConnectEvent sessionSubscribeEvent) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
//        sessionSubscribeEvent
        System.out.println("1.执行连接事件"+wrap.getCommand().getMessageType());
    }

}
