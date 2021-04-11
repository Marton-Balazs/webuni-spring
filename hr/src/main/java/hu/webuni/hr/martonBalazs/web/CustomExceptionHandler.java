package hu.webuni.hr.martonBalazs.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import hu.webuni.hr.martonBalazs.service.NonUniqueIDException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(NonUniqueIDException.class)
	public ResponseEntity<MyError> handleNonUniqueId(NonUniqueIDException e, WebRequest req) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyError(e.getMessage(), 1002));
	}

}
