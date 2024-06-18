package Backend.Models;

import java.nio.file.Path;
import java.util.ArrayList;
import Backend.DB.DB;

/**
 * Clase refugio hereda de Usuario porque es un tipo de usuario.
 */
public class Refugio extends Usuario{
    /*
     * Atributos
     */
    private String direccion;
    ArrayList<String> tipo_mascota;
    /*
     * Metodos
     */
    public Refugio(){}
    /**
     * Constructor que amplia el constructor de usuario con los atributos de esta clase.
     * @param nombre
     * @param contraseña
     * @param numero_contacto
     * @param direccion
     * @param tipo_mascota
     */
    public Refugio(int id, String nombre, String contraseña, String numero_contacto, String direccion, ArrayList<String> tipo_mascota) {
        super(id,nombre, contraseña, numero_contacto);
        setAcceso(true); // True indica que el usuario es un refugio.
        this.direccion = direccion;
        this.tipo_mascota = tipo_mascota;
    }

    /**
     * Registrarse del refugio el cual contiene mas informacion que el del usuario.
     * @param nombre
     * @param contra
     * @param numero
     * @param direccion
     */
    public void registrarse(String nombre, String contra, String numero, String direccion){
        setNombre(nombre);
        setContraseña(contra);
        setNumero_contacto(numero);
        setDireccion(direccion);
        setAcceso(true);
    }
    /*
     * GETTERS AND SETTERS
     */
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getTipo_mascota() {
        return tipo_mascota;
    }

    public void setTipo_mascota(ArrayList<String> tipo_mascota) {
        this.tipo_mascota = tipo_mascota;
    }
    
}
