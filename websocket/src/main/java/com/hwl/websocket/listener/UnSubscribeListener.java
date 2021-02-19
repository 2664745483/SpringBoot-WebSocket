package com.hwl.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class UnSubscribeListener implements ApplicationListener<SessionUnsubscribeEvent> {

    /**
     * 取消订阅事件
     * @param sessionUnSubscribeEvent
     */
    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent sessionUnSubscribeEvent) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap(sessionUnSubscribeEvent.getMessage());

        System.out.println("3.取消订阅事件"+wrap.getCommand().toString());
    }
}
