package com.example.demo.examplehelper;

import com.example.demo.domain.Building;
import com.example.demo.domain.Production;
import com.example.demo.domain.ProductionType;

import org.springframework.stereotype.Component;

@Component
public class ProductionHelper {

	public Production createRunningProduction(Building building, ProductionType productionType) {
		return new Production();
	}

}
