package com.example.demo.scenario.builder;

import com.example.demo.domain.Player;
import com.example.demo.domain.PlayerRepository;
import com.example.demo.scenario.given.GivenGameScenario;
import com.example.demo.scenario.given.GivenPlayer;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * A ScenarioBuilderPart that creates all {@link Player} entities configured in the {@link GivenGameScenario}.
 */
@Component
@RequiredArgsConstructor
public class PlayerBuilderPart implements ScenarioBuilderPart<GivenGameScenario> {

	private final PlayerRepository playerRepository;

	@Override
	public int getOrder() {
		return BuilderPartOrder.PLAYER.ordinal();
	}

	@Override
	public void build(GivenGameScenario givenScenario) {
		givenScenario.getPlayers().forEach(this::createPlayer);
	}

	private void createPlayer(GivenPlayer givenPlayer) {
		var player = new Player();
		player.setId(UUID.randomUUID());
		player.setName(givenPlayer.getName() == null ? "test" : givenPlayer.getName());

		playerRepository.save(player);

		givenPlayer.setEntity(player);
	}

}
