package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AllianceRepository implements GameRepository {

	private final Map<UUID, Alliance> storedAlliances = new HashMap<>();

	public void save(Alliance alliance) {
		storedAlliances.put(alliance.getId(), alliance);
	}

	@Override
	public List<String> print() {
		return storedAlliances.values().stream()
			.map(Alliance::toString)
			.collect(Collectors.toList());
	}

}
