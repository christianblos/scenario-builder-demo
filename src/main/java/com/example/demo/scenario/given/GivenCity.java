package com.example.demo.scenario.given;

import com.example.demo.domain.BuildingType;
import com.example.demo.domain.City;
import com.example.demo.domain.CityType;
import com.example.demo.scenario.builder.CityBuilderPart;
import com.innogames.scenariobuilder.EntityRefHolder;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Contains configuration of a City in a {@link GivenGameScenario}.
 * It holds the related {@link City} entity that is creates by the {@link CityBuilderPart}.
 */
@Getter
public class GivenCity extends EntityRefHolder<GivenCity, City> {

	private final CityType type;

	private final List<GivenBuilding> buildings = new ArrayList<>();

	public GivenCity(CityType type) {
		this.type = type;
	}

	public GivenCity withBuilding(BuildingType type, Consumer<GivenBuilding> consumer) {
		var building = new GivenBuilding(type);
		consumer.accept(building);
		this.buildings.add(building);
		return this;
	}

}
