package com.example.demo.scenario.builder;

import com.example.demo.domain.Alliance;
import com.example.demo.domain.AllianceRepository;
import com.example.demo.scenario.given.GivenAlliance;
import com.example.demo.scenario.given.GivenGameScenario;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A ScenarioBuilderPart that creates all {@link Alliance} entities configured in the {@link GivenGameScenario}.
 */
@Component
@RequiredArgsConstructor
public class AllianceBuilderPart implements ScenarioBuilderPart<GivenGameScenario> {

	private final AllianceRepository allianceRepository;

	@Override
	public int getOrder() {
		return BuilderPartOrder.ALLIANCE.ordinal();
	}

	@Override
	public void build(GivenGameScenario givenScenario) {
		givenScenario.getAlliances().forEach(this::createAlliance);
	}

	private void createAlliance(GivenAlliance givenAlliance) {
		var alliance = new Alliance();
		alliance.setId(UUID.randomUUID());
		alliance.setName(givenAlliance.getName());
		alliance.setMemberIds(givenAlliance.getMembers().stream()
			.map(member -> member.get().getId())
			.collect(Collectors.toList()));

		allianceRepository.save(alliance);

		givenAlliance.setEntity(alliance);
	}

}
