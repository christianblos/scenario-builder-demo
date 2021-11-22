package com.example.demo.application.groovy;

import com.innogames.scenariobuilder.Ref;
import groovy.lang.Script;

/**
 * This class provides methods that are available in the scenario groovy scripts.
 */
public abstract class ScenarioScript extends Script {

	/**
	 * Get or create a Ref object by name.
	 */
	public Ref<?> namedRef(String name) {
		var references = ScenarioScriptProperties.getEntityReferences(this);

		if (!references.containsKey(name)) {
			references.put(name, new Ref<>());
		}

		return references.get(name);
	}

}
