package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Production implements GameEntity {

	private UUID id;

	private UUID buildingId;

	private ProductionType type;

	private int amount;

	private Instant startedAt;

	private Instant finishAt;

}
