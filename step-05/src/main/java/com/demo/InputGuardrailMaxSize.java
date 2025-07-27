package com.demo;

import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import dev.langchain4j.data.message.UserMessage;

@ApplicationScoped
public class InputGuardrailMaxSize implements InputGuardrail {

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String text = userMessage.singleText();
        if (text.length() > 2000) {
            return fatal("Input too long, size = " + text.length());
        }
        return success();
    }
}