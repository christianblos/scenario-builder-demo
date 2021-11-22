package com.example.demo.scenario.builder;

import com.example.demo.domain.ResourceRepository;
import com.example.demo.scenario.given.GivenGameScenario;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

/**
 * A ScenarioBuilderPart that initializes player resources configured in the {@link GivenGameScenario}.
 */
@Component
@RequiredArgsConstructor
public class ResourceBuilderPart implements ScenarioBuilderPart<GivenGameScenario> {

	private final ResourceRepository resourceRepository;

	@Override
	public int getOrder() {
		return BuilderPartOrder.RESOURCE.ordinal();
	}

	@Override
	public void build(GivenGameScenario givenScenario) {
		givenScenario.getPlayers().forEach(givenPlayer ->
			givenPlayer.getResources().forEach((resource, amount) ->
				resourceRepository.set(givenPlayer.getEntity().getId(), resource, amount)
			)
		);
	}

}
