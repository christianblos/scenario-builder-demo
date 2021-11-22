package com.example.demo;

import com.example.demo.domain.Player;
import com.example.demo.scenario.GameClient;
import com.example.demo.scenario.GameScenarioExtension;
import com.example.demo.scenario.assertion.PlayerDatabaseAssert;
import com.example.demo.scenario.assertion.ResponseAssert;
import com.example.demo.scenario.given.GivenGameScenario;
import com.innogames.scenariobuilder.Ref;
import com.innogames.scenariobuilder.ScenarioBuilder;
import com.innogames.scenariobuilder.junit5.ScenarioAware;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.Consumer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ExtendWith(GameScenarioExtension.class)
abstract class BaseIntegrationTest implements ScenarioAware<GivenGameScenario> {

	@Autowired
	private ApplicationContext applicationContext;

	protected ScenarioBuilder<GivenGameScenario> scenarioBuilder;

	@Override
	public void setScenarioBuilder(ScenarioBuilder<GivenGameScenario> builder) {
		this.scenarioBuilder = builder;
	}

	protected GameClient gameClientOf(Ref<Player> playerRef) {
		return new GameClient(playerRef, applicationContext);
	}

	protected <R> void assertThatResponse(ResponseEntity<R> response, Consumer<ResponseAssert<R>> consumer) {
		var responseAssert = new ResponseAssert<>(response);
		consumer.accept(responseAssert);
	}

	protected void assertThatDatabaseOf(Ref<Player> playerRef, Consumer<PlayerDatabaseAssert> consumer) {
		var dbAssert = new PlayerDatabaseAssert(applicationContext, playerRef);
		consumer.accept(dbAssert);
	}

}
