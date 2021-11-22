package com.example.demo.application.groovy;

import com.innogames.scenariobuilder.EntityRefHolder;
import com.innogames.scenariobuilder.Ref;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Contains a whitelist of classes that are allowed to be used in the scenario groovy scripts.
 */
public class GroovySandboxClassWhitelist {

	private static final String SCENARIO_PACKAGE = "com.example.demo.scenario";
	private static final String DOMAIN_PACKAGE = "com.example.demo.domain";

	/**
	 * Mapping of "Class name" -> "Alias".
	 * If an alias is defined, the class will be imported automatically in groovy scripts
	 * (See {@link GroovySandboxImportCustomizer}).
	 */
	@Getter
	private static final Map<String, String> whitelist = new HashMap<>();

	static {
		addWithAlias(Ref.class);

		findGivenClasses().forEach(clazz -> addWithAlias(clazz));
		findReferencedEntityClasses().forEach(clazz -> addWithAlias(clazz));
		findEnums().forEach(clazz -> addWithAlias(clazz));
	}

	private static void addWithAlias(Class<?> clazz) {
		whitelist.put(clazz.getName(), clazz.getSimpleName());
	}

	/**
	 * Find scenario classes that are prefixed with "Given".
	 */
	private static List<Class<?>> findGivenClasses() {
		try (ScanResult scanResult = new ClassGraph().acceptPackages(SCENARIO_PACKAGE).scan()) {
			return scanResult.getAllClasses().stream()
				.filter(classInfo -> classInfo.getSimpleName().startsWith("Given"))
				.map(ClassInfo::loadClass)
				.collect(Collectors.toList());
		}
	}

	/**
	 * Find classes that are referenced by all EntityRefHolder classes.
	 */
	private static List<Class<?>> findReferencedEntityClasses() {
		try (ScanResult scanResult = new ClassGraph().acceptPackages(SCENARIO_PACKAGE).scan()) {
			return scanResult.getAllClasses().stream()
				.filter(classInfo -> classInfo.extendsSuperclass(EntityRefHolder.class.getName()))
				.map(classInfo -> {
					var genericType = (ParameterizedType) classInfo.loadClass().getGenericSuperclass();
					Type actualTypeArgument = genericType.getActualTypeArguments()[1];
					if (actualTypeArgument instanceof ParameterizedType) {
						return (Class<?>) ((ParameterizedType) actualTypeArgument).getRawType();
					}
					return (Class<?>) actualTypeArgument;
				})
				.collect(Collectors.toList());
		}
	}

	private static List<Class<?>> findEnums() {
		try (ScanResult scanResult = new ClassGraph()
			.acceptPackages(SCENARIO_PACKAGE)
			.acceptPackages(DOMAIN_PACKAGE)
			.scan()
		) {
			return scanResult.getAllClasses().stream()
				.filter(ClassInfo::isEnum)
				.map(ClassInfo::loadClass)
				.collect(Collectors.toList());
		}
	}

}
