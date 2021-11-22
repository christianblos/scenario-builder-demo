package com.example.demo.scenario.given;

import com.example.demo.domain.Building;
import com.example.demo.domain.BuildingType;
import com.example.demo.domain.ProductionType;
import com.example.demo.scenario.builder.BuildingBuilderPart;
import com.innogames.scenariobuilder.EntityRefHolder;

import lombok.Getter;

import java.util.function.Consumer;

/**
 * Contains configuration of a Building in a {@link GivenGameScenario}.
 * It holds the related {@link Building} entity that is creates by the {@link BuildingBuilderPart}.
 */
@Getter
public class GivenBuilding extends EntityRefHolder<GivenBuilding, Building> {

	private final BuildingType type;

	private int level;

	private int x;

	private int y;

	private GivenProduction production;

	public GivenBuilding(BuildingType type) {
		this.type = type;
	}

	public GivenBuilding withLevel(int level) {
		this.level = level;
		return this;
	}

	public GivenBuilding withPosition(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public GivenBuilding withProduction(ProductionType type) {
		this.production = new GivenProduction(type);
		return this;
	}

	public GivenBuilding withProduction(ProductionType type, Consumer<GivenProduction> consumer) {
		this.production = new GivenProduction(type);
		consumer.accept(this.production);
		return this;
	}

}
