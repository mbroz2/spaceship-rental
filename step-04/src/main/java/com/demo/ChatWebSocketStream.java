package com.demo;

import java.util.List;

import io.quarkiverse.langchain4j.runtime.aiservice.GuardrailException;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.common.annotation.Blocking;
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
    @Blocking
    public Multi<String> onStreamingTextMessage(String message) {
        try {
            boolean isSpaceshipQuery = customerSupportAgent.isSpaceshipQuery(message);

            if (isSpaceshipQuery) {
                SpaceshipQuery userQuery = customerSupportAgent.extractSpaceshipAttributes(message);
                
                List<Spaceship> spaceships = Fleet.findCompatibleSpaceships(userQuery);
                return customerSupportAgent.streamSuggestSpaceships(message, spaceships);
            } else 
                return customerSupportAgent.streamChat(message);
        } catch (GuardrailException e) {
            Multi<String> result = Multi.createFrom().item("The input message exceeded the maximum length.");
            return result;
        }
        
    }
}
