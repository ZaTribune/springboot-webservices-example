package zatribune.spring.example.webservices.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zatribune.spring.example.webservices.Exceptions.NotFoundException;

@ControllerAdvice
public class RestControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        //using [ResponseEntity<> class] = using [@ResponseStatus+@ResponseBody]
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }



}
