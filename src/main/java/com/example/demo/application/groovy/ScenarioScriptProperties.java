package com.example.demo.application.groovy;

import com.innogames.scenariobuilder.Ref;
import groovy.lang.GroovyObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a utility class to set or get properties that are used
 * inside the scenario groovy scripts.
 */
public class ScenarioScriptProperties {

	/**
	 * The `references` property is a map that contains all Ref objects
	 * created inside the groovy script via the namedRef() function.
	 */
	public static final String PROPERTY_REFERENCES = "references";

	public static void setInitialProperties(GroovyObject object) {
		object.setProperty(PROPERTY_REFERENCES, new HashMap<>());
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Ref<?>> getEntityReferences(GroovyObject object) {
		return (Map<String, Ref<?>>) object.getProperty(PROPERTY_REFERENCES);
	}

}
