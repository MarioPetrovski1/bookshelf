package mk.iwec.bookshelf.infrastucture.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception e, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ExceptionResponse exceptionResponse = defineExceptionResponse(httpStatus, e.getMessage());
		return new ResponseEntity<>(exceptionResponse, httpStatus);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(ResourceNotFoundException e,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ExceptionResponse exceptionResponse = defineExceptionResponse(httpStatus, e.getMessage());
		return new ResponseEntity<>(exceptionResponse, httpStatus);
	}

	private ExceptionResponse defineExceptionResponse(HttpStatus httpStatus, String errorMessage) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		
		exceptionResponse.setHttpStatus(httpStatus);
		exceptionResponse.setErrorMessage(errorMessage);
		exceptionResponse.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

		return exceptionResponse;
	}
}