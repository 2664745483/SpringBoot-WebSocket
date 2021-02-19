package com.hwl.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {

    /**
     * 在事件触发的时候执行此方法  连接成功后执行此方法SubscribeEventListener监听事件类订阅事件
     * @param sessionSubscribeEvent
     */
    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());

        System.out.println("2.执行订阅事件"+wrap.getCommand().toString());
    }
}
