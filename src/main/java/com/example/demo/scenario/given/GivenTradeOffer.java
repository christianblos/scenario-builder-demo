package com.example.demo.scenario.given;

import com.example.demo.domain.Player;
import com.example.demo.domain.Resource;
import com.example.demo.domain.TradeOffer;
import com.example.demo.scenario.builder.TradeOfferBuilderPart;
import com.innogames.scenariobuilder.EntityRefHolder;
import com.innogames.scenariobuilder.Ref;

import lombok.Getter;

/**
 * Contains configuration of an TradeOffer in a {@link GivenGameScenario}.
 * It holds the related {@link TradeOffer} entity that is creates by the {@link TradeOfferBuilderPart}.
 */
@Getter
public class GivenTradeOffer extends EntityRefHolder<GivenTradeOffer, TradeOffer> {

	private Resource resource;

	private int amount;

	private Ref<Player> acceptedPlayerRef;

	public GivenTradeOffer withResource(Resource resource) {
		this.resource = resource;
		return this;
	}

	public GivenTradeOffer withAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public GivenTradeOffer withAcceptedBy(Ref<Player> playerRef) {
		this.acceptedPlayerRef = playerRef;
		return this;
	}

}
