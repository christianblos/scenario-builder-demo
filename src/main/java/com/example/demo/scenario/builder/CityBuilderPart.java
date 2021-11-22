package com.example.demo.scenario.builder;

import com.example.demo.domain.City;
import com.example.demo.domain.CityRepository;
import com.example.demo.scenario.given.GivenCity;
import com.example.demo.scenario.given.GivenGameScenario;
import com.example.demo.scenario.given.GivenPlayer;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * A ScenarioBuilderPart that creates all {@link City} entities configured in the {@link GivenGameScenario}.
 */
@Component
@RequiredArgsConstructor
public class CityBuilderPart implements ScenarioBuilderPart<GivenGameScenario> {

	private final CityRepository cityRepository;

	@Override
	public int getOrder() {
		return BuilderPartOrder.CITY.ordinal();
	}

	@Override
	public void build(GivenGameScenario givenScenario) {
		givenScenario.getPlayers().forEach(givenPlayer ->
			givenPlayer.getCities().forEach(givenCity ->
				createCity(givenPlayer, givenCity)
			)
		);
	}

	private void createCity(GivenPlayer givenPlayer, GivenCity givenCity) {
		var city = new City();
		city.setId(UUID.randomUUID());
		city.setPlayerId(givenPlayer.getEntity().getId());
		city.setType(givenCity.getType());

		cityRepository.save(city);

		givenCity.setEntity(city);
	}

}
