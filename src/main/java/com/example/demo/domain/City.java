package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class City implements GameEntity {

	private UUID id;

	private UUID playerId;

	private CityType type;

}
