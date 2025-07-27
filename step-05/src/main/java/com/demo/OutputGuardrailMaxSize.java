package com.demo;

import dev.langchain4j.data.message.AiMessage;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrail;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OutputGuardrailMaxSize implements OutputGuardrail {

    int maxLength = 2000;

    @Override
    public OutputGuardrailResult validate(AiMessage llmResponse) {
        String response = llmResponse.text();
        if (response.length() > maxLength) {
            return reprompt("Response too long.", "Make sure the response is no more than " + maxLength + " characters long.");
        }
        return success();
    }

}