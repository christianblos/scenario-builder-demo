package com.example.demo.examplehelper;

import com.example.demo.domain.Player;

import org.springframework.stereotype.Component;

@Component
public class PlayerHelper {

	public Player createPlayer() {
		return new Player();
	}

}
