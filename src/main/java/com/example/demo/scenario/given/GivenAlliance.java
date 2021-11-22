package com.example.demo.scenario.given;

import com.example.demo.domain.Alliance;
import com.example.demo.domain.Player;
import com.example.demo.scenario.builder.AllianceBuilderPart;
import com.innogames.scenariobuilder.EntityRefHolder;
import com.innogames.scenariobuilder.Ref;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains configuration of an Alliance in a {@link GivenGameScenario}.
 * It holds the related {@link Alliance} entity that is creates by the {@link AllianceBuilderPart}.
 */
@Getter
public class GivenAlliance extends EntityRefHolder<GivenAlliance, Alliance> {

	private String name = "Test Alliance";

	private final List<Ref<Player>> members = new ArrayList<>();

	public GivenAlliance withName(String name) {
		this.name = name;
		return this;
	}

	public GivenAlliance withMember(Ref<Player> playerRef) {
		this.members.add(playerRef);
		return this;
	}

}
