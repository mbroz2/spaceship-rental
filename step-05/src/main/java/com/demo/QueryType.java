package com.demo;

import dev.langchain4j.model.output.structured.Description;

@Description("Determines the type of query the user is asking about")
public enum QueryType {
    SPACESHIP,
    SPACE_WEATHER,
    OTHER
}
