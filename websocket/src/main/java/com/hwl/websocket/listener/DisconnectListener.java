package com.hwl.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class DisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    /**
     * 取消订阅事件
     * @param disconnectListener
     */
    @Override
    public void onApplicationEvent(SessionDisconnectEvent disconnectListener) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap(disconnectListener.getMessage());

        System.out.println("4.断开连接事件"+wrap.getCommand().toString());
    }
}
