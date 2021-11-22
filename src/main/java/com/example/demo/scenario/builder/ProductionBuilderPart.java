package com.example.demo.scenario.builder;

import com.example.demo.domain.Production;
import com.example.demo.domain.ProductionRepository;
import com.example.demo.scenario.given.GivenBuilding;
import com.example.demo.scenario.given.GivenGameScenario;
import com.example.demo.scenario.given.GivenProduction;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

/**
 * A ScenarioBuilderPart that creates all {@link Production} entities configured in the {@link GivenGameScenario}.
 */
@Component
@RequiredArgsConstructor
public class ProductionBuilderPart implements ScenarioBuilderPart<GivenGameScenario> {

	private final ProductionRepository productionRepository;

	@Override
	public int getOrder() {
		return BuilderPartOrder.PRODUCTION.ordinal();
	}

	@Override
	public void build(GivenGameScenario givenScenario) {
		givenScenario.getPlayers().forEach(givenPlayer ->
			givenPlayer.getCities().forEach(givenCity ->
				givenCity.getBuildings().stream()
					.filter(givenBuilding -> givenBuilding.getProduction() != null)
					.forEach(givenBuilding -> createProduction(givenBuilding, givenBuilding.getProduction()))
			)
		);
	}

	private void createProduction(GivenBuilding givenBuilding, GivenProduction givenProduction) {
		var production = new Production();
		production.setId(UUID.randomUUID());
		production.setBuildingId(givenBuilding.getEntity().getId());
		production.setType(givenProduction.getType());
		production.setAmount(givenProduction.getAmount());
		production.setStartedAt(givenProduction.getFinishAt() == null
			? Instant.now()
			: givenProduction.getFinishAt().minus(production.getType().getDuration()));
		production.setFinishAt(production.getStartedAt().plus(production.getType().getDuration()));

		productionRepository.save(production);

		givenProduction.setEntity(production);
	}

}
