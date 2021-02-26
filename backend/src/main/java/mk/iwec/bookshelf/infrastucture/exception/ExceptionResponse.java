package mk.iwec.bookshelf.infrastucture.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String errorMessage;
    private Timestamp timestamp;
}
