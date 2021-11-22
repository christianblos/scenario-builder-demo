package com.example.demo.scenario;

import com.example.demo.scenario.given.GivenGameScenario;
import com.innogames.scenariobuilder.ScenarioBuilderPart;
import com.innogames.scenariobuilder.junit5.ScenarioExtension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * The Junit Extension for the Scenario Builder.
 * See details here: https://github.com/innogames/junit5-scenario-builder/blob/master/docs/getting-started.md
 */
public class GameScenarioExtension extends ScenarioExtension<GivenGameScenario> {

	@SuppressWarnings("unchecked")
	@Override
	protected Collection<ScenarioBuilderPart<GivenGameScenario>> getBuilderParts(ExtensionContext extensionContext) {
		ApplicationContext applicationContext = SpringExtension.getApplicationContext(extensionContext);
		return applicationContext.getBeansOfType(ScenarioBuilderPart.class).values().stream()
			.map(part -> (ScenarioBuilderPart<GivenGameScenario>) part)
			.collect(Collectors.toList());
	}

	@Override
	protected GivenGameScenario createGivenScenario(ExtensionContext extensionContext) {
		return new GivenGameScenario();
	}

}
