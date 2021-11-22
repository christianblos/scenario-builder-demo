package com.example.demo.application.controller;

import com.example.demo.application.groovy.GroovyScenarioBuilder;
import com.example.demo.application.groovy.GroovyShellFactory;
import com.example.demo.application.groovy.ScenarioScriptProperties;
import com.example.demo.domain.GameEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ScenarioController {

	private final GroovyShellFactory groovyShellFactory;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final GroovyScenarioBuilder scenarioBuilder;

	/**
	 * Accepts a groovy script as the request body to build a scenario.
	 * Example:
	 *
	 * <pre>
	 * scenario
	 * 	 .withPlayer(player -> player
	 * 	   .ref(namedRef("player"))
	 * 	   .withName('alice')
	 * 	 )
	 * </pre>
	 */
	@PostMapping(value = "/build-scenario", produces = MediaType.APPLICATION_JSON_VALUE)
	public String buildScenario(@RequestBody String request) throws JsonProcessingException {
		var groovyShell = groovyShellFactory.createSandboxShell();

		scenarioBuilder.build(scenario -> {
			// Set scenario property that can be accessed in the groovy script
			groovyShell.setProperty("scenario", scenario);

			// Run requested groovy script which configures the scenario object
			groovyShell.evaluate(request);
		});

		// Return entityIds that were referenced in the groovy script
		Map<String, String> ids = new HashMap<>();
		ScenarioScriptProperties.getEntityReferences(groovyShell).forEach((name, ref) -> {
			if (ref.get() instanceof GameEntity) {
				ids.put(name, ((GameEntity) ref.get()).getId().toString());
			}
		});

		return objectMapper.writeValueAsString(ids);
	}

}
