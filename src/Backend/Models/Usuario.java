package Backend.Models;

import java.nio.file.Path;
import java.sql.SQLException;

import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;

public class Usuario {
    /*
     * Atributos
     */
    
    public int id;
    public String nombre, contraseña, numero_contacto;
    public boolean acceso; // Dara un nivel de acceso dependiendo si es un usuario comun o un refugio.
    /*
     * Metodos
     */
    public Usuario(){}
     /**
      * Constructor con todos los atributos
      * @param id ID que se usa para identificar al usuario.
      * @param nombre Nombre que usaran para su usuario
      * @param contraseña Contraseña del usuario
      * @param numero_contacto Numero telefonico
      */
    public Usuario(int id, String nombre, String contraseña, String numero_contacto) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.numero_contacto = numero_contacto;
        setAcceso(false); //False indica que es un usuario.
    }
    /**
     * Metodo que asigna los atributos y lo guarda en la base de datos.
     * @param nombre
     * @param contra
     * @param numero
     * @return
     * @throws UserDoesNotExistException
     * @throws SQLException
     */
    public boolean registrarse(String nombre, String contra, String numero) throws UserDoesNotExistException, SQLException{
       setNombre(nombre);
       setContraseña(contra);
       setNumero_contacto(numero);
       setAcceso(false);
       return DB.crearUsuario(this);
    }
    /**
     * Con username y contraseña se compara si son iguales los ingresados a las del objeto encontrado.
     * @param nombre Nombre de usuario
     * @param contra Contraseña
     * @return Si son iguales devuelve un usuario sino un nulo.
     */
    public Usuario login(String nombre, String contra) {
        if(this.nombre.equals(nombre) && this.contraseña.equals(contra)){
            return this;
        }
       System.out.println("Usuario y/o contraseña incorrecto.");
       return null;
    }
    /**
     * Getters y Setters
     */
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getNumero_contacto() {
        return numero_contacto;
    }
    public void setNumero_contacto(String numero_contacto) {
        this.numero_contacto = numero_contacto;
    }
    public boolean isAcceso() {
        return acceso;
    }
    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }
     
}