package com.example.demo.examplehelper;

import com.example.demo.domain.Building;
import com.example.demo.domain.BuildingType;
import com.example.demo.domain.City;

import org.springframework.stereotype.Component;

@Component
public class BuildingHelper {

	public Building placeBuilding(City city, BuildingType buildingType, int level, int x, int y) {
		return new Building();
	}

}
