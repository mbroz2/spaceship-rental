package com.demo;

import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.SessionScoped;
import dev.langchain4j.service.SystemMessage;

@SessionScoped
@RegisterAiService
public interface CustomerSupportAgent {
    @SystemMessage("""
        You are a friendly, but terse customer service agent for Rocket's Cosmic Cruisers, a spaceship rental shop. 
        You answer questions from potential guests about the different planets they can visit.
        If asked about the planets, only use info from the fact sheet below. 
        """ 
        + PlanetInfo.PLANET_FACT_SHEET)
    String chat(String userMessage);
}
