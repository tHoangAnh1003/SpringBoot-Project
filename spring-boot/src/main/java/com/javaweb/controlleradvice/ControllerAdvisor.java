package com.javaweb.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.javaweb.DTO.ErrorResponseDTO;
import com.javaweb.customerexception.FieldRequiredException;

@ControllerAdvice
public class ControllerAdvisor {
	
	@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(
    		ArithmeticException ex, WebRequest request) {
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> detail = new ArrayList<>();
		detail.add("Khong chia het cho 0");
		errorResponseDTO.setDetail(detail);

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleArithmeticException(
    		FieldRequiredException ex, WebRequest request) {
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> detail = new ArrayList<>();
		detail.add("Ten hoac dia chi chua duoc gui ve!");
		errorResponseDTO.setDetail(detail);

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
