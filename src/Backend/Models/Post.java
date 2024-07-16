package Backend.Models;

import java.io.File;
import Backend.DB.DB;
public class Post {
    /*
     *  Atributos 
     */
    int id;
    String titulo, descripcion, tamaño, tipoMascota, edad;
    Opciones verificacion;
    File foto;
    
    /*
     * Metodos
     */
    public Post(){
    }
    public Post(String titulo, String descripcion, Opciones verificacion,
                String edad, String tamaño, String tipoMascota, File foto){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.verificacion = verificacion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.foto = foto;
    }
    public Post(int id, String titulo, String descripcion, Opciones verificacion,
            String edad, String tamaño, String tipoMascota, File foto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.verificacion = verificacion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.foto = foto;
    }
    
    public Post(String titulo2, String descripcion2, Opciones opciones, String edad2, String tamaño2,
            String tipoMascosta) {
        //TODO Auto-generated constructor stub
    }
    public void asignar(String titulo, String descripcion, String tamaño, Opciones verificacion, String edad, String tipoMascota, File foto){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.verificacion = verificacion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.foto = foto;
    }
    /**
     * Metodo con el cual un Post se guardara en la base de datos
     * @param titulo
     * @param descripcion
     * @param tamaño
     * @param verificacion Clase en la que estaran atributos booleanos
     * @param edad
     * @param tipoMascota
     * @param foto
     * @return Regresa el objeto si se publico exitosamente. Sino un null.
     */
    public Post guardarPost(String titulo, String descripcion, String tamaño, Opciones verificacion, String edad, String tipoMascota, File foto) {
        asignar(titulo, descripcion, tamaño, verificacion, edad, tipoMascota, foto);
        return this;
    }
    /**
     * Metodo para editar un Post en la DB
     * @param nuevo Cambios del post
     * @return Post editar, caso no existir: null
     */
    public String editarPost(Post nuevo) {
        asignar(nuevo.titulo, nuevo.descripcion, nuevo.tamaño, nuevo.verificacion, nuevo.edad, tipoMascota, foto);
        return (DB.updatePost(this)) ? "OK" : "Error";
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
