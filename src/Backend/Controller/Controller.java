package Backend.Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
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
        if(DB.existeNombre(username)){
            if(DB.getUsuario(username) != null){
                user = DB.getUsuario(username);
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
    /**
     * Metodo para registrar un Usuario en la BD
     * @param nombre
     * @param contraseña
     * @param contacto
     * @return OK O Error: => Mensaje
     */
    public String registrarUsuario(String nombre, String contraseña, String contacto){
        if (!(DB.existeNombre(nombre))) {
            // Si no existe otro usuario con ese nombre
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setContraseña(contraseña);
            usuario.setNumero_contacto(contacto);
            usuario.setAcceso(false);
            try {
                return (DB.crearUsuario(usuario))? "OK" : "Error";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error: "+e.getMessage();
            }
        }else{
            return "Error: Usuario ya existe..";
        }
    }
    /**
     * Metodo para registrar un Refugio en la BD
     * @param nombre
     * @param contraseña
     * @param contacto
     * @param direccion
     * @return OK o Error: => Mensaje
     */
    public String registrarRefugio(String nombre, String contraseña, String contacto, String direccion) {
        if (!(DB.existeNombre(nombre))) {
            // Si no existe otro refugio con ese nombre
            Refugio refugio = new Refugio();
            refugio.setNombre(nombre);
            refugio.setContraseña(contraseña);
            refugio.setNumero_contacto(contacto);
            refugio.setAcceso(true);
            refugio.setDireccion(direccion);
            try {
                return (DB.crearRefugio(refugio))? "OK" : "Error";
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
    public Post publicarPost(String titulo, String raza, String descripcion, 
    boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado,
    String edad, String tamaño, String tipoMascosta, File foto) {
        Post post = new Post(titulo, raza, descripcion, new Opciones(vacunas, niños, otrasMascotas, desparacitado),edad,tamaño, tipoMascosta, foto);
        Post postGuardado = DB.publicarPost(post);
        if (postGuardado != null) {
            return postGuardado;
        }else{
            return null;
        }
    }
    /**
     * Metodo para eliminar un post de la BD
     * @param post Post ha eliminar
     * @return OK o Error => Mensaje
     */
    public String eliminarPost(int idPost) {
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
     * Metodo para modifcar un post de la BD
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
    public String modifcarPost(int idPost, String titulo, String raza, String descripcion, 
    boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado,
    String edad, String tamaño, String tipoMascosta, File foto) {
        try {
            if (DB.getPostId(idPost) != null) {
                // Si el Post existe dentro de la base de datos
                Post nuevo = new Post(idPost,titulo, raza, descripcion, new Opciones(vacunas, niños, otrasMascotas, desparacitado), edad, tamaño, tipoMascosta, foto);
                return (DB.modificarPost(nuevo))? "OK": "Error";
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
    public ArrayList<Post> filtrPosts(boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado) {
        Opciones filtro = new Opciones(vacunas, niños, otrasMascotas, desparacitado);
        ArrayList<Post> lista_filtro = DB.filtrarPost(filtro);
        if (lista_filtro != null) {
            return lista_filtro;
        }else{
            return null;
        }
    }
}
