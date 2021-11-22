package com.example.demo.application.controller;

import com.example.demo.application.AuthService;
import com.example.demo.domain.Building;
import com.example.demo.domain.BuildingRepository;
import com.example.demo.domain.BuildingState;
import com.example.demo.domain.Production;
import com.example.demo.domain.ProductionRepository;
import com.example.demo.domain.ProductionType;
import com.example.demo.domain.Resource;
import com.example.demo.domain.ResourceRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductionController {

	private final AuthService authService;
	private final BuildingRepository buildingRepository;
	private final ProductionRepository productionRepository;
	private final ResourceRepository resourceRepository;

	@PostMapping("/production/skip")
	public void skipProduction(HttpServletRequest request, @RequestBody SkipProductionRequest body) {
		UUID playerId = authService.getAuthenticatedPlayerId(request);

		Building building = buildingRepository.findByPlayerIdAndBuildingId(playerId, body.getBuildingId())
			.orElseThrow(() -> new RuntimeException("Building not found"));

		Production production = productionRepository.findByBuildingId(building.getId())
			.orElseThrow(() -> new RuntimeException("Production not found"));

		ProductionType productionType = production.getType();
		resourceRepository.add(playerId, productionType.getResource(), production.getAmount());
		resourceRepository.subtract(playerId, Resource.GEMS, productionType.getSkipCost());

		productionRepository.remove(production);

		building.setState(BuildingState.IDLE);
		buildingRepository.save(building);
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class SkipProductionRequest {

		private UUID buildingId;

	}

}
