package Backend.Models;

import java.nio.file.Path;
import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;
public class Post {
    /*
     *  Atributos 
     */
    int id;
    String titulo, raza, descripcion, verificacion, tamaño, tipoMascota;
    int edad;
    Path foto;
    
    /*
     * Metodos
     */
    public Post(){

    }
    public Post(int id, String titulo, String raza, String descripcion, String verificacion,
            int edad, String tamaño, String tipoMascota, Path foto) {
        this.id = id;
        this.titulo = titulo;
        this.raza = raza;
        this.descripcion = descripcion;
        this.verificacion = verificacion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.foto = foto;
    }
    public void editar(String titulo, String descripcion, String raza, String tamaño, String verificacion, int edad, String tipoMascota){
        this.titulo = titulo;
        this.raza = raza;
        this.descripcion = descripcion;
        this.verificacion = verificacion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
    }
    /**
     * Metodo para eliminar un Post de la BD
     * @param PostId Id del Post
     * @return Confirmacion: OK ==> true, Error => mensaje
     */
    public String eliminarPost(int PostId){
        boolean confirmacion = DB.eliminarPost(PostId);
        if (confirmacion) {
            return "OK";
        }else{
            return "Error: al eliminar";
        }
    }
    /**
     * Metodo para guardar un Post en la DB
     * @param post Post que se va a guardar
     * @return Confirmacion: OK => true, Error => mensaje
     */
    public String guardarPost(Post post) {
        if (!(DB.existePost(post))) {
            boolean confirmacion = DB.publicarPost(post.getId());
            return (confirmacion)? "OK" : "Error: al publicar";
        }else{
            return "Error: Post ya existe";
        }
    }
    /**
     * Metodo para editar un Post en la DB
     * @param post Post a editar
     * @return Post editar, caso no existir: null
     */
    public Post editarPost(Post post) {
        if (DB.existePost(post.getId())) {
            Post postUpdate = DB.modificarPost(post);
            return postUpdate;
        }else{
            return null;
        }
    }
    /**
     * Metodo para filtrar Posts por opciones
     * @param filtro Opciones para filtrar
     * @return Array de Posts con el filtro o null si no se encuentra
     */
    public Post[] filtrarPosts(Opciones filtro) {
        Post[] filtro = DB.filtrarPost(filtro);
        if (filtro != null) {
            return filtro;
        }else{
            return null;
        }
    }
    /*
     * Getters and Setters
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String gettitulo() {
        return titulo;
    }
    public void settitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getVerificacion() {
        return verificacion;
    }
    public void setVerificacion(String verificacion) {
        this.verificacion = verificacion;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getTamaño() {
        return tamaño;
    }
    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
    public String getTipoMascota() {
        return tipoMascota;
    }
    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }
    public Path getFoto() {
        return foto;
    }
    public void setFoto(Path foto) {
        this.foto = foto;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
     
}
