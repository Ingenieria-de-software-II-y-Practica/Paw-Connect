package Backend.Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Backend.DB.DB;
import Backend.Models.Opciones;
import Backend.Models.Post;
import Backend.Models.Refugio;
import Backend.Models.Usuario;

public class Controller {
    /**
     * Metodo para que la vista se loguee.
     * @param username Nombre de usuario que nos pasa la vista
     * @param password Contraseña que nos pasa la vista
     * @return Devuelve un refugio, un adoptante o nulo.
     */
    public static Usuario loginUsuario(String username, String password){
        if(DB.existeNombre(username)){
            Usuario user;
            try{
                if(DB.getUsuario(username) != null){
                    user = DB.getUsuario(username);
                } else{
                    user = DB.getRefugio(username);
                }
                user = user.login(username, password);
                return user;
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    /**
     * Metodo para el boton de registrar de la Vista.
     * @param nombre Nombre de usuario proporcionado por la vista
     * @param contraseña Contraseña proporcionada por la vista
     * @param contacto Numero de contacto
     * @return OK O Error: => Mensaje
     */
    public static String registrarUsuario(String nombre, String contraseña, String contacto){
        if (!(DB.existeUsuario(nombre))) {
            // Si no existe otro usuario con ese nombre
            Usuario usuario = new Usuario();
            try {
                // True -> "OK" | False -> "Error"
                return (usuario.registrarse(nombre, contraseña, contacto))? "OK" : "Error";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: "+ e.getMessage();
            }
        }else{
            return "Error: Usuario ya existe..";
        }
    }
    /**
     * Metodo para registrar un refugio por la vista.
     * @param nombre Nombre de usuario
     * @param contraseña Contraseña
     * @param contacto Numero de contacto
     * @param direccion Direccion
     * @return OK o Error: => Mensaje
     */
    public static String registrarRefugio(String nombre, String contraseña, String contacto, String direccion){
        if (!(DB.existeRefugio(nombre))) {
            // Si no existe otro refugio con ese nombre
            Refugio refugio = new Refugio();
            try {
                return (refugio.registrarse(nombre, contraseña, contacto, direccion)) ? "OK" : "Error";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: "+e.getMessage();
            }
        }else{
            return "Error: El refugio ya existe....";
        }
    }
    /**
     * Metodo para publicar un Post en la BD
     * @param titulo
     * @param raza
     * @param descripcion
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparacitado
     * @param edad
     * @param tamaño
     * @param tipoMascosta
     * @param foto
     * @return Post recientemente publicado
     */
    public static Post publicarPost(String titulo, String raza, String descripcion, 
    boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado,
    String edad, String tamaño, String tipoMascosta, File foto) {
        Post post = new Post();
        try{
            return post.guardarPost(titulo, descripcion, raza, tamaño, new Opciones(vacunas, niños, otrasMascotas, desparacitado), edad, tipoMascosta, foto);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo para eliminar un post de la BD
     * @param post Post ha eliminar
     * @return OK o Error => Mensaje
     */
    public static String eliminarPost(int idPost) {
       try {
        if (DB.getPostId(idPost) != null) {
            // Si encuentra el post dentro de la BD
            return (DB.eliminarPost(idPost))? "OK" : "Error";
        }else{
            return "Error: Post no encontrado";
        }
       } catch (SQLException e) {
            e.printStackTrace();
            return "Error: "+e.getMessage();
       }
    }
    /**
     * Metodo para modificar un post de la BD
     * @param idPost
     * @param titulo
     * @param raza
     * @param descripcion
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparacitado
     * @param edad
     * @param tamaño
     * @param tipoMascosta
     * @param foto
     * @return OK o Error: => Mensaje
     */
    public static String modificarPost(int idPost, String titulo, String raza, String descripcion, 
    boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado,
    String edad, String tamaño, String tipoMascosta) {
        try {
            if (DB.getPostId(idPost) != null) {
                // Si el Post existe dentro de la base de datos
                Post viejo = DB.getPostId(idPost);
                Post nuevo = new Post(titulo, raza, descripcion, new Opciones(vacunas, niños, otrasMascotas, desparacitado), edad, tamaño, tipoMascosta);
                return (viejo.editarPost(nuevo));
            }else{
                return "Error: No existe el post";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
    }
    /**
     * Metodo para filtrar por opciones en la BD
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparacitado
     * @return ArrayList con todos los posts
     */
    public static ArrayList<Post> filtroPosts(boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado, String tipoMascota, String tamaño) {
        Opciones filtro = new Opciones(vacunas, niños, otrasMascotas, desparacitado);
        ArrayList<Post> lista_filtro = DB.filtrarPost(filtro, tipoMascota, tamaño);
        return lista_filtro;
    }
}
