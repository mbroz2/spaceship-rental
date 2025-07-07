package com.demo;

import java.util.Arrays;
import java.util.List;

import dev.langchain4j.data.message.AiMessage;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrail;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OutputGuardrailProhibitedTerms implements OutputGuardrail {

    List<String> prohibitedTerms = Arrays.asList("aaa", "zzz");

    @Override
    public OutputGuardrailResult validate(AiMessage llmResponse) {
        String response = llmResponse.text();
        for (String prohibitedTerm : prohibitedTerms){
            if (response.toLowerCase().contains(prohibitedTerm.toLowerCase())) {
                return reprompt("Response contains a prohibited term.", "Make sure the response does not contain any of the following words: " + prohibitedTerms);
             }
        }
        return success();
    }

}