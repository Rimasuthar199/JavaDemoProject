package userregistration.userregistration.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ExceptionMessage {

	 Integer code;
	 String message;
}
