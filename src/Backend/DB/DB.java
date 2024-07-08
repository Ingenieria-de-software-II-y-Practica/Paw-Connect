package Backend.DB;

import java.util.ArrayList;

import Backend.DB.Exceptions.*;
import Backend.Models.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import javax.xml.stream.events.StartDocument;

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
    /**
     * Obtiene la información de un usuario (de tipo 'Usuario') que ya ha sido registrado.
     * @param   username Nombre del usuario del que se quiere recuperar la información.
     * @return           Objeto 'Usuario' con toda la información que se encuentra en la base de datos. Devuelve null si el usuario no existe.
     */
    public static Usuario getUsuario (String username) throws UserDoesNotExistException {
        
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

        if (user == null) System.out.println("El usuario '" + username + "' no existe en la base de datos.");
        return user;
    }
    
    /**
     * Toma un objeto 'Usuario' ya existente y reestablece los valores de sus atributos a los que se encuentran en la base de datos, usando su ID de Usuario.
     * @param   user    El usuario al que se le quiere reestablecer los atributos.
     * @return          Confirmación de que se han reestablecido los atributos (true) o ha fallado la operación (false).
     */
    public static boolean resetUsuario (Usuario user) {
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
                return true;
            } else {
                System.out.println("El usuario con el ID '" + user.getId() + "' no existe en la base de datos.");
                return false;
            }
        } catch (SQLException se) { System.out.println(se + "en getUsuario"); }
        return false;
    }
    
    /**
     * Toma un objeto 'Usuario' ya existente y reestablece de algún atributo al valor que se encuentra en la base de datos.
     * @param user      El usuario al que se le quiere reestablecer el atributo.
     * @param FIELD     Atributo que se quiere reestablecer. NAME = Nombre; PASS = Contraseña; EMAIL = Dirección de correo electrónico; NUM = Número de contacto; PHOTO = Dirección de la imagen del usuario. Utilizar 'ID' devolverá false porque no puede ser cambiado.
     * @return          Confirmación de que se ha reestablecido el atributo elegido (true) o ha fallado la operación (false).
     */
    public static boolean resetUsuarioField (Usuario user, UserField FIELD) {
        try {
            String query = "SELECT " + FIELD.field + " FROM usuario WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, user.getId());
            ResultSet rs = stat.executeQuery();

            if (!rs.next()) {
                System.out.println("El usuario con el ID '" + user.getId() + "' no existe en la base de datos.");
                return false;
            }

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
                    return false;
            }
            return true;

        } catch (SQLException se) { System.out.println(se + "en getUsuario"); }
        return false;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     * @param user      El usuario al que se quiere registrar.
     * @return          Confirmación de que se ha creado el usuario (true) o ha fallado la operación (false).
     */
    public static boolean registerUsuario (Usuario user) {
        
        //TODO: Verificar que no haya dirección de e-mail repetida si eventualmente se agrega.

        //Primero se verifica que no exista Refugio o Usuario con el mismo nombre.
        if (existeNombre(user.getNombre())) {
            System.out.println("El nombre de usuario '" + user.getNombre() + "' no se encuentra disponible.");
            return false;
        }

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
            //TODO: Cambiarlo a LAST_INSERT_ID
            ResultSet rs = null;
            query = "SELECT " + UserField.ID + " from usuario WHERE " + UserField.NAME + " = ?";
            stat = con.prepareStatement(query);
            stat.setString(1, user.getNombre());
            rs = stat.executeQuery();
            rs.next();

            user.setId(rs.getInt(UserField.ID.field));
            System.out.println("El usuario '" + user.getNombre() + "' ha sido creado correctamente.");
            return true;

        } catch (SQLException se) { System.out.println(se + "en crearUsuario"); } 
        return false;
    }
    
    /**
     * Actualiza la base de datos con un atributo de un objeto 'Usuario'.
     * Se puede utilizar también el método "UpdateUsuarioAll", pero este existe por si sólo se necesita actualizar un único atributo para reducir la carga o por si se quiere cambiar uno solo.
     * @param user      El usuario al que se le quiere actualizar el atributo.
     * @param FIELD     Atributo que se quiere actualizar. NAME = Nombre; PASS = Contraseña; EMAIL = Dirección de correo electrónico; NUM = Número de contacto; PHOTO = Dirección de la imagen del usuario. Utilizar 'ID' devolverá false porque no puede ser cambiado.
     * @return          Confirmación de que se han actualizado los atributos (true) o ha fallado la operación (false).
     */
    public static boolean updateUsuario (Usuario user, UserField FIELD) {
        
        //Si lo que se quiere actualizar es el nombre de usuario, pero el nuevo nombre de usuario ya está ocupado, falla la operación.
        if (FIELD == UserField.NAME && existeNombre(user.getNombre())) {
            System.out.println("El nombre de usuario '" + user.getNombre() + "' no se encuentra disponible.");
            return false;
        } else if (FIELD == UserField.ID) return false;

        try {
            String query = "UPDATE usuario SET " + FIELD.field + " = ? WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, getFieldUsuario(user, FIELD));
            stat.setInt(2, user.getId());
            stat.executeQuery();
            System.out.println("Se ha actualizado el atributo '" + FIELD.field + "' del usuario '" + user.getNombre() + "' correctamente.");
            return true;

        } catch (SQLException se) { System.out.println(se + "en UpdateUsuario"); }
        return false;
    }
    
    /**
     * Actualiza la base de datos con todos los atributos de un objeto 'Usuario'.
     * @param user      El usuario al que se quiere actualizar en la base de datos.
     * @return          Confirmación de que se han actualizado los atributos del usuario en la base de datos (true) o ha fallado la operación (false).
     */
    public static boolean UpdateUsuarioAll (Usuario user) {
        if (!existeUsuario(user.getId())) {
            System.out.println("El usuario con el ID '" + user.getId() + "' no existe en la base de datos.");
            return false;
        }
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
            System.out.println("El usuario '" + user.getNombre() + "' ha sido actualizado en la base de datos.");
            return true;

        } catch (SQLException se) { System.out.println(se + "en UpdateUsuarioAll"); }
        return false;
    }

    /**
     * Elimina a un usuario de la base de datos.
     * @param user      El usuario al que se quiere eliminar de la base de datos.
     * @return          Confirmación de que se ha eliminado al usuario de la base de datos (true) o ha fallado la operación (false).
     */
    public static boolean EliminarUsuario (Usuario user) {
        return EliminarUsuario(user.getId());
    }

    public static boolean EliminarUsuario (String username) {
        if (!existeUsuario(username)) {
            System.out.println("El usuario '" + username + "' no existe en la base de datos.");
            return false;
        }
        try {
            String query = "DELETE FROM usuario WHERE " + UserField.NAME.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            System.out.println("El usuario '" + username + "' fue eliminado.");
            return true;

        } catch (SQLException se) { System.out.println(se + "en EliminarUsuario"); }
        return false;
    }

    /**
     * Elimina a un usuario de la base de datos.
     * @param ID        El ID del usuario al que se quiere eliminar de la base de datos.
     * @return          Confirmación de que se ha eliminado al usuario de la base de datos (true) o ha fallado la operación (false).
     */
    public static boolean EliminarUsuario (int ID) {
        if (ID < 0 || !existeUsuario(ID)) {
            System.out.println("El usuario con el ID '" + ID + "' no existe en la base de datos.");
            return false;
        }

        try {
            String query = "DELETE FROM usuario WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, ID);
            System.out.println("El usuario con el ID '" + ID + "' fue eliminado.");
            return true;

        } catch (SQLException se) { System.out.println(se + "en EliminarUsuario"); }
        return false;
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
    /**
     * Metodo para crear un usuario tipo Refugio
     * @param refugio Refugio
     * @return Confirmacion: true o false
     */
    public static boolean crearRefugio(Refugio refugio) {
        try {
            if (!(DB.existeNombre(refugio.getNombre()) && DB.existeRefugio(refugio.getNombre()))) {
                // Primero agregamos la direccion
                String[] direccionRefugio = refugio.getDireccion().split(","); //[0]:calle, [1]:altura, [2]:departamento
                PreparedStatement direccion_query = con.prepareStatement("INSERT INTO direccion VALUES(NULL, '"+direccionRefugio[0]+"', '"+direccionRefugio[1]+"', '"+direccionRefugio[2]+"');");
                direccion_query.executeQuery();
                PreparedStatement direccion_id_query = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet direccion_id = direccion_id_query.executeQuery();
                direccion_id.next();
                con.prepareStatement("INSERT INTO refugio VALUES(NULL,'"+direccion_id.getString("LAST_INSERT_ID")+"','"+refugio.getNombre()+"','1');").executeQuery();
                return true;    
            }
            else{
                System.out.println("El refugio ya existe");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * Metodo para obtener un refugio por su id
     * @param refugio_id id del refugio
     * @return Refugio
     */
    public static Refugio getRefugio(int refugio_id) {
        ResultSet refugioBD = null;
        Refugio refugio = null;
        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM refugio WHERE refugio_id = "+refugio_id+";");
            refugioBD = query.executeQuery();
            if (refugioBD.next()) {
                int id_refugio = Integer.parseInt(refugioBD.getString("refugio_id"));
                String nombreRefugio = refugioBD.getString("refugio_nombre");
                String direccion = refugioBD.getString("refugio_direccion_id");
                refugio = new Refugio(id_refugio,nombreRefugio, direccion);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (refugio == null) {
            return null;
        }else{
            return refugio;
        }
    }
    /**
     * Metodo para obtener la direccion de un refugio
     * @param id_direccion Refugio.getDireccion()
     * @return String direccion del refugio o null si no se encuentra
     */
    public static String getDirrecionRefugio(int id_direccion) {
        try {
            ResultSet consulta =con.prepareStatement("SELECT * FROM direccion WHERE direccion_id = "+id_direccion+";").executeQuery();
            if (consulta.next()) {
                return consulta.getString("direccion_calle")+","+consulta.getString("direccion_altura")+","+consulta.getString("direccion_departamento");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
    /**
     * Metodo para elimianar un refugio de la BD
     * @param refugio_id id del refugio: Refugio.getId()
     * @return Confirmacion: true o false
     */
    public static  boolean eliminarRefugio(int refugio_id) {
        try {
            PreparedStatement query = con.prepareStatement("DELETE FROM refugio WHERE refugio_id="+refugio_id+";");
            query.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /*------------------
    MÉTODOS DE REFUGIO
    ------------------*/

    public static boolean publicarPost (Post post) {
        try {
            
            int saludID = getSaludID(post.getVerificacion().isVacunas(), post.getVerificacion().isDesparacitado());
            int habitacionID = getHabitacionID(post.getVerificacion().isOtrasMascotas(), post.getVerificacion().isNiños());
            if (saludID == -1 || habitacionID == -1) {
                System.out.println("Ha habido un error al crear el post.");
                return false;
            }

            String query = "INSERT INTO mascota (mascota_nombre, mascota_edad, mascota_descripcion, mascota_recomendacion, mascota_id_refugio, mascota_salud, mascota_habitacion, mascota_tamanio, mascota_tipo)";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, post.getTitulo());
            stat.setString(2, post.getEdad());
            stat.setString(3, post.getDescripcion());
            //TODO: getRecomendacion();
            stat.setString(4, "Recomendación.");
            //TODO: getRefugio_ID
            stat.setInt(5, 0);
            stat.setInt(6, saludID);
            stat.setInt(7, habitacionID);
            stat.setString(8, post.getTamaño());
            stat.setString(9, post.getTipoMascota());

            System.out.println("El post se ha creado correctamente.");
            return true;

        } catch (SQLException sqe) { System.out.println(sqe + "en PublicarPost"); }
        return false;
    }

    public static ArrayList<Post> filtrarPost (Opciones opciones) {
        try {
            int saludID = getSaludID(opciones.isVacunas(), opciones.isDesparacitado());
            int habitacionID = getHabitacionID(opciones.isOtrasMascotas(), opciones.isNiños());

            String query = "SELECT * from mascota WHERE mascota_salud_id = ? and mascota_habitacion_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, saludID);
            stat.setInt(2, habitacionID);
            ResultSet rs = stat.executeQuery();
            ArrayList<Post> result = new ArrayList<Post>();
            Post post = null;
            Opciones opcionesPost = null;

            while (rs.next()) {
                post = new Post(rs.getInt("mascota_id"), rs.getString("mascota_nombre"), "RAZA", rs.getString("mascota_descripcion"), opciones, rs.getString("mascota_edad"), rs.getString("mascota_tamanio"), rs.getString("mascota_tipo"), Paths.get(rs.getString("mascota_foto"));
                result.add(post);
            }

            if (result.size() == 0) return null;
            else return result;
            
            
        } catch (SQLException sqe) {
            return null;
        }
    }

    //Verifica que no exista ni usuario ni refugio con el nombre de usuario dado.
    public static boolean existeNombre (String username) {
        return existeUsuario(username) || existeRefugio(username);
    }

    public static boolean existeUsuario (String username) {
        try {
            ResultSet rs = null;
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
        try {
            ResultSet rs = null;
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

    private static int getSaludID (boolean vacunado, boolean desparacitado) {
        int saludID = -1;
        try {
            
            String query = "SELECT salud_id FROM salud WHERE salud_vacunado = ? AND salud_desparacitado = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setBoolean(1, vacunado);
            stat.setBoolean(2, desparacitado);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) saludID = rs.getInt("salud_id");
            else {
                /*Todavía no existe tabla en 'salud' que corresponda a los atributos de la mascota que se quiere publicar.
                Se crea la entrada. */
                query = "INSERT INTO salud (salud_vacunado, salud_desparacitado) VALUES (?, ?)";
                stat = con.prepareStatement(query);
                stat.setBoolean(1, vacunado);
                stat.setBoolean(2, desparacitado);
                stat.executeQuery();

                //Se busca el ID de la entrada recién creada.
                query = "SELECT LAST_INSERT_ID()";
                stat = con.prepareStatement(query);
                rs = stat.executeQuery();
                rs.next();
                saludID = rs.getInt("salud_id");
            }
            
        } catch (SQLException sqe) { System.out.println(sqe + "en getSaludID"); }
        return saludID;
    }

    private static int getHabitacionID (boolean mascotas, boolean ninios) {
        int habitacionID = -1;
        try {
            
            String query = "SELECT habitacion_id FROM habitacion WHERE habitacion_otras_mascotas = ? AND habitacion_ninios = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setBoolean(1, mascotas);
            stat.setBoolean(2, ninios);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) habitacionID = rs.getInt("habitacion_id");
            else {
                query = "INSERT INTO habitacion (habitacion_otras_mascotas, habitacion_ninios) VALUES (?, ?)";
                stat = con.prepareStatement(query);
                stat.setBoolean(1,mascotas);
                stat.setBoolean(2, ninios);
                stat.executeQuery();

                query = "SELECT LAST_INSERT_ID()";
                stat = con.prepareStatement(query);
                rs = stat.executeQuery();
                rs.next();
                habitacionID = rs.getInt("habitacion_id");
            }
         }  catch (SQLException se) { System.out.println(se + "en getHabitacionID"); }

         return habitacionID;
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

    public enum UserField {
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