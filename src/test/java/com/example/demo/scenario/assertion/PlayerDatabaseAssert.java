package com.example.demo.scenario.assertion;

import com.example.demo.domain.Player;
import com.innogames.scenariobuilder.Ref;

import org.assertj.core.api.AbstractAssert;
import org.springframework.context.ApplicationContext;

import java.util.function.Consumer;

public class PlayerDatabaseAssert extends AbstractAssert<PlayerDatabaseAssert, ApplicationContext> {

	private final Ref<Player> playerRef;

	public PlayerDatabaseAssert(ApplicationContext applicationContext, Ref<Player> playerRef) {
		super(applicationContext, PlayerDatabaseAssert.class);
		this.playerRef = playerRef;
	}

	public PlayerDatabaseAssert resources(Consumer<ResourcesAssert> consumer) {
		consumer.accept(new ResourcesAssert(actual, playerRef));
		return this;
	}

	public PlayerDatabaseAssert buildings(Consumer<BuildingsAssert> consumer) {
		consumer.accept(new BuildingsAssert(actual, playerRef));
		return this;
	}

}
