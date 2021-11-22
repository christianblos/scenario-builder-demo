package com.example.demo.domain;

import lombok.Getter;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CityRepository implements GameRepository {

	@Getter
	private final Map<UUID, City> storedCities = new HashMap<>();

	public void save(City city) {
		storedCities.put(city.getId(), city);
	}

	@Override
	public List<String> print() {
		return storedCities.values().stream()
			.map(City::toString)
			.collect(Collectors.toList());
	}

}
