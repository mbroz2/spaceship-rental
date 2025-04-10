package com.demo;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.mutiny.Multi;

@WebSocket(path = "/chat/stream")
public class ChatWebSocketStream {

    private final CustomerSupportAgent customerSupportAgent;

    public ChatWebSocketStream(CustomerSupportAgent customerSupportAgent) {
        this.customerSupportAgent = customerSupportAgent;
    }

    @OnOpen
    public String onOpen() {
        return "Welcome to Rocket's Cosmic Cruisers! How can I help you today?";
    }

    @OnTextMessage
    public Multi<String> onStreamingTextMessage(String message) {
        return customerSupportAgent.streamChat(message);
    }
    
}
