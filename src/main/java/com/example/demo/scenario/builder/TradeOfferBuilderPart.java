package com.example.demo.scenario.builder;

import com.example.demo.domain.TradeOffer;
import com.example.demo.domain.TradeOfferRepository;
import com.example.demo.scenario.given.GivenGameScenario;
import com.example.demo.scenario.given.GivenPlayer;
import com.example.demo.scenario.given.GivenTradeOffer;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * A ScenarioBuilderPart that creates all {@link TradeOffer} entities configured in the {@link GivenGameScenario}.
 */
@Component
@RequiredArgsConstructor
public class TradeOfferBuilderPart implements ScenarioBuilderPart<GivenGameScenario> {

	private final TradeOfferRepository tradeOfferRepository;

	@Override
	public int getOrder() {
		return BuilderPartOrder.TRADE_OFFER.ordinal();
	}

	@Override
	public void build(GivenGameScenario givenScenario) {
		givenScenario.getPlayers().forEach(givenPlayer ->
			givenPlayer.getTraceOffers().forEach(givenTradeOffer ->
				createTradeOffer(givenPlayer, givenTradeOffer)
			)
		);
	}

	private void createTradeOffer(GivenPlayer givenPlayer, GivenTradeOffer givenTradeOffer) {
		var offer = new TradeOffer();
		offer.setId(UUID.randomUUID());
		offer.setPlayerId(givenPlayer.getEntity().getId());
		offer.setResource(givenTradeOffer.getResource());
		offer.setAmount(givenTradeOffer.getAmount());

		if (givenTradeOffer.getAcceptedPlayerRef() != null) {
			offer.setAcceptedPlayerId(givenTradeOffer.getAcceptedPlayerRef().get().getId());
		}

		tradeOfferRepository.save(offer);

		givenTradeOffer.setEntity(offer);
	}

}
