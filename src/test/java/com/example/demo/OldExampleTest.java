package com.example.demo;

import com.example.demo.domain.BuildingType;
import com.example.demo.domain.CityType;
import com.example.demo.domain.ProductionType;
import com.example.demo.domain.Resource;
import com.example.demo.examplehelper.BuildingHelper;
import com.example.demo.examplehelper.CityHelper;
import com.example.demo.examplehelper.PlayerHelper;
import com.example.demo.examplehelper.ProductionHelper;
import com.example.demo.examplehelper.ResourceHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is just an example how a test could look like without the Scenario Builder.
 */
public class OldExampleTest extends BaseIntegrationTest {

	@Autowired
	private PlayerHelper playerHelper;

	@Autowired
	private CityHelper cityHelper;

	@Autowired
	private BuildingHelper buildingHelper;

	@Autowired
	private ProductionHelper productionHelper;

	@Autowired
	private ResourceHelper resourceHelper;

	@Test
	public void test() {
		var player = playerHelper.createPlayer();
		var city = cityHelper.createCity(player, CityType.CAPITAL);
		var farm = buildingHelper.placeBuilding(city, BuildingType.FARM, 1, 11, 12);
		productionHelper.createRunningProduction(farm, ProductionType.FOOD);

		resourceHelper.setResource(player, Resource.GEMS, 1000);

		// send request to /production/skip

		// assertions
	}

}
