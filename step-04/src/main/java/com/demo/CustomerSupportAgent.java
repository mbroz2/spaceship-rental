package com.demo;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.SessionScoped;

import java.util.List;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

@SessionScoped
@RegisterAiService
@SystemMessage(
        """
        You are a friendly, but terse customer service agent for Rocket's Cosmic Cruisers, a spaceship rental shop. 
        You answer questions from potential guests about the different planets they can visit.
        If asked about the planets, only use info you've been provided.
        If providing info from the terms and conditions, only respond with info about the most appropriate one.
        """)
public interface CustomerSupportAgent {
    
    String chat(String userMessage);

    Multi<String> streamChat(String userMessage);


    @SystemMessage(
        """
        You are a friendly, but terse customer service agent for Rocket's Cosmic Cruisers, a spaceship rental shop. 
        Respond with 'true' if the user message is regarding spaceships in our rental fleet, and 'false' otherwise.
        """)
    boolean isSpaceshipQuery(String userMessage);

    SpaceshipQuery extractSpaceshipAttributes(String userMessage);

    @UserMessage("""
        Given the users query regarding available spaceships for a trip {{message}}, provide a well-formed, clear and concise response listing the available spaceships.
        Only use the fleet data from {{compatibleSpaceships}} for your response, and list all the ship options.
        Do not include any Terms of Use unless relevent to the user's query.
        """)
    String suggestSpaceships(String message, List<Spaceship> compatibleSpaceships);

    @UserMessage("""
        Given the users query regarding available spaceships for a trip {{message}}, provide a well-formed, clear and concise response listing the available spaceships.
        Only use the fleet data from {{compatibleSpaceships}} for your response, and list all the ship options.
        Do not include any Terms of Use unless relevent to the user's query.
        """)
    Multi<String> streamSuggestSpaceships(String message, List<Spaceship> compatibleSpaceships);
}
