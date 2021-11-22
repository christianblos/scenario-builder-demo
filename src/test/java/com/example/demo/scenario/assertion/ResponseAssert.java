package com.example.demo.scenario.assertion;

import org.assertj.core.api.AbstractAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseAssert<R> extends AbstractAssert<ResponseAssert<R>, ResponseEntity<R>> {

	public ResponseAssert(ResponseEntity<R> response) {
		super(response, ResponseAssert.class);
	}

	public void isOk() {
		isNotNull();
		Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

}
