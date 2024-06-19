package Backend.Controller;

import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;

public class Controller {
    public static void main(String[] args) throws UserDoesNotExistException {
        System.out.println(DB.existeUsuario("algo"));
    }
    
}
