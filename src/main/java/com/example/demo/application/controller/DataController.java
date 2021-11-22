package com.example.demo.application.controller;

import com.example.demo.domain.AllianceRepository;
import com.example.demo.domain.BuildingRepository;
import com.example.demo.domain.CityRepository;
import com.example.demo.domain.GameRepository;
import com.example.demo.domain.PlayerRepository;
import com.example.demo.domain.ResourceRepository;
import com.example.demo.domain.TradeOfferRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DataController {

	private final AllianceRepository allianceRepository;
	private final BuildingRepository buildingRepository;
	private final CityRepository cityRepository;
	private final PlayerRepository playerRepository;
	private final ResourceRepository resourceRepository;
	private final TradeOfferRepository tradeOfferRepository;

	@GetMapping("/data")
	public String printData() {
		List<String> lines = new ArrayList<>();

		Map<String, GameRepository> repositories = new LinkedHashMap<>();
		repositories.put("Players", playerRepository);
		repositories.put("Resources", resourceRepository);
		repositories.put("Cities", cityRepository);
		repositories.put("Buildings", buildingRepository);
		repositories.put("Trade Offers", tradeOfferRepository);
		repositories.put("Alliances", allianceRepository);

		repositories.forEach((name, repo) -> {
			lines.add("<b>" + name + "</b>");
			lines.addAll(repo.print());
			lines.add("");
		});

		return String.join("<br/>", lines);
	}

}
