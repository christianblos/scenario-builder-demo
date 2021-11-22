package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Alliance implements GameEntity {

	private UUID id;

	private String name;

	private List<UUID> memberIds = new ArrayList<>();

}
