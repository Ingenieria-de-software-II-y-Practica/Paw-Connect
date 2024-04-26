package Backend.Models;
import Backend.DB.DB;

public class Opciones {
    // ---------VARIABLES---------//
    private int IDpost;
    private boolean vacunas;
    private boolean niños;
    private boolean otrasMascotas;
    private boolean desparazitado;
    // ---------CONSTRUCTORES---------//
    /**
     * Contructor para crear una nueva opcion en un Post
     * @param iDpost
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparazitado
     */
    public Opciones(int iDpost, boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparazitado) {
        IDpost = iDpost;
        this.vacunas = vacunas;
        this.niños = niños;
        this.otrasMascotas = otrasMascotas;
        this.desparazitado = desparazitado;
    }
    /**
     * Constructor para buscar los parametros de las opciones
     * @param vacunas
     * @param niños
     * @param otrasMascotas
     * @param desparazitado
     */
    public Opciones(boolean vacunas, boolean niños, boolean otrasMascotas, boolean desparazitado) {
        this.vacunas = vacunas;
        this.niños = niños;
        this.otrasMascotas = otrasMascotas;
        this.desparazitado = desparazitado;
    }
    // ---------METODOS---------//
    /**
     * Metodo para realizar la consulta a la base de datos sobre las opciones de un Post dado un ID
     * @param IDPost IDPost : int
     * @param db db : DB
     * @return opcionBuscada : Opciones
     */
    // public Opciones BDOpcionesID(int IDPost, DB db) {
    //      Opciones opcionBuscada = (Opciones) db.consulta("SELECT opciones_buscar_id("+IDPost+");")[0];
    //      return (opcionBuscada == null)? opcionBuscada : null;
    // }

    // public  boolean agregarOpciones(int IDPost, DB db) {
    //     try {
    //         // respuesta = db.consulta("")
    //     } catch (Exception e) {
    //     }
    // }
    // ---------GETTERS AND SETTERS---------//
    /**
     * Metodo para devolver el IDPost de las opciones
     * @return IDPost : Int 
     */
    public int getIDpost() {
        return IDpost;
    }
    /**
     * Metodo para cambiar el IDPost
     * @param iDpost IDPost : int
     */
    public void setIDpost(int iDpost) {
        IDpost = iDpost;
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
    public boolean isDesparazitado() {
        return desparazitado;
    }
    /**
     * Metodo para cambiar si esta desparazitada la mascota o no
     * @param desparazitado desparasitada : boolean
     */
    public void setDesparazitado(boolean desparazitado) {
        this.desparazitado = desparazitado;
    }
    
}
