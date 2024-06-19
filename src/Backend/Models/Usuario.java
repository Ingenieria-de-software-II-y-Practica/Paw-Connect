package Backend.Models;

public class Usuario {
    /*
     * Atributos
     */
    
     private int id;
     private String nombre, contraseña, numero_contacto;
     private boolean acceso; // Dara un nivel de acceso dependiendo si es un usuario comun o un refugio.
 
     /*
      * Metodos
      */
     public Usuario(){

     }
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
     public void login(){
        
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
