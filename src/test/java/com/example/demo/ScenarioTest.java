package com.example.demo;

import com.example.demo.application.controller.ProductionController.SkipProductionRequest;
import com.example.demo.domain.Building;
import com.example.demo.domain.BuildingState;
import com.example.demo.domain.BuildingType;
import com.example.demo.domain.CityType;
import com.example.demo.domain.Player;
import com.example.demo.domain.ProductionType;
import com.example.demo.domain.Resource;
import com.innogames.scenariobuilder.Ref;

import org.junit.jupiter.api.Test;

/**
 * Example tests that use the Scenario Builder
 */
public class ScenarioTest extends BaseIntegrationTest {

	@Test
	public void scenarioTest() {
		var playerRef = new Ref<Player>();
		var farmRef = new Ref<Building>();

		scenarioBuilder.build(scenario -> scenario
			.withPlayer(player -> player
				.ref(playerRef)
				.withCity(CityType.CAPITAL, city -> city
					.withBuilding(BuildingType.FARM, farm -> farm
						.ref(farmRef)
						.withLevel(1)
						.withPosition(11, 12)
						.withProduction(ProductionType.FOOD, production -> production
							.withAmount(20)
						)
					)
				)
				.withResource(Resource.FOOD, 0)
				.withResource(Resource.GEMS, ProductionType.FOOD.getSkipCost())
			)
		);

		var response = gameClientOf(playerRef).sendRequest("/production/skip", SkipProductionRequest.builder()
			.buildingId(farmRef.get().getId())
			.build());

		assertThatResponse(response, res ->
			res.isOk()
		);

		assertThatDatabaseOf(playerRef, db -> db
			.resources(resources -> resources
				.hasAmount(Resource.FOOD, 20)
				.hasAmount(Resource.GEMS, 0)
			)
			.buildings(buildings -> buildings
				.building(farmRef, farm -> farm
					.hasState(BuildingState.IDLE)
				)
			)
		);
	}

	@Test
	public void allianceTest() {
		var aliceRef = new Ref<Player>();
		var bobRef = new Ref<Player>();

		scenarioBuilder.build(scenario -> scenario
			.withPlayer(player -> player
				.ref(aliceRef)
				.withName("alice")
			)
			.withPlayer(player -> player
				.ref(bobRef)
				.withName("bob")
			)
			.withAlliance(alliance -> alliance
				.withName("Fireflies")
				.withMember(aliceRef)
				.withMember(bobRef)
			)
		);

		// action and assertion here ...
	}

	@Test
	public void tradeOfferTest() {
		var aliceRef = new Ref<Player>();
		var bobRef = new Ref<Player>();

		scenarioBuilder.build(scenario -> scenario
			.withPlayer(player -> player
				.ref(aliceRef)
				.withName("alice")
				.withTradeOffer(tradeOffer -> tradeOffer
					.withResource(Resource.WOOD)
					.withAmount(100)
					.withAcceptedBy(bobRef)
				)
			)
			.withPlayer(player -> player
				.ref(bobRef)
				.withName("bob")
			)
		);

		// action and assertion here ...
	}

}
