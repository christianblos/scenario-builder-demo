package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Building implements GameEntity {

	private UUID id;

	private UUID playerId;

	private UUID cityId;

	private BuildingType type;

	private int level;

	private int x;

	private int y;

	private BuildingState state;

}
