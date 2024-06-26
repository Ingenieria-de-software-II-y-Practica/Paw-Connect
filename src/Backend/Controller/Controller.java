package Backend.Controller;

import java.util.Scanner;

import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;
import Backend.Models.Opciones;
import Backend.Models.Post;
import Backend.Models.Refugio;
import Backend.Models.Usuario;

public class Controller {
    public static void main(String[] args) throws UserDoesNotExistException {
        DB db = new DB();
        Usuario user = new Usuario();
        Refugio refugio = new Refugio();
        Post post = new Post();
        Opciones opcion = new Opciones();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("""
                    1. Iniciar Sesion
                    2. Registrarse
                    """);
            switch(scan.nextLine()){
                case "1":
                    String username = scan.nextLine();
                    String password = scan.nextLine();
                    user = user.login(username, password);
                    if(user != null){
                        if(user.acceso == false){
                            System.out.println("Bienvenido Usuario " + user.nombre + ".");
                        } else{
                            System.out.println("Bienvenido Refugio " + user.nombre + ".");
                        }
                    }
                    
            }
        }

    }
    
}
