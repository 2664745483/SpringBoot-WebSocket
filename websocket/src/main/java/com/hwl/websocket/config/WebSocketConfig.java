package com.hwl.websocket.config;

import com.hwl.websocket.intecepter.HttpHandShakeIntecepter;
import com.hwl.websocket.intecepter.SocketChannelIntecepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	
	/**
	 * 注册端点，发布或者订阅消息的时候需要连接此端点 已有手机连接基站基站的名称是 /endpoint-websocket
	 * * 注册端点，发布或者订阅消息的时候需要连接此端点 已有手机连接基站基站的名称是 /endpoint-websocket
	 * setAllowedOriginPatterns 是可以跨域连接的
	 * withSockJS  表示开始sockejs支持
	 */
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("2.开启消息基站"+registry.toString());

		registry.addEndpoint("/endpoint-websocket").addInterceptors(new HttpHandShakeIntecepter()).setAllowedOriginPatterns("*").withSockJS();

//		registry.addEndpoint("/endpoint-websocket").setAllowedOrigins("*").withSockJS();

	}

	/**
	 * 配置消息代理(中介)
	 * enableSimpleBroker 服务端推送给客户端的路径前缀
	 * setApplicationDestinationPrefixes  客户端发送数据给服务器端的一个前缀
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		System.out.println("1.消息中间代理:"+registry.toString());

		registry.enableSimpleBroker("/topic", "/chat");
		registry.setApplicationDestinationPrefixes("/app");
		
	}
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors( new SocketChannelIntecepter());
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		registration.interceptors( new SocketChannelIntecepter());
	}

	
	
	
	
}


