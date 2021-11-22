package com.example.demo.scenario.assertion;

import com.example.demo.domain.Player;
import com.example.demo.domain.Resource;
import com.example.demo.domain.ResourceRepository;
import com.innogames.scenariobuilder.Ref;

import org.assertj.core.api.AbstractAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.ApplicationContext;

public class ResourcesAssert extends AbstractAssert<ResourcesAssert, ApplicationContext> {

	private final Ref<Player> playerRef;
	private final ResourceRepository resourceRepository;

	public ResourcesAssert(ApplicationContext applicationContext, Ref<Player> playerRef) {
		super(applicationContext, ResourcesAssert.class);
		this.playerRef = playerRef;
		this.resourceRepository = applicationContext.getBean(ResourceRepository.class);
	}

	public ResourcesAssert hasAmount(Resource resource, int amount) {
		Assertions.assertEquals(amount, resourceRepository.getAmount(playerRef.get().getId(), resource));
		return this;
	}

}
