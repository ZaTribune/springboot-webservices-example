package zatribune.spring.example.webservices.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id) {
        super("Could not find Entity with id " + id);
    }
}