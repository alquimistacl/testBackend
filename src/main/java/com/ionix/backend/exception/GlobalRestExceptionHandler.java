package com.ionix.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class allows to handle the exceptions to be showed in a clear way
 * 
 * @author Luis San Martin
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({ GenericSqlException.class })
	public ResponseEntity<GenericError> handleNotFoundException(Exception ex) {

		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		GenericError genericError = buildErrorTrace(ex);

		return new ResponseEntity<>(genericError, httpStatus);
	}

	private GenericError buildErrorTrace(Exception ex) {
		GenericError error = new GenericError();
		error.setUserMessage(ex.getMessage());
		return error;
	}

}