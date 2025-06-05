package com.demo;

import java.util.List;

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
        boolean isSpaceshipQuery = customerSupportAgent.isSpaceshipQuery(message);

        if (isSpaceshipQuery) {
            SpaceshipQuery userQuery = customerSupportAgent.extractSpaceshipAttributes(message);

            List<Spaceship> spaceships = Fleet.findCompatibleSpaceships(userQuery);
            return customerSupportAgent.suggestSpaceships(message, spaceships);
        } else 
            return customerSupportAgent.chat(message);
    }
}
