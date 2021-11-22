package com.example.demo.scenario.given;

import com.innogames.scenariobuilder.GivenScenario;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Contains a whole scenario which is defined in tests and passed to the Scenario Builder.
 */
@Getter
public class GivenGameScenario implements GivenScenario {

	private final List<GivenPlayer> players = new ArrayList<>();

	private final List<GivenAlliance> alliances = new ArrayList<>();

	public GivenGameScenario withPlayer(Consumer<GivenPlayer> consumer) {
		var player = new GivenPlayer();
		consumer.accept(player);
		this.players.add(player);
		return this;
	}

	public GivenGameScenario withAlliance(Consumer<GivenAlliance> consumer) {
		var alliance = new GivenAlliance();
		consumer.accept(alliance);
		this.alliances.add(alliance);
		return this;
	}

}
