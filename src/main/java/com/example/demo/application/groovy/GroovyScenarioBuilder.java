package com.example.demo.application.groovy;

import com.example.demo.scenario.given.GivenGameScenario;
import com.innogames.scenariobuilder.ScenarioBuilder;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Adds the ScenarioBuilder as Spring bean for the "Build Scenario" endpoint.
 */
@Component
public class GroovyScenarioBuilder extends ScenarioBuilder<GivenGameScenario> {

	public GroovyScenarioBuilder(List<ScenarioBuilderPart<GivenGameScenario>> builderParts) {
		super(() -> new GivenGameScenario(), builderParts);
	}

}
