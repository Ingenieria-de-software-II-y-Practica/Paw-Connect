package Backend.Controller;

import java.io.File;
import java.util.ArrayList;

import Backend.DB.DB;
import Backend.Models.Opciones;
import Backend.Models.Post;
import Backend.Models.Refugio;
import Backend.Models.Usuario;
import Frontend.LoginAndSignUp.src.loginandsignup.Login;

public class Controller {
    public static void main(String[] args) {
        DB.connectToDatabase();
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        
    }
    /**
     * Metodo para que la vista se loguee.
     * @param username Nombre de usuario que nos pasa la vista
     * @param password Contraseña que nos pasa la vista
     * @return Devuelve un refugio, un adoptante o nulo.
     */
    public static Refugio loginRefugio(String username, String password){
        if(DB.existeRefugio(username)){
            Refugio ref = new Refugio();
            try{
                ref = DB.getRefugio(username);
                ref = ref.login(username, password);
                return ref;
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    /**
     * Metodo para que la vista se loguee.
     * @param username Nombre de usuario que nos pasa la vista
     * @param password Contraseña que nos pasa la vista
     * @return Devuelve un refugio, un adoptante o nulo.
     */
    public static Usuario loginUsuario(String username, String password){
        if(DB.existeUsuario(username)){
            Usuario user = new Usuario();
            try{
                    user = DB.getUsuario(username);
                    Usuario user2 = user.login(username, password);
                    return user2;
                }
                
            catch(Exception e){
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
    public static Post publicarPost(String titulo, String descripcion, 
    boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado,
    String edad, String tamaño, String tipoMascosta, File foto, Refugio refugio) {
        Post post = new Post();
        try{
            return post.guardarPost(titulo, descripcion, tamaño, new Opciones(vacunas, niños, otrasMascotas, desparacitado), edad, tipoMascosta, foto, refugio);
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
       
        if (DB.getPost(idPost) != null) {
            // Si encuentra el post dentro de la BD
            return (DB.eliminarPost(idPost))? "OK" : "Error";
        }else{
            return "Error: Post no encontrado";
        }
    }
    /**
     * Metodo para modificar un post de la BD
     * @param idPost
     * @param titulo
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
    public static String modificarPost(int idPost, String titulo, String descripcion, 
    boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado,
    String edad, String tamaño, String tipoMascosta) {
            if (DB.getPost(idPost) != null) {
                // Si el Post existe dentro de la base de datos
                Post viejo = DB.getPost(idPost);
                Post nuevo = new Post(titulo, descripcion, new Opciones(vacunas, niños, otrasMascotas, desparacitado), edad, tamaño, tipoMascosta);
                return (viejo.editarPost(nuevo));
            }else{
                return "Error: No existe el post";
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
    public static ArrayList<Post> getRefugioPosts(int id){
        return DB.allPostRefugio(id);
    }
    public static ArrayList<Post> getAllPosts(){
        return DB.getAllPosts();
    }
    public static Refugio getRefugiobyPost(int id){
        int id2 = DB.getRefugioID(id);
        return DB.getRefugio(id2);
    }
}
