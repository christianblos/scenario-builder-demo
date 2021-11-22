package com.example.demo.scenario.given;

import com.example.demo.domain.CityType;
import com.example.demo.domain.Player;
import com.example.demo.domain.Resource;
import com.example.demo.scenario.builder.PlayerBuilderPart;
import com.innogames.scenariobuilder.EntityRefHolder;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Contains configuration of a Player in a {@link GivenGameScenario}.
 * It holds the related {@link Player} entity that is creates by the {@link PlayerBuilderPart}.
 */
@Getter
public class GivenPlayer extends EntityRefHolder<GivenPlayer, Player> {

	private String name;

	private final List<GivenCity> cities = new ArrayList<>();

	private final Map<Resource, Integer> resources = new HashMap<>();

	private final List<GivenTradeOffer> traceOffers = new ArrayList<>();

	public GivenPlayer withName(String name) {
		this.name = name;
		return this;
	}

	public GivenPlayer withResource(Resource resource, int amount) {
		this.resources.put(resource, amount);
		return this;
	}

	public GivenPlayer withCity(CityType type, Consumer<GivenCity> consumer) {
		var city = new GivenCity(type);
		consumer.accept(city);
		this.cities.add(city);
		return this;
	}

	public GivenPlayer withTradeOffer(Consumer<GivenTradeOffer> consumer) {
		var offer = new GivenTradeOffer();
		consumer.accept(offer);
		this.traceOffers.add(offer);
		return this;
	}

}
