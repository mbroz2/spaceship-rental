package com.demo;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.SessionScoped;

import java.util.List;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;


@SessionScoped
@RegisterAiService
@SystemMessage("""
    You are a friendly, but terse customer service agent for Rocket's Cosmic Cruisers, a spaceship rental shop. 
    You answer questions from potential guests about the different planets they can visit.
    If asked about the planets, only use info from the fact sheet below. 
    """
    + PlanetInfo.PLANET_FACT_SHEET)
public interface CustomerSupportAgent {
    
    @OutputGuardrails({OutputGuardrailMaxSize.class, OutputGuardrailProhibitedTerms.class})
    String chat(String userMessage);
    
    @OutputGuardrails({OutputGuardrailMaxSize.class, OutputGuardrailProhibitedTerms.class})
    Multi<String> streamChat(String userMessage);

    SpaceshipQuery extractSpaceshipAttributes(String userMessage);

    @InputGuardrails(InputGuardrailMaxSize.class)
    @SystemMessage(
        """
        You are a friendly, but terse customer service agent for Rocket's Cosmic Cruisers, a spaceship rental shop. 
        Respond with 'SPACESHIP' if the user message is regarding spaceships in our rental fleet, 'SPACE_WEATHER' if it's about space travel weather/forecast/conditions (e.g. solar flares), and 'OTHER' if it's something else).
        """)
    QueryType queryType(String userMessage);

    @OutputGuardrails({OutputGuardrailMaxSize.class, OutputGuardrailProhibitedTerms.class})
    @UserMessage("""
        Given the user's query regarding available spaceships for a trip {{message}}, provide a well-formed, clear and concise response listing our applicable spaceships.
        Only use the spaceship fleet data from {{compatibleSpaceships}} for your response.
        """)
    String suggestSpaceships(String message, List<Spaceship> compatibleSpaceships);

    @OutputGuardrails({OutputGuardrailMaxSize.class, OutputGuardrailProhibitedTerms.class})
    @UserMessage("""
        Given the user's query regarding available spaceships for a trip {{message}}, provide a well-formed, clear and concise response listing our applicable spaceships.
        Only use the spaceship fleet data from {{compatibleSpaceships}} for your response.
        """)
    Multi<String> streamSuggestSpaceships(String message, List<Spaceship> compatibleSpaceships);
}
