package com.demo;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;

@WebSocket(path = "/chat/batch")
public class ChatWebSocket {

    private final CustomerSupportAgent customerSupportAgent;

    public ChatWebSocket(CustomerSupportAgent customerSupportAgent) {
        this.customerSupportAgent = customerSupportAgent;
    }

    @OnOpen
    public String onOpen() {
        return "Welcome to Rocket's Cosmic Cruisers! How can I help you today?";
    }

    @OnTextMessage
    public String onTextMessage(String message) {
        return customerSupportAgent.chat(message);
    }
}
