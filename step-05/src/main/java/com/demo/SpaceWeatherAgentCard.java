package com.demo;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import io.a2a.server.PublicAgentCard;
import io.a2a.spec.AgentCapabilities;
import io.a2a.spec.AgentCard;
import io.a2a.spec.AgentSkill;

@ApplicationScoped
public class SpaceWeatherAgentCard {

    @Produces
    @PublicAgentCard
    public AgentCard agentCard() {
        return new AgentCard.Builder()
                .name("Space Weather Agent")
                .description("Provides weather forecast for our solar system")
                .url("http://localhost:8080/")
                .version("1.0.0")
                .documentationUrl("http://example.com/docs")
                .capabilities(new AgentCapabilities.Builder()
                        .streaming(true)
                        .pushNotifications(true)
                        .stateTransitionHistory(true)
                        .build())
                .defaultInputModes(Collections.singletonList("text"))
                .defaultOutputModes(Collections.singletonList("text"))
                .skills(Collections.singletonList(new AgentSkill.Builder()
                                .id("space_weather")
                                .name("Space Weather Forecast")
                                .description("Provides weather forecast for our solar system")
                                .tags(List.of("space weather", "space forecast"))
                                .examples(List.of("10% chance of a solar flare", "Advisory: Extreme solar radiation"))
                                .build()))
                .protocolVersion("0.2.5")
                .build();
    }
}