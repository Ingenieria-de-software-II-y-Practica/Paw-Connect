package Backend.Models;

import Backend.DB.DB;
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
    public Refugio(int id, String nombre, String contraseña, String numero_contacto, String direccion){
        super(id,nombre, contraseña, numero_contacto);
        setAcceso(true); // True indica que el usuario es un refugio.
        this.direccion = direccion;
    }
    /**
     * Metodo el cual asigna valores a los atributos y guarda el Refugio en la base de datos
     * @param nombre
     * @param contra
     * @param numero
     * @param direccion
     * @return
     * @throws UserDoesNotExistException
     */
    public boolean registrarse(String nombre, String contra, String numero, String direccion){
        setNombre(nombre);
        setContraseña(contra);
        setNumero_contacto(numero);
        setDireccion(direccion);
        setAcceso(true);
        return DB.crearRefugio(this);
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