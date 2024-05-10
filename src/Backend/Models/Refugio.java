package Backend.Models;

import java.nio.file.Path;
/**
 * Clase refugio hereda de Usuario porque es un tipo de usuario.
 */
public class Refugio extends Usuario{
    /*
     * Atributos
     */
    private String direccion, tipo_mascota;
    private Path foto;
    /*
     * Metodos
     */

    /**
     * Constructor que amplia el constructor de usuario con los atributos de esta clase.
     * @param id
     * @param nombre
     * @param contraseña
     * @param numero_contacto
     * @param direccion
     * @param tipo_mascota
     * @param foto
     */
    public Refugio(int id, String nombre, String contraseña, String numero_contacto, String direccion, String tipo_mascota, Path foto) {
        super(id, nombre, contraseña, numero_contacto);
        setAcceso(true); // True indica que el usuario es un refugio.
        this.direccion = direccion;
        this.tipo_mascota = tipo_mascota;
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_mascota() {
        return tipo_mascota;
    }

    public void setTipo_mascota(String tipo_mascota) {
        this.tipo_mascota = tipo_mascota;
    }

    public Path getFoto() {
        return foto;
    }

    public void setFoto(Path foto) {
        this.foto = foto;
    }
    
}
