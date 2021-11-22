package com.example.demo.scenario.given;

import com.example.demo.domain.Production;
import com.example.demo.domain.ProductionType;
import com.example.demo.scenario.builder.ProductionBuilderPart;
import com.innogames.scenariobuilder.EntityRefHolder;

import lombok.Getter;

import java.time.Instant;

/**
 * Contains configuration of a Production in a {@link GivenGameScenario}.
 * It holds the related {@link Production} entity that is creates by the {@link ProductionBuilderPart}.
 */
@Getter
public class GivenProduction extends EntityRefHolder<GivenProduction, Production> {

	private final ProductionType type;

	private int amount = 1;

	private Instant finishAt;

	public GivenProduction(ProductionType type) {
		this.type = type;
	}

	public GivenProduction withAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public GivenProduction withFinishAt(Instant finishAt) {
		this.finishAt = finishAt;
		return this;
	}

	public GivenProduction withFinishNow() {
		this.finishAt = Instant.now();
		return this;
	}


}
