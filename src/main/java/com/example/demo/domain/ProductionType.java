package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public enum ProductionType {

	COINS(Resource.COINS, Duration.ofHours(1), 100),
	FOOD(Resource.FOOD, Duration.ofSeconds(30), 10);

	private final Resource resource;
	private final Duration duration;
	private final int skipCost;

}
