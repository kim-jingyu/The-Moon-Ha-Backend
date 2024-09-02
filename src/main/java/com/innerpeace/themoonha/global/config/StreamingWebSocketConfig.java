package com.innerpeace.themoonha.global.config;

import com.innerpeace.themoonha.global.handler.StreamingWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class StreamingWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new StreamingWebSocketHandler(), "/streaming").setAllowedOrigins("*");
    }
}
