package com.demo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import io.a2a.server.agentexecution.AgentExecutor;
import io.a2a.server.agentexecution.RequestContext;
import io.a2a.server.events.EventQueue;

import io.a2a.A2A;
import io.a2a.spec.JSONRPCError;
import io.a2a.spec.Message;
import io.a2a.spec.Part;
import io.a2a.spec.TextPart;
import io.a2a.spec.UnsupportedOperationError;

@ApplicationScoped

public class SpaceWeatherAgentExecutor {

    @Inject
    SpaceWeatherAgent spaceWeatherAgent;

    @Produces
    public AgentExecutor agentExecutor(SpaceWeatherAgent spaceWeatherAgent) {
        return new AgentExecutor() {
            @Override
            public void execute(RequestContext context, EventQueue eventQueue) throws JSONRPCError {
                
                // Process the request message
                StringBuilder textBuilder = new StringBuilder();
                Message message = context.getMessage();
                if (message.getParts() != null) {
                    for (Part<?> part : message.getParts()) {
                        if (part instanceof TextPart textPart) {
                            textBuilder.append(textPart.getText());
                        }
                    }
                }
                
                // Send response
                String agentResponse = spaceWeatherAgent.chat(textBuilder.toString());
                eventQueue.enqueueEvent(A2A.toAgentMessage(agentResponse));
            }

            @Override
            public void cancel(RequestContext context, EventQueue eventQueue) throws JSONRPCError {
                throw new UnsupportedOperationError();
            }
        };
    }
}