package Backend.Models;

import java.io.File;
import java.nio.file.Path;
import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;
public class Post {
    /*
     *  Atributos 
     */
    int id;
    String titulo, raza, descripcion, tamaño, tipoMascota, edad;
    Opciones verificacion;
    File foto;
    
    /*
     * Metodos
     */
    public Post(){
    }
    public Post(String titulo, String raza, String descripcion, Opciones verificacion,
                String edad, String tamaño, String tipoMascota, File foto){
        this.titulo = titulo;
        this.raza = raza;
        this.descripcion = descripcion;
        this.verificacion = verificacion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.foto = foto;
    }
    public Post(int id, String titulo, String raza, String descripcion, Opciones verificacion,
            String edad, String tamaño, String tipoMascota, File foto) {
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
    public void asignar(String titulo, String descripcion, String raza, String tamaño, Opciones verificacion, String edad, String tipoMascota){
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
    public String guardarPost(String titulo, String descripcion, String raza, String tamaño, Opciones verificacion, String edad, String tipoMascota) {
        asignar(titulo, descripcion, raza, tamaño, verificacion, edad, tipoMascota);
        boolean confirmacion = DB.publicarPost(this);
        return (confirmacion)? "OK" : "Error: al publicar";
    }
    /**
     * Metodo para editar un Post en la DB
     * @param post Post a editar
     * @return Post editar, caso no existir: null
     */
    public Post editarPost(String titulo, String descripcion, String raza, String tamaño, Opciones verificacion, String edad, String tipoMascota) {
        if (DB.existePost(id)) {
            asignar(titulo, descripcion, raza, tamaño, verificacion, edad, tipoMascota);
            Post postUpdate = DB.modificarPost(this);
            return postUpdate;
        }
        
        return null;
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
    public Opciones getVerificacion() {
        return verificacion;
    }
    public void setVerificacion(Opciones verificacion) {
        this.verificacion = verificacion;
    }
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
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
    public File getFoto() {
        return foto;
    }
    public void setFoto(File foto) {
        this.foto = foto;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
     
}
