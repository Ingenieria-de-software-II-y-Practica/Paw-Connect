package Backend.DB;

import Backend.DB.Exceptions.UserDoesNotExistException;
import Backend.Models.Usuario;
import java.nio.file.Path;
import java.sql.*;

public abstract class DB {
    private static String USER = "root";
 	private static String PASS = "root";
	private static String DBNAME = "paw_connect";
	private static String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private static Connection con = null;

    public static boolean existeUsuario (String username) {
        ResultSet rs = null;
        try {
            connectToDatabase();
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

    public static Usuario getUsuario(String username) throws UserDoesNotExistException {
        ResultSet rs = null;
        Usuario user = null;
        try {
            connectToDatabase();
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
                //TODO: Agregar la variable email
                user = new Usuario(id, username, password, telephoneNumber, photo);
            }

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); } 
		finally { try { con.close(); } catch (Exception e) {/*Ignorado*/} }

        //Si el nombre de usuario no existe, significa que todavía no se registró.
        if (user == null) 
            throw new UserDoesNotExistException("El usuario '" + username + "' no existe en la base de datos.");

        return user;
    }



    public static void crearUsuario(Usuario user) {

    }

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
