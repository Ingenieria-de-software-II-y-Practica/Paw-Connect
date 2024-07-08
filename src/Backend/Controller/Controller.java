package Backend.Controller;

import java.util.Scanner;

import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;
import Backend.Models.Opciones;
import Backend.Models.Post;
import Backend.Models.Refugio;
import Backend.Models.Usuario;

public class Controller {
    DB db = new DB();
    Usuario user = new Usuario();
    Refugio refugio = new Refugio();
    Post post = new Post();
    Opciones opcion = new Opciones();
    /**
     * Metodo para que la vista se loguee.
     * @param username Nombre de usuario que nos pasa la vista
     * @param password Contraseña que nos pasa la vista
     * @return Devuelve un refugio, un adoptante o nulo.
     * @throws UserDoesNotExistException Por si no existe el usuario que se busca.
     */
    public Usuario loginUsuario(String username, String password) throws UserDoesNotExistException{
        if(db.existeNombre(username)){
            if(db.getUsuario(username) != null){
                user = db.getUsuario(username);
                user = user.login(username,password);
                return user;
            } else{
                refugio = db.getRefugio(username);
                refugio = refugio.login(username, password);
                return refugio;
            }
        }
        return null;
    }
}
