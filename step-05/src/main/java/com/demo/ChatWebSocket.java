package com.demo;

import java.util.List;

import io.a2a.A2A;
import io.a2a.client.A2AClient;
import io.a2a.spec.AgentCard;
import io.a2a.spec.EventKind;
import io.a2a.spec.Message;
import io.a2a.spec.MessageSendParams;
import io.a2a.spec.Part;
import io.a2a.spec.SendMessageResponse;
import io.a2a.spec.TextPart;
import io.quarkiverse.langchain4j.runtime.aiservice.GuardrailException;
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
        try {
            QueryType queryType = customerSupportAgent.queryType(message);

            // Route to appropriate agent based on query type
            if (queryType.equals(QueryType.SPACESHIP)) {
                SpaceshipQuery userQuery = customerSupportAgent.extractSpaceshipAttributes(message);
                List<Spaceship> spaceships = Fleet.findCompatibleSpaceships(userQuery);
                return customerSupportAgent.suggestSpaceships(message, spaceships);
            } else if (queryType.equals(QueryType.SPACE_WEATHER)) {
                // Use A2A protocol to route request to another Agent
                String SERVER_URL = "http://localhost:8080/";
                AgentCard agentCard = A2A.getAgentCard(SERVER_URL);
                A2AClient client = new A2AClient(agentCard);
                Message msg = A2A.toUserMessage(message);
                MessageSendParams params = new MessageSendParams.Builder()
                    .message(msg)
                    .build();
                SendMessageResponse response = client.sendMessage(params);

                // Get response from Agent
                EventKind result = response.getResult();
                if (result instanceof Message responseMessage) {
                    StringBuilder textBuilder = new StringBuilder();
                    if (responseMessage.getParts() != null) {
                        for (Part<?> part : responseMessage.getParts()) {
                            if (part instanceof TextPart textPart) {
                                textBuilder.append(textPart.getText());
                            }
                        }
                    }
                    return textBuilder.toString();
                }
                // Fallback to Customer Support Agent
                return customerSupportAgent.chat(message);
            } else 
                return customerSupportAgent.chat(message);
        } catch (GuardrailException e) {
            return "The input message exceeded the maximum length.";
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            return "An error occurred, please try again later.";
        }
    }
}
