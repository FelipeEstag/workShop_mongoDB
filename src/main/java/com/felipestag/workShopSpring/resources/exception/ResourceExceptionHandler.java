package com.felipestag.workShopSpring.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.felipestag.workShopSpring.services.exepction.ObjectNotFoundException;

@ControllerAdvice // Para tratar os possiveis erros nas requisicoes
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)// serve para quando houver essa excecao, chamar esse metodo e nao o metodo default
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException o, HttpServletRequest e) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "not found", o.getMessage(),
				e.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

}
