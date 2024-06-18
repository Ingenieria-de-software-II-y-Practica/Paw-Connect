package Backend.Models;
import Backend.DB.DB;

public class Opciones {
    // ---------VARIABLES---------//
    private int iDPost;
    private boolean vacunas;
    private boolean niños;
    private boolean otrasMascotas;
    private boolean desparacitado;
    // ---------CONSTRUCTORES---------//
    /**
     * Contructor para crear una nueva opcion en un Post
     * @param iDPost
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparacitado
     */
    public Opciones(int iDPost, boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado) {
        this.iDPost = iDPost;
        this.vacunas = vacunas;
        this.niños = niños;
        this.otrasMascotas = otrasMascotas;
        this.desparacitado = desparacitado;
    }
    /**
     * Constructor para buscar los parametros de las opciones
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparacitado
     */
    public Opciones(boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado) {
        this.vacunas = vacunas;
        this.niños = niños;
        this.otrasMascotas = otrasMascotas;
        this.desparacitado = desparacitado;
    }
    // ---------METODOS---------//
    // ---------GETTERS AND SETTERS---------//
    /**
     * Metodo para devolver el iDPost de las opciones
     * @return iDPost : Int 
     */
    public int getiDPost() {
        return iDPost;
    }
    /**
     * Metodo para cambiar el iDPost
     * @param iDPost iDPost : int
     */
    public void setiDPost(int iDPost) {
        this.iDPost = iDPost;
    }
    /**
     * Metodo para devolver si tiene o no vacunas
     * @return vacunas : boolean
     */
    public boolean isVacunas() {
        return vacunas;
    }
    /**
     * Metodo para cambiar el valor de las vacunas
     * @param vacunas vacunas : boolean
     */
    public void setVacunas(boolean vacunas) {
        this.vacunas = vacunas;
    }
    /**
     * Metodo para devolver si puede estar con niños o no
     * @return niños : boolean
     */
    public boolean isNiños() {
        return niños;
    }
    /**
     * Metodo para cambiar el valor de si puede estar con niños
     * @param niños niños : boolean
     */
    public void setNiños(boolean niños) {
        this.niños = niños;
    }
    /**
     * Metodo para devolver si puede estar con otras mascotas
     * @return mascotas : boolean
     */
    public boolean isOtrasMascotas() {
        return otrasMascotas;
    }
    /**
     * Metodo para cambiar si puede o no estar con otras mascotas
     * @param otrasMascotas mascotas : boolean
     */
    public void setOtrasMascotas(boolean otrasMascotas) {
        this.otrasMascotas = otrasMascotas;
    }
    /**
     * Metodo para devolver si la mascota esta desparazitada o no
     * @return desparazitada : boolean
     */
    public boolean isDesparacitado() {
        return desparacitado;
    }
    /**
     * Metodo para cambiar si esta desparazitada la mascota o no
     * @param desparacitado desparasitada : boolean
     */
    public void setDesparacitado(boolean desparacitado) {
        this.desparacitado = desparacitado;
    }
    
}
