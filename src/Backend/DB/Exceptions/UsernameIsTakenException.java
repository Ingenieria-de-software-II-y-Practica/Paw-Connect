package Backend.DB.Exceptions;

public class UsernameIsTakenException extends Exception {
    public UsernameIsTakenException (String message) {
        super(message);
    }
}
