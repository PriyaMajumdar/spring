package com.microservice1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExpceptionController {
	@ExceptionHandler(value = NullFeild.class)
    public ResponseEntity<Object> exception(NullFeild exception) {
		return new ResponseEntity<>("Field is null", HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = PasswordNotMatch.class)
    public ResponseEntity<Object> exception2(PasswordNotMatch exception) {
		return new ResponseEntity<>("Password  is not match to the confim Password ", HttpStatus.RESET_CONTENT);
	}
	@ExceptionHandler(value = EmailFormat.class)
    public ResponseEntity<Object> exception3(EmailFormat exception) {
		return new ResponseEntity<>("Email format is not correct ", HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(value = SameCompanyName.class)
    public ResponseEntity<Object> exception4(SameCompanyName exception) {
		return new ResponseEntity<>("Company name is duplicate it must be unique ", HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = IdNotFound.class)
    public ResponseEntity<Object> exception5(IdNotFound exception) {
		return new ResponseEntity<>("Id is not Found ", HttpStatus.ALREADY_REPORTED);
	}


	@ExceptionHandler(value = SameEmailId.class)
    public ResponseEntity<Object> exception5(SameEmailId exception) {
		return new ResponseEntity<>("EmailId already exist , I must be unique ", HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(value = UnAuthorisedAccess.class)
    public ResponseEntity<Object> exception5(UnAuthorisedAccess exception) {
		return new ResponseEntity<>("May be username and passworn would be wrong ", HttpStatus.UNAUTHORIZED);
	}


}
