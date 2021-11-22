package com.example.demo.scenario;

import com.example.demo.domain.Player;
import com.innogames.scenariobuilder.Ref;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Sends requests to the backend
 */
public class GameClient {

	private final Ref<Player> playerRef;
	private final TestRestTemplate restTemplate;

	public GameClient(Ref<Player> playerRef, ApplicationContext applicationContext) {
		this.playerRef = playerRef;
		this.restTemplate = applicationContext.getBean(TestRestTemplate.class);
	}

	public ResponseEntity<String> sendRequest(String url, Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));

		// JUST FOR DEMO PURPOSES!
		// usually, we would authenticate the player here and pass a proper auth token to the request
		headers.set("X-PLAYER", playerRef.get().getId().toString());

		HttpEntity<?> request = body == null ? new HttpEntity<>(headers) : new HttpEntity<>(body, headers);
		return restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	}

}
