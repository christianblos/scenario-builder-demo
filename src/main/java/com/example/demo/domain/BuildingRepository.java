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
public class BuildingRepository implements GameRepository {

	@Getter
	private final Map<UUID, Building> storedBuildings = new HashMap<>();

	public void save(Building building) {
		storedBuildings.put(building.getId(), building);
	}

	public Optional<Building> findByPlayerIdAndBuildingId(UUID playerId, UUID buildingId) {
		return storedBuildings.values().stream()
			.filter(building -> building.getPlayerId().equals(playerId) && building.getId().equals(buildingId))
			.findFirst();
	}

	@Override
	public List<String> print() {
		return storedBuildings.values().stream()
			.map(Building::toString)
			.collect(Collectors.toList());
	}

}
