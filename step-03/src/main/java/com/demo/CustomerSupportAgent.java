package com.demo;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.SessionScoped;
import dev.langchain4j.service.SystemMessage;

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
}
