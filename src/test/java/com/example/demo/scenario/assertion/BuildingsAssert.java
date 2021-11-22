package com.example.demo.scenario.assertion;

import com.example.demo.domain.Building;
import com.example.demo.domain.BuildingRepository;
import com.example.demo.domain.Player;
import com.innogames.scenariobuilder.Ref;

import org.assertj.core.api.AbstractAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.ApplicationContext;

import java.util.Optional;
import java.util.function.Consumer;

public class BuildingsAssert extends AbstractAssert<BuildingsAssert, ApplicationContext> {

	private final Ref<Player> playerRef;
	private final BuildingRepository buildingRepository;

	public BuildingsAssert(ApplicationContext applicationContext, Ref<Player> playerRef) {
		super(applicationContext, BuildingsAssert.class);
		this.playerRef = playerRef;
		this.buildingRepository = applicationContext.getBean(BuildingRepository.class);
	}

	public BuildingsAssert building(Ref<Building> buildingRef, Consumer<BuildingAssert> consumer) {
		Optional<Building> building = buildingRepository.findByPlayerIdAndBuildingId(playerRef.get().getId(), buildingRef.get().getId());
		Assertions.assertTrue(building.isPresent());
		consumer.accept(new BuildingAssert(building.get()));
		return this;
	}

}
