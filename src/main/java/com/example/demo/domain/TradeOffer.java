package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class TradeOffer implements GameEntity {

	private UUID id;

	private UUID playerId;

	private Resource resource;

	private int amount;

	private UUID acceptedPlayerId;

}
