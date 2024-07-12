package Backend.Models;

import java.nio.file.Path;

import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;
/**
 * Clase refugio hereda de Usuario porque es un tipo de usuario.
 */
public class Refugio extends Usuario{
    /*
     * Atributos
     */
    public String direccion;
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
     * @param foto
     */
    public Refugio(int id, String nombre, String contraseña, String numero_contacto, String direccion, Path foto){
        super(id,nombre, contraseña, numero_contacto,foto);
        setAcceso(true); // True indica que el usuario es un refugio.
        this.direccion = direccion;
    }

    public String registrarse(String nombre, String contra, String numero, String direccion) throws UserDoesNotExistException{
        if(DB.getUsuario(nombre) == null){
            setNombre(nombre);
            setContraseña(contra);
            setNumero_contacto(numero);
            setDireccion(direccion);
            setAcceso(true);
            DB.crearRefugio(this);
            return "OK";
        }
        return "Error: Ya existe un usuario con ese nombre de usuario.";
    }
    /*
     * GETTERS & SETTERS
     */
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
