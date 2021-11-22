package com.example.demo.domain;

import lombok.Getter;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TradeOfferRepository implements GameRepository {

	@Getter
	private final Map<UUID, TradeOffer> storedTradeOffers = new HashMap<>();

	public void save(TradeOffer tradeOffer) {
		storedTradeOffers.put(tradeOffer.getId(), tradeOffer);
	}

	@Override
	public List<String> print() {
		return storedTradeOffers.values().stream()
			.map(TradeOffer::toString)
			.collect(Collectors.toList());
	}

}
