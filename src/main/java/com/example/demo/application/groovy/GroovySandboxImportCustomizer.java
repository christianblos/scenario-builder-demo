package com.example.demo.application.groovy;

import org.codehaus.groovy.control.customizers.ImportCustomizer;

/**
 * Adds default imports to the groovy shell. This allows us to omit the
 * import statements in groovy scripts for commonly used classes.
 */
public class GroovySandboxImportCustomizer extends ImportCustomizer {

	public GroovySandboxImportCustomizer() {
		super();

		// import whitelisted classes that have an alias defined
		GroovySandboxClassWhitelist.getWhitelist().entrySet().stream()
			.filter(entry -> !entry.getValue().isBlank())
			.forEach(entry -> addImport(entry.getValue(), entry.getKey()));
	}

}
