package com.example.demo.scenario.builder;

import com.example.demo.domain.Building;
import com.example.demo.domain.BuildingRepository;
import com.example.demo.domain.BuildingState;
import com.example.demo.scenario.given.GivenBuilding;
import com.example.demo.scenario.given.GivenCity;
import com.example.demo.scenario.given.GivenGameScenario;
import com.example.demo.scenario.given.GivenPlayer;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * A ScenarioBuilderPart that creates all {@link Building} entities configured in the {@link GivenGameScenario}.
 */
@Component
@RequiredArgsConstructor
public class BuildingBuilderPart implements ScenarioBuilderPart<GivenGameScenario> {

	private final BuildingRepository buildingRepository;

	@Override
	public int getOrder() {
		return BuilderPartOrder.BUILDING.ordinal();
	}

	@Override
	public void build(GivenGameScenario givenScenario) {
		givenScenario.getPlayers().forEach(givenPlayer ->
			givenPlayer.getCities().forEach(givenCity ->
				givenCity.getBuildings().forEach(givenBuilding ->
					createBuilding(givenPlayer, givenCity, givenBuilding)
				)
			)
		);
	}

	private void createBuilding(GivenPlayer givenPlayer, GivenCity givenCity, GivenBuilding givenBuilding) {
		var building = new Building();
		building.setId(UUID.randomUUID());
		building.setPlayerId(givenPlayer.getEntity().getId());
		building.setCityId(givenCity.getEntity().getId());
		building.setType(givenBuilding.getType());
		building.setLevel(givenBuilding.getLevel());
		building.setX(givenBuilding.getX());
		building.setY(givenBuilding.getY());
		building.setState(givenBuilding.getProduction() == null ? BuildingState.IDLE : BuildingState.PRODUCING);

		buildingRepository.save(building);

		givenBuilding.setEntity(building);
	}

}
