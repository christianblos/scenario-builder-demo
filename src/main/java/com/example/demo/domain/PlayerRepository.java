package com.example.demo.domain;

import lombok.Getter;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PlayerRepository implements GameRepository {

	@Getter
	private final Map<UUID, Player> storedPlayers = new HashMap<>();

	public void save(Player player) {
		storedPlayers.put(player.getId(), player);
	}

	@Override
	public List<String> print() {
		return storedPlayers.values().stream()
			.map(Player::toString)
			.collect(Collectors.toList());
	}

}
