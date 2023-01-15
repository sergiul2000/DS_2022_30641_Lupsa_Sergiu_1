package com.example.DS_2022_30641_Lupsa_Sergiu_1_Backend.entities;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //tema2
                config.enableSimpleBroker("/topic","/user");
        config.setApplicationDestinationPrefixes("/chat");

        //linie adaugata la tema3
//        config.setApplicationDestinationPrefixes("/app");
//        config.enableSimpleBroker("/chatroom","/user");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-message").setAllowedOrigins("*").withSockJS();
    }
}