package com.example.demo.domain;

import lombok.Getter;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductionRepository implements GameRepository {

	@Getter
	private final Map<UUID, Production> storedProductions = new HashMap<>();

	public void save(Production production) {
		storedProductions.put(production.getId(), production);
	}

	public void remove(Production production) {
		storedProductions.remove(production.getId());
	}

	public Optional<Production> findByBuildingId(UUID buildingId) {
		return storedProductions.values().stream()
			.filter(production -> production.getBuildingId().equals(buildingId))
			.findFirst();
	}

	@Override
	public List<String> print() {
		return storedProductions.values().stream()
			.map(Production::toString)
			.collect(Collectors.toList());
	}

}
