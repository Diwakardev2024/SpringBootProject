package com.nexgenvision.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.nexgenvision.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExeptionsHandler {
	
	@ExceptionHandler(value= {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request)
	{
		ErrorMessage errorMessagge=new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessagge,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleUserServiceException(Exception ex, WebRequest request)
	{
		ErrorMessage errorMessagge=new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessagge,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {NullPointerException.class})
	public ResponseEntity<Object> handlenpeException(Exception ex, WebRequest request)
	{
		ErrorMessage errorMessagge=new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessagge,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
