package Backend.DB;

import Backend.DB.Exceptions.UserDoesNotExistException;
import Backend.Models.Usuario;
import java.nio.file.Path;
import java.sql.*;

public class DB {
    private static String USER = "root";
 	private static String PASS = "root";
	private static String DBNAME = "paw_connect";
	private static String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private static Connection con = null;
    public DB() {
        DB.connectToDatabase();
    }
    /*------------------
    MÉTODOS DE USUARIO
    ------------------*/
    /**
     * Metodo para verificar la existencia de un usuario en la BD
     * @param username Nombre de usuario
     * @return Confirmacion: true o false
     */
    public static boolean existeUsuario (String username) {
        ResultSet rs = null;
        try {
            String query = "SELECT usuario_id FROM Usuario WHERE usuario_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            //rs es True si existe alguna fila, es falso si no existe ninguna.
            //Si el usuario no existe, será falso.
            return rs.next();

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); } 
        finally { try { con.close(); } catch (Exception e) {/*Ignorado*/} }
        
        System.out.println("Si ven esto, Cata se mandó alguna cagada en existeUsuario.");
        return false;
    }
    /**
     * Metodo para traer un usuario de la BD
     * @param username Nombre de usuario
     * @return Tipo: Usuario
     * @throws UserDoesNotExistException
     */
    public static Usuario getUsuario (String username) throws UserDoesNotExistException {
        ResultSet rs = null;
        Usuario user = null;
        try {
            String query = "SELECT * FROM Usuario WHERE usuario_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();
            
            //Solo me molesto en fijarme en la primera fila del ResultSet, ya que debería haber un único usuario por nombre de usuario.
            if (rs.next()) {
                int id = rs.getInt("usuario_id");
                String password = rs.getString("usuario_contrasenia");
                String telephoneNumber = rs.getString("usuario_numero");
                String email = rs.getString("usuario_email");
                Path photo = null;
                //TODO: Ver lo de las fotos
                user = new Usuario(id, username, password, telephoneNumber, photo, email);
            }

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); } 

        //Si el nombre de usuario no existe, significa que todavía no se registró.
        if (user == null){ 
            return null;
        }
        return user;
    }
    /**
     * Metodo para insertar un usuario en la BD
     * @param user Tipo: Usuario
     * @return confirmacion: true o false
     */
    public static boolean crearUsuario (Usuario user) {
        ResultSet confirmacion;
        try {
            if (DB.existeUsuario(user.getNombre())) {
                String query = "INSERT INTO Usuario VALUES (NULL,'"+user.getContraseña()+"','"+user.getNombre()+"', '"+user.getMail()+"', '"+user.getFoto()+"', '"+user.getNumero_contacto()+"');";
                PreparedStatement stat = DB.con.prepareStatement(query);
                confirmacion = stat.executeQuery();
            } else {
                throw new Exception("El usuario "+user.getNombre()+" ya existe...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * Metodo para eliminar un usuario de la BD
     * @param nombreUsuario Nombre de usuario
     * @return confirmacion: true o false
     * @throws SQLException
     */
    public static boolean eliminarUsuario(String nombreUsuario) throws SQLException {
        try {
            Usuario user = DB.getUsuario(nombreUsuario);
            if (user != null) {
                String query = "DELETE FROM Usuario WHERE usuario_id = "+user.getId()+";";
                PreparedStatement stat = DB.con.prepareStatement(query);
                stat.executeQuery();
            }
        } catch (UserDoesNotExistException e) {
            System.out.println(e.getMessage());
            return false;
        }
        catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }
        return true;
    }
    /*------------------
    MÉTODOS DE REFUGIO
    ------------------*/
    /**
     * Metodo para verificar si existe un usuario refugio
     * @param username Nombre del  usuario tipo refugio
     * @return Confirmacion: true o false
     */
    public static boolean existeRefugio (String username) {
        ResultSet rs = null;
        try {
            String query = "SELECT administrador_id FROM administrador WHERE administrador_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            //rs es True si existe alguna fila, es falso si no existe ninguna.
            //Si el usuario no existe, será falso.
            return rs.next();

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); } 
        
        System.out.println("Si ven esto, Cata se mandó alguna cagada en existeUsuario.");
        return false;
    }

    //Verifica que no exista ni usuario ni refugio con el nombre de usuario dado.
    public static boolean existeNombre (String username) {
        ResultSet rs = null;
        try {
            String query = "SELECT usuario_id FROM usuario WHERE usuario_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            //rs es True si existe alguna fila, es falso si no existe ninguna.
            //Si el usuario no existe, será falso.
            if (rs.next()) return true;
            
            query = "SELECT administrador_id FROM administrador WHERE administrador_nombre = ?";
            stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            return rs.next();

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); } 
        
        System.out.println("Si ven esto, Cata se mandó alguna cagada en existeNombre.");
        return false;
    }
    /**
     * Metodo estatico para conectarse a la BD
     */
    private static void connectToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No se encontró la clase del Driver para SQL. Si ven esto, es porque tienen que agregar el driver (mysql-connector-j-8.0.32.jar) a las librerías referenciadas del proyecto.");
        } catch (SQLException sqle) {
            System.out.println("Hubo un error conectándose a la base de datos.");
        }
	}

}
