package Backend.Models;
import java.nio.file.Path;
import java.util.ArrayList;
import Backend.DB.DB;

public class Post {
    /*
     *  Atributos 
     */
    private int id;
    private String titulo, raza, direccion, descripcion, verificacion, edad, tamaño, tipoMascota;
    private Path foto;
    
    /*
     * Metodos
     */
    public Post(){

    }
    public Post(int id, String titulo, String raza, String direccion, String descripcion, String verificacion,
            String edad, String tamaño, String tipoMascota, Path foto) {
        this.id = id;
        this.titulo = titulo;
        this.raza = raza;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.verificacion = verificacion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.foto = foto;
    }
    
    public void editar(String titulo, String desc, String raza, String tamaño, String edad){
        this.titulo = titulo;
        this.raza = raza;
        this.descripcion = desc;
        this.edad = edad;
        this.tamaño = tamaño;
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
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
