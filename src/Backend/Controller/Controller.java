package Backend.Controller;

import Backend.DB.DB;

public class Controller {
    public static void main(String[] args) {
        System.out.println(DB.existeUsuario("algo"));
    }
    
}
