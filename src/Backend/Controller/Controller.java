package Backend.Controller;

import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;

public class Controller {
    public static void main(String[] args) throws UserDoesNotExistException {
        DB db = new DB();
        System.out.println(DB.existeUsuario("algo"));
    }
    
}
