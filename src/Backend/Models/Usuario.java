package Backend.Models;

import java.nio.file.Path;

import Backend.DB.DB;
import Backend.DB.Exceptions.UserDoesNotExistException;

public class Usuario {
    /*
     * Atributos
     */
    
<<<<<<< HEAD
    public int id;
    public String nombre, contraseña, numero_contacto;
    public Path foto;
    public boolean acceso; // Dara un nivel de acceso dependiendo si es un usuario comun o un refugio.
    /*
     * Metodos
     */
    public Usuario(){}
=======
     private int id;
     private String nombre, contraseña, numero_contacto;
     private boolean acceso; // Dara un nivel de acceso dependiendo si es un usuario comun o un refugio.
     private Path foto;
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
     * @throws UserDoesNotExistException 
       */
     public Usuario(int id, String nombre, String contraseña, String numero_contacto, Path foto) throws UserDoesNotExistException {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.numero_contacto = numero_contacto;
        this.foto = foto;
        setAcceso(false); //False indica que es un usuario. 
     }

     public boolean registrarse(String nombre, String contraseña, String numero_contacto, Path foto) throws UserDoesNotExistException{
        Usuario user = DB.getUsuario(nombre);
        if(user == null){
            this.nombre = nombre;
            this.contraseña = contraseña;
            this.numero_contacto = numero_contacto;
            this.foto = foto;
            setAcceso(false);
            return true; // Registrado exitosamente
        }
        return false; // No pudo registrarse porque ya existe un usuario con ese nombre de Usuario.
     }
     
     public boolean login(String nombre, String contra) throws UserDoesNotExistException{
        Usuario user = DB.getUsuario(nombre);
        if(user != null){
            return this.nombre.equals(nombre) && contraseña.equals(contra);    
        }
        
        return false;
     }

>>>>>>> main
     /**
      * Constructor con todos los atributos
      * @param id ID que se usa para identificar al usuario.
      * @param nombre Nombre que usaran para su usuario
      * @param contraseña Contraseña del usuario
      * @param numero_contacto Numero telefonico
      */
    public Usuario(int id, String nombre, String contraseña, String numero_contacto, Path foto) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.numero_contacto = numero_contacto;
        this.foto = foto;
        setAcceso(false); //False indica que es un usuario.
    }
    
    public String registrarse(String nombre, String contra, String numero) throws UserDoesNotExistException{
       if(!DB.existeUsuario(nombre)){
           setNombre(nombre);
           setContraseña(contra);
           setNumero_contacto(numero);
           setAcceso(false);
           DB.crearUsuario(this);
           return "OK";
       }
       return "Error: Ya existe un usuario con ese nombre de usuario";
    }

    public Usuario login(String nombre, String contra) throws UserDoesNotExistException{
        // Revisar si para esto seria mejor solo tener un metodo para buscar entre todos los usuarios y refugios existentes
        Usuario user = DB.getUsuario(nombre);
        if(user.nombre.equals(nombre) && user.contraseña.equals(contra)){
            return user;
        }
       System.out.println("Usuario y/o contraseña incorrecto.");
       return null;
    }
    public Usuario vaciar(Usuario usuario){
        usuario = null;
        return usuario;
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
    public Path getFoto() {
       return foto;
    }
    public void setFoto(Path foto) {
       this.foto = foto;
    }
     
}
