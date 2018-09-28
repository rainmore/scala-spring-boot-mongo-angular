package au.com.wytn.centus.domains;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        this("Requested entity can't be found!");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

}
