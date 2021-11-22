package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Player implements GameEntity {

	private UUID id;

	private String name;

}
