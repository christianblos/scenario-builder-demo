package com.example.demo.examplehelper;

import com.example.demo.domain.City;
import com.example.demo.domain.CityType;
import com.example.demo.domain.Player;

import org.springframework.stereotype.Component;

@Component
public class CityHelper {

	public City createCity(Player player, CityType type) {
		return new City();
	}

}
