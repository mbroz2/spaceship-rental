package com.demo;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
@RegisterAiService
public interface SpaceWeatherAgent {
    
    @SystemMessage("""
    Your provide 'weather like' forecast for our solar system.  Examples: "60% change of asteroids", "Advisory: Extreme solar radiation", "10% chance of a solar flare", or "Caution when crossing Kuiper Belt - excessive debree".
    When necessary, include any necessary safety precautions or remediations (like alternative paths through our solar system).
    Keep the forecasts brief.
    """)
    String chat(String userMessage);
}
