package com.example.demo.domain;

import lombok.Getter;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ResourceRepository implements GameRepository {

	@Getter
	private final Map<UUID, Map<Resource, Integer>> storedResources = new HashMap<>();

	public int getAmount(UUID playerId, Resource resource) {
		Map<Resource, Integer> playerResources = storedResources.get(playerId);
		if (playerResources == null) {
			return 0;
		}
		return playerResources.getOrDefault(resource, 0);
	}

	public void set(UUID playerId, Resource resource, int amount) {
		storedResources.computeIfAbsent(playerId, pid -> new HashMap<>());
		storedResources.get(playerId).put(resource, amount);
	}

	public void add(UUID playerId, Resource resource, int amount) {
		storedResources.computeIfAbsent(playerId, pid -> new HashMap<>());
		Map<Resource, Integer> playerResources = storedResources.get(playerId);
		playerResources.compute(resource, (key, val) -> val == null ? amount : val + amount);
	}

	public void subtract(UUID playerId, Resource resource, int amount) {
		storedResources.computeIfAbsent(playerId, pid -> new HashMap<>());
		Map<Resource, Integer> playerResources = storedResources.get(playerId);
		playerResources.compute(resource, (key, val) -> val == null ? 0 : Math.max(0, val - amount));
	}

	@Override
	public List<String> print() {
		return storedResources.entrySet().stream()
			.flatMap(player -> player.getValue().entrySet().stream()
				.map(resource -> String.format("%s: %s x%d", player.getKey(), resource.getKey(), resource.getValue())))
			.collect(Collectors.toList());
	}

}
