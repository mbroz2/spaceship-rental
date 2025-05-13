# step-04

This application builds on step-03 with an example of using structured outputs from LLMs.

## Using the application

1. Access the application locally at http://localhost:8080/
2. Launch the chatbot by clicking on the agent icon in the bottom right corner of the UI.
3. Toggle streaming on/off in the chatbot dialog and observe the difference in the output when asking the chatbot questions, such as:
  - What's a good planet to visit if I want to see volcanoes?
  - Where should I go if I want to be able to see the earth rise/set?
  - What's a good place to go to get out of the cold?
4. Experiment with RAG by asking questions related to the "Terms of Use" document:
  - Can I get a refund if I cancel my booking 9 days in advance?
  - I locked my keys in the spaceship
  - I'm on Neptune and my spaceship broke down
5. Utilize structured outputs to help the LLM choose the best spaceships for a journey:
  -  I'd like to go visit Venus with my best friend, what spaceships are available for that?
  -  Which spaceship can travel outside of our solar system with 3 passengers?
  -  I want to travel to Saturn with 5 friends to collect rocks and ice from the rings and bring them back. Which of your spaceships is best for that?

## Related Guides

- LangChain4j Ollama ([guide](https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html)): Provides the basic integration of Ollama with LangChain4j
- WebSockets Next ([guide](https://quarkus.io/guides/websockets-next-reference)): Implementation of the WebSocket API with enhanced efficiency and usability
