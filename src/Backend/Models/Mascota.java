package Backend.Models;

import java.nio.file.Path;
/**
 * Clase Modelo de Mascotas
 */
public class Mascota {
    // ---------VARIABLES---------//
    private int idMascota;
    private String nombre, raza, descripcion, edad, tamaño, tipoMascota;
    private Opciones verificaciones;
    private Path foto;
    // ---------VARIABLES---------//
    // ---------CONSTRUCTORES---------//
    /**
     * Contructor de la clase
     * @param idMascota ID de la mascota en la BD
     * @param nombre Nombre de la mascota
     * @param raza Raza de la mascota
     * @param descripcion Descripcion de la mascota
     * @param edad Edad de la mascota
     * @param tamaño Tamaño de la mascota
     * @param tipoMascota Tipo de mascota es: Gato, Perro, Canario, Etc
     * @param verificaciones Clase Opciones con los checks que tenga la mascota
     * @param foto Foto de la mascota
     */
    public Mascota(int idMascota, String nombre, String raza, String descripcion, String edad, String tamaño, String tipoMascota, Opciones verificaciones, Path foto) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.raza = raza;
        this.descripcion = descripcion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.verificaciones = verificaciones;
        this.foto = foto;
    }
    /**
     * Constructor simplificado para operaciones como filtrar
     * @param nombre Nombre de la mascota
     * @param raza Raza de la mascota
     * @param descripcion Descripcion de la mascota
     * @param edad Edad de la mascota
     * @param tamaño Tamaño de la mascota
     * @param tipoMascota Tipo de mascota: Gato, Perro, Canario, Etc
     * @param verificaciones Clase Opciones con las verificaciones de la mascota
     */
    public Mascota(String nombre, String raza, String descripcion, String edad, String tamaño, String tipoMascota,
            Opciones verificaciones) {
        this.nombre = nombre;
        this.raza = raza;
        this.descripcion = descripcion;
        this.edad = edad;
        this.tamaño = tamaño;
        this.tipoMascota = tipoMascota;
        this.verificaciones = verificaciones;
    }
    // ---------CONSTRUCTORES---------//
    // ---------METODOS---------//
    // ---------METODOS---------//
    // ---------GETTERS AND SETTERS---------//
    /**
     * Metodo para obtener el ID de una Mascota
     * @return IDMascota : int
     */
    public int getIdMascota() {
        return idMascota;
    }
    /**
     * Metodo para cambiar el ID de una Mascota
     * @param idMascota IDMascota : int
     */
    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }
    /**
     * Metodo para obtener el nombre de una Mascota
     * @return Nombre : String
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo para cambiar el nombre de una Mascota
     * @param nombre Nombre : String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Metodo para obtener la raza de una Mascota
     * @return Raza : String
     */
    public String getRaza() {
        return raza;
    }
    /**
     * Metodo para cambiar la raza de una Mascota
     * @param raza Raza : String
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }
    /**
     * Metodo para obtener la descripcion de una Mascota
     * @return Descripcion : String
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Metodo para cambiar la descripcion de una Mascota
     * @param descripcion Descripcion : Mascota
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Metodo para obtener la edad de una Mascota
     * @return Edad : String
     */
    public String getEdad() {
        return edad;
    }
    /**
     * Metodo para cambiar la edad de una Mascota
     * @param edad Edad : String
     */
    public void setEdad(String edad) {
        this.edad = edad;
    }
    /**
     * Metodo para obtener el tamaño de una Mascota
     * @return Tamaño : String
     */
    public String getTamaño() {
        return tamaño;
    }
    /**
     * Metodo para cambiar el tamaño de una Mascota
     * @param tamaño Tamaño  : String
     */
    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
    /**
     * Metodo para obtener el tipo de una Mascota
     * @return Tipo : String
     */
    public String getTipoMascota() {
        return tipoMascota;
    }
    /**
     * Metodo para cambiar el tipo de una Mascota
     * @param tipoMascota Tipo : String
     */
    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }
    /**
     * Metodo para obtener las verificaciones que tiene una Mascota
     * @return Verificaciones : Opciones
     */
    public Opciones getVerificaciones() {
        return verificaciones;
    }
    /**
     * Metodo para cambiar las verificaciones de una Mascota
     * @param verificaciones Verificaciones : Opciones
     */
    public void setVerificaciones(Opciones verificaciones) {
        this.verificaciones = verificaciones;
    }
    /**
     * Metodo para obtener la foto de una Mascota
     * @return Foto : Path
     */
    public Path getFoto() {
        return foto;
    }
    /**
     * Metodo para cambiar la foto de una Mascota
     * @param foto Foto : Path
     */
    public void setFoto(Path foto) {
        this.foto = foto;
    }
    // ---------GETTERS AND SETTERS---------//
}
