package com.example.demo.scenario.assertion;

import com.example.demo.domain.Building;
import com.example.demo.domain.BuildingState;

import org.assertj.core.api.AbstractAssert;
import org.junit.jupiter.api.Assertions;

public class BuildingAssert extends AbstractAssert<BuildingAssert, Building> {

	public BuildingAssert(Building building) {
		super(building, BuildingAssert.class);
	}

	public BuildingAssert hasState(BuildingState state) {
		Assertions.assertEquals(actual.getState(), state);
		return this;
	}

}
