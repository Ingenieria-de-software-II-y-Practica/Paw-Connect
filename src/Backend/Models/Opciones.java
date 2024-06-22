package Backend.Models;
import Backend.DB.DB;

public class Opciones {
    // ---------VARIABLES---------//
    private int iDpost;
    private boolean vacunas;
    private boolean niños;
    private boolean otrasMascotas;
    private boolean desparacitado;
    // ---------CONSTRUCTORES---------//
    /**
     * Contructor para crear una nueva opcion en un Post
     * @param iDpost
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparacitado
     */
    public Opciones(int iDpost, boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado) {
        this.iDpost = iDpost;
        this.vacunas = vacunas;
        this.niños = niños;
        this.otrasMascotas = otrasMascotas;
        this.desparacitado = desparacitado;
    }
    /**
     * Constructor crear opciones sin id
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
    
    public void editar(boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparacitado){
        this.vacunas = vacunas;
        this.niños = niños;
        this.otrasMascotas = otrasMascotas;
        this.desparacitado = desparacitado;
    }
    // ---------GETTERS AND SETTERS---------//
    /**
     * Metodo para devolver el iDPost de las opciones
     * @return iDPost : Int 
     */
    public int getiDpost() {
        return iDpost;
    }
    /**
     * Metodo para cambiar el iDPost
     * @param iDpost iDPost : int
     */
    public void setiDpost(int iDpost) {
        this.iDpost = iDpost;
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
     * Metodo para devolver si la mascota esta desparacitada o no
     * @return desparacitada : boolean
     */
    public boolean isDesparacitado() {
        return desparacitado;
    }
    /**
     * Metodo para cambiar si esta desparacitada la mascota o no
     * @param desparacitado desparacitada : boolean
     */
    public void setDesparacitado(boolean desparacitado) {
        this.desparacitado = desparacitado;
    }
    
}
