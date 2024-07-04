package Backend.DB;

import Backend.DB.Exceptions.UserDoesNotExistException;
import Backend.DB.Exceptions.UsernameIsTakenException;
import Backend.Models.Usuario;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class DB {
    private static String USER = "root";
 	private static String PASS = "root";
	private static String DBNAME = "paw_connect";
	private static String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private static Connection con = null;
    private static boolean connected = false;
    
    public DB () {
        connectToDatabase();
    }

    /*------------------
    MÉTODOS DE USUARIO
    ------------------*/

    public static Usuario getUsuario (String username) throws UserDoesNotExistException {
        
        if (!existeUsuario(username))
            throw new UserDoesNotExistException ("El usuario '" + username + "' no existe en la base de datos.");

        Usuario user = null;
        try {
            ResultSet rs = null;
            String query = "SELECT * FROM usuario WHERE " + UserField.NAME.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt(UserField.ID.field);
                String password = rs.getString(UserField.PASS.field);
                String telephoneNumber = rs.getString(UserField.NUM.field);
                String email = rs.getString(UserField.EMAIL.field);
                Path photo = Paths.get(rs.getString(UserField.PHOTO.field));

                user = new Usuario(id, username, password, telephoneNumber, photo);
            }

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); } 
        return user;
    }

    public static void resetUsuario (Usuario user) throws UserDoesNotExistException {
        
        if (!existeUsuario(user.getId()))
            throw new UserDoesNotExistException ("El usuario con el ID '" + user.getId() + "' no existe en la base de datos.");

        try {
            String query = "SELECT * FROM usuario WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, user.getId());
            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                String username = rs.getString(UserField.NAME.field);
                String password = rs.getString(UserField.PASS.field);
                String telephoneNumber = rs.getString(UserField.NUM.field);
                String email = rs.getString(UserField.EMAIL.field);
                Path photo = Paths.get(rs.getString(UserField.PHOTO.field));

                user.setNombre(username);
                user.setContraseña(password);
                user.setNumero_contacto(telephoneNumber);
                //TODO: user.setEmail(email);
                user.setFoto(photo);
                System.out.println("Se han reestablecido los valores del usuario con el ID '" + user.getId() + ".");
            }
        } catch (SQLException se) { System.out.println(se + "en getUsuario"); }
    }

    public static void SetUsuarioField (Usuario user, UserField FIELD) throws UserDoesNotExistException {
        if (!existeUsuario(user.getId()))
            throw new UserDoesNotExistException ("El usuario con el ID '" + user.getId() + "' no existe en la base de datos.");
        
        try {
            String query = "SELECT " + FIELD.field + " FROM usuario WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, user.getId());
            ResultSet rs = stat.executeQuery();

            switch (FIELD) {
                case NAME:
                    user.setNombre(rs.getString(UserField.NAME.field));
                    break;
                case PASS:
                    user.setContraseña(rs.getString(UserField.PASS.field));
                    break;
                case EMAIL:
                    //TODO: user.setEmail(rs.getString(UserField.EMAIL.field));
                    break;
                case NUM:
                    user.setNumero_contacto(rs.getString(UserField.NUM.field));
                    break;
                case PHOTO:
                    user.setFoto(Paths.get(rs.getString(UserField.PHOTO.field)));
                    break;
                case ID:
                    break;
            }

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); }
    }

    public static void crearUsuario (Usuario user) throws UsernameIsTakenException {
        
        //TODO: Verificar que no haya dirección de e-mail repetida si eventualmente se agrega.

        //Primero se verifica que no exista Refugio o Usuario con el mismo nombre.
        if (existeNombre(user.getNombre())) 
            throw new UsernameIsTakenException("El nombre de usuario '" + user.getNombre() + "' no se encuentra disponible.");

        try {
            String query = "INSERT INTO usuario (" + UserField.NAME.field + "," + UserField.PASS.field + "," + UserField.EMAIL.field + ","  + UserField.NUM.field + ", " + UserField.PHOTO.field + ") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, user.getNombre());
            stat.setString(2, user.getContraseña());
            //TODO: Agregar campo de e-mail
            stat.setString(3, "email@example.com");
            stat.setString(4, user.getNumero_contacto());
            stat.setString(5, user.getFoto().toString());
            stat.executeQuery();

            //Se le asigna el ID correspondiente al usuario.
            ResultSet rs = null;
            query = "SELECT " + UserField.ID + " from usuario WHERE " + UserField.NAME + " = ?";
            stat = con.prepareStatement(query);
            stat.setString(1, user.getNombre());
            rs = stat.executeQuery();
            rs.next();

            user.setId(rs.getInt(UserField.ID.field));

        } catch (SQLException se) { System.out.println(se + "en crearUsuario"); } 
    }

    public static void UpdateUsuario (Usuario user, UserField FIELD) throws UserDoesNotExistException, UsernameIsTakenException {
        
        if (FIELD == UserField.NAME && existeNombre(user.getNombre())) throw new UsernameIsTakenException ("El nombre de usuario '" + user.getNombre() + "' no se encuentra disponible.");
        else if (FIELD == UserField.ID) return;

        try {
            String query = "UPDATE usuario SET " + FIELD.field + " = ? WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, getFieldUsuario(user, FIELD));
            stat.setInt(2, user.getId());
            stat.executeQuery();

        } catch (SQLException se) { System.out.println(se + "en UpdateUsuario"); }
    }

    public static void UpdateUsuarioAll (Usuario user) throws UserDoesNotExistException, UsernameIsTakenException {

        try {
            String query = "UPDATE usuario SET " + UserField.NAME.field + " = ?, " + UserField.PASS.field + " = ?, " + UserField.EMAIL + " = ?, " + UserField.NUM.field + "= ? WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, user.getNombre());
            stat.setString(2, user.getContraseña());

            //TODO: Agregar variable e-mail
            stat.setString(3, "email@example.com");
            stat.setString(4, user.getNumero_contacto());
            stat.setInt(5, user.getId());
            stat.executeQuery();

        } catch (SQLException se) { System.out.println(se + "en UpdateUsuarioAll"); }
    }

    public static void EliminarUsuario (Usuario user) throws UserDoesNotExistException {
        EliminarUsuario(user.getNombre());
    }

    public static void EliminarUsuario (String username) throws UserDoesNotExistException {
        if (!existeUsuario(username))
            throw new UserDoesNotExistException ("El usuario '" + username + "' no existe en la base de datos.");

        try {
            String query = "DELETE FROM usuario WHERE " + UserField.NAME.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            System.out.println("El usuario '" + username + "' fue eliminado.");

        } catch (SQLException se) { System.out.println(se + "en EliminarUsuario"); }
    }

    public static void EliminarUsuario (int ID) throws UserDoesNotExistException {
        if (!existeUsuario(ID)) 
            throw new UserDoesNotExistException ("El usuario con el ID '" + ID + "' no existe en la base de datos.");

        try {
            String query = "DELETE FROM usuario WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, ID);
            System.out.println("El usuario con el ID '" + ID + "' fue eliminado.");

        } catch (SQLException se) { System.out.println(se + "en EliminarUsuario"); }
    }

    private static String getFieldUsuario (Usuario user, UserField FIELD) {
        String field = "";
        switch (FIELD) {
            case NAME:
                field = user.getNombre();
                break;
            case PASS:
                field = user.getContraseña();
                break;
            case NUM:
                field = user.getNumero_contacto();
                break;
            case PHOTO:
                field = user.getFoto().toString();
            case EMAIL:
                field = "email@example.com";
            default:
                break;
        }

        return field;
    }

    /*------------------
    MÉTODOS DE REFUGIO
    ------------------*/

    //Verifica que no exista ni usuario ni refugio con el nombre de usuario dado.
    public static boolean existeNombre (String username) {
        return existeUsuario(username) || existeRefugio(username);
    }

    public static boolean existeUsuario (String username) {
        ResultSet rs = null;
        try {
            String query = "SELECT " + UserField.NAME.field + " FROM usuario WHERE " + UserField.NAME.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            //rs es True si existe alguna fila, es falso si no existe ninguna.
            //Si el usuario no existe, será falso.
            return rs.next();
        } catch (SQLException se) { System.out.println(se + "en existeUsuario"); } 
        return false;
    }

    public static boolean existeUsuario (int ID) {
        ResultSet rs = null;
        try {
            String query = "SELECT " + UserField.ID.field + " FROM usuario WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, ID);
            rs = stat.executeQuery();

            return rs.next();
        } catch (SQLException se) { System.out.println(se + "en existeUsuario"); }
        return false; 
    }

    public static boolean existeRefugio (String username) {
        ResultSet rs = null;
        //TODO: Cambiar por formato field
        try {  
            String query = "SELECT administrador_id FROM administrador WHERE administrador_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            return rs.next();

        } catch (SQLException se) { System.out.println(se + "en existeRefugio"); } 
        return false;
    }

    public static void connectToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
            connected = true;
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No se encontró la clase del Driver para SQL. Si ven esto, es porque tienen que agregar el driver (mysql-connector-j-8.0.32.jar) a las librerías referenciadas del proyecto.");
        } catch (SQLException sqle) {
            System.out.println("Hubo un error conectándose a la base de datos.");
        }
	}

    public static void closeConnection() {
        if (connected) {
            try { 
                con.close();
                connected = false;
            } catch (Exception e) {/*Ignorado*/} 
        }
        
    }

    enum UserField {
        ID ("usuario_id"),
        NAME("usuario_nombre"),
        PASS("usuario_contrasenia"),
        EMAIL("usuario_email"),
        NUM("usuario_numero"),
        PHOTO("usuario_foto");

        public final String field;

        UserField (String field) {
            this.field = field;
        }
        
    }

}
