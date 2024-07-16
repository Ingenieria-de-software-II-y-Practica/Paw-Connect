package Backend.DB;

import java.util.ArrayList;
import Backend.Models.*;
import java.io.File;
import java.sql.*;

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
    public static Usuario getUsuario (String username) {
        
        Usuario user = null;
        try {
            String query = "SELECT * FROM usuario INNER JOIN telefono ON usuario.telefono_id = telefono.telefono_id WHERE usuario_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            ResultSet rs = stat.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("usuario.usuario_id");
                String password = rs.getString("usuario.usuario_contrasenia");
                String telephoneNumber = rs.getString("telefono.telefono_telefono");

                user = new Usuario(id, username, password, telephoneNumber);
            }

        } catch (SQLException se) { System.out.println(se + " en getUsuario"); }

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
            String query = "SELECT * FROM usuario WHERE usuario_nombre = ? INNER JOIN telefono ON usuario.telefono_id = telefono.telefono_id";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, user.getId());
            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                String username = rs.getString("usuario.usuario_nombre");
                String password = rs.getString("usuaro.usuario_contrasenia");
                String telephoneNumber = rs.getString("telefono.telefono_telefono");

                user.setNombre(username);
                user.setContraseña(password);
                user.setNumero_contacto(telephoneNumber);
                //TODO: user.setEmail(email);

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
            String query = "SELECT " + FIELD.field + " FROM usuario WHERE usuario_id = ?";
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
        //TODO: Verificar que el número no esté repetido.

        //Primero se verifica que no exista Refugio o Usuario con el mismo nombre.
        if (existeNombre(user.getNombre())) {
            System.out.println("El nombre de usuario '" + user.getNombre() + "' no se encuentra disponible.");
            return false;
        }

        try {
            //Se inserta el número de teléfono primero porque está en una tabla separada.
            String query = "INSERT INTO telefono (telefono_telefono) VALUES (?)";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, user.getNumero_contacto());
            stat.executeUpdate();

            //Se obtiene el ID del teléfono recién insertado.
            query = "SELECT LAST_INSERT_ID()";
            stat = con.prepareStatement(query);
            ResultSet rs = stat.executeQuery();
            rs.next();

            int telephoneID = rs.getInt(1);

            query = "INSERT INTO usuario (usuario_nombre, usuario_contrasenia, usuario_email, telefono_id) VALUES (?, ?, ?, ?)";
            stat = con.prepareStatement(query);
            stat.setString(1, user.getNombre());
            stat.setString(2, user.getContraseña());
            //TODO: Agregar campo de e-mail
            stat.setString(3, "email@example.com");
            stat.setInt(4, telephoneID);
            stat.executeUpdate();

            //Se le asigna el ID correspondiente al objeto Usuario.            
            query = "SELECT LAST_INSERT_ID()";
            stat = con.prepareStatement(query);
            rs = stat.executeQuery();
            rs.next();

            user.setId(rs.getInt(1));
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
            //Como el número de teléfono se encuentra en una tabla separada, se tiene que considerar individualmente.
            if (FIELD == UserField.NUM) {
                String query = "SELECT telefono_id from usuario WHERE usuario_id = ?";
                PreparedStatement stat = con.prepareStatement(query);
                stat.setInt(1, user.getId());
                ResultSet rs = stat.executeQuery();
                rs.next();

                int telephoneID = rs.getInt(1);

                query = "UPDATE telefono SET telefono_telefono = ? WHERE telefono_id = ?";
                stat = con.prepareStatement(query);
                stat.setString(1, user.getNumero_contacto());
                stat.setInt(2, telephoneID);
                stat.executeQuery();
                System.out.println("Se ha actualizado el teléfono del usuario '" + user.getNombre() + "''.");
                return true;
            }

            String query = "UPDATE usuario SET " + FIELD.field + " = ? WHERE usuario_id = ?";
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
            String query = "UPDATE usuario SET usuario_nombre = ?, usuario_contrasenia = ?, usuario_email = ? WHERE usuario_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, user.getNombre());
            stat.setString(2, user.getContraseña());
            //TODO: Agregar variable e-mail
            stat.setString(3, "email@example.com");
            stat.setInt(4, user.getId());
            stat.executeUpdate();

            updateUsuario(user, UserField.NUM);
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
    public static boolean eliminarUsuario (Usuario user) {
        return eliminarUsuario(user.getId());
    }

    public static boolean eliminarUsuario (String username) {
        if (!existeUsuario(username)) {
            System.out.println("El usuario '" + username + "' no existe en la base de datos.");
            return false;
        }
        try {
            //Es necesario borrar el número de teléfono de forma independiente porque está en otra tabla.
            
            //Primero se obtiene telefono_id de la tabla 'usuario'.
            String query = "SELECT telefono_id FROM usuario WHERE usuario_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            ResultSet rs = stat.executeQuery();
            rs.next();

            int telephoneID = rs.getInt(1);

            query = "DELETE FROM usuario WHERE usuario_nombre = ?";
            stat = con.prepareStatement(query);
            stat.setString(1, username);
            System.out.println("El usuario '" + username + "' fue eliminado.");

            //Se elimina la entrada de la tabla 'telefono' correspondiente.
            query = "DELETE FROM telefono WHERE telefono_id = ?";
            stat = con.prepareStatement(query);
            stat.setInt(1, telephoneID);
            stat.executeQuery();
            return true;

        } catch (SQLException se) { System.out.println(se + "en EliminarUsuario"); }
        return false;
    }

    /**
     * Elimina a un usuario de la base de datos.
     * @param ID        El ID del usuario al que se quiere eliminar de la base de datos.
     * @return          Confirmación de que se ha eliminado al usuario de la base de datos (true) o ha fallado la operación (false).
     */
    public static boolean eliminarUsuario (int ID) {
        if (ID < 0 || !existeUsuario(ID)) {
            System.out.println("El usuario con el ID '" + ID + "' no existe en la base de datos.");
            return false;
        }

        try {
            //Es necesario borrar el número de teléfono de forma independiente porque está en otra tabla.
            
            //Primero se obtiene telefono_id de la tabla 'usuario'.
            String query = "SELECT telefono_id FROM usuario WHERE usuario_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, ID);
            ResultSet rs = stat.executeQuery();
            rs.next();

            int telephoneID = rs.getInt(1);

            query = "DELETE FROM usuario WHERE usuario_id = ?";
            stat = con.prepareStatement(query);
            stat.setInt(1, ID);

            //Se elimina la entrada de la tabla 'telefono' correspondiente.
            query = "DELETE FROM telefono WHERE telefono_id = ?";
            stat = con.prepareStatement(query);
            stat.setInt(1, telephoneID);
            stat.executeQuery();

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
     * Crea un nuevo refugio en la base de datos.
     * @param       refugio Refugio que se quiere registrar en la base de datos.
     * @return      Confirmación de que se ha creado el usuario (true) o ha fallado la operación (false).
     */
    public static boolean crearRefugio(Refugio refugio) {
        //Primero se verifica que no exista Refugio o Usuario con el mismo nombre.
        if (existeNombre(refugio.getNombre())) {
            System.out.println("El nombre de usuario '" + refugio.getNombre() + "' no se encuentra disponible.");
            return false;
        }

        try {
            //Primero se agrega la dirección.
            String[] addressRefugio = refugio.getDireccion().split(","); //[0]:calle, [1]:altura, [2]:departamento
            String query = "INSERT INTO direccion (direccion_calle, direccion_altura, direccion_departamento) VALUES (?, ?, ?)";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, addressRefugio[0]);
            try{
                stat.setString(2, addressRefugio[1]); 
                stat.setString(3, addressRefugio[2]);
            } catch(Exception e){
                
            }
            
            stat.executeUpdate();

            //Se obtiene el ID de la dirección recién insertada.
            query = "SELECT LAST_INSERT_ID()";
            stat = con.prepareStatement(query);
            ResultSet rs = stat.executeQuery();
            rs.next();

            int addressID = rs.getInt(1);

            //Se repite el proceso con el número de teléfono.
            query = "INSERT INTO telefono (telefono_telefono) VALUES (?)";
            stat = con.prepareStatement(query);
            stat.setString(1, refugio.getNumero_contacto());
            stat.executeUpdate();

            query = "SELECT LAST_INSERT_ID()";
            stat = con.prepareStatement(query);
            rs = stat.executeQuery();
            rs.next();

            int telephoneID = rs.getInt(1);

            //Se repite con la tabla 'administrador', que contiene todas las características iguales a los usuarios pero para refugios.
            query = "INSERT INTO administrador (administrador_nombre, administrador_contrasenia, administrador_email) VALUES (?, ?, ?)";
            stat = con.prepareStatement(query);
            stat.setString(1, refugio.getNombre());
            stat.setString(2, refugio.getContraseña());
            //TODO: Agregar e-mail.
            stat.setString(3, "email@example.com");
            stat.executeUpdate();

            query = "SELECT LAST_INSERT_ID()";
            stat = con.prepareStatement(query);
            rs = stat.executeQuery();
            rs.next();

            int administratorID = rs.getInt(1);

            //Ahora se inserta el resto de la información del refugio.
            //TODO: Nombre de usuario != nombre del refugio.
            query = "INSERT INTO refugio (refugio_nombre, administrador_id, direccion_id, telefono_id) VALUES (?, ?, ?, ?)";
            stat = con.prepareStatement(query);
            stat.setString(1, refugio.getNombre());
            stat.setInt(2, administratorID);
            stat.setInt(3, addressID);
            stat.setInt(4, telephoneID);
            stat.executeUpdate();

            //Se busca el ID para asignárselo a el objeto 'refugio'.
            query = "SELECT LAST_INSERT_ID()";
            stat = con.prepareStatement(query);
            rs = stat.executeQuery();
            rs.next();

            refugio.setId(rs.getInt(1));
            System.out.println("El refugio '" + refugio.getNombre() + "' ha sido creado correctamente.");
            return true;
        } catch (SQLException se) { System.out.println(se + "en crearRefugio"); }
        return false;
    }

    public static Refugio getRefugio (String username) {
        Refugio refugio = null;
        try {
            //El nombre de usuario corresponde al administrador, así que primero se debe buscar su ID.
            String query = "SELECT * FROM administrador WHERE administrador_nombre = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, username);
            ResultSet rs = stat.executeQuery();
            rs.next();

            String password = rs.getString("administrador_contrasenia");
            int administratorID = rs.getInt("administrador_id");

            query = "SELECT * FROM refugio INNER JOIN telefono ON telefono.telefono_id = refugio.telefono_id INNER JOIN direccion ON direccion.direccion_id = refugio.direccion_id WHERE refugio.administrador_id = ?";
            stat = con.prepareStatement(query);
            stat.setInt(1, administratorID);
            rs = stat.executeQuery();
            rs.next();
            
            int refugioID = rs.getInt("refugio.refugio_id");
            String nameRefugio = rs.getString("refugio.refugio_nombre");
            String telephoneNumber = rs.getString("telefono.telefono_telefono");
            String address = rs.getString("direccion.direccion_calle") + "," + rs.getString("direccion.direccion_altura") + "," + rs.getString("direccion.direccion_departamento");

            refugio = new Refugio(refugioID, nameRefugio, password, telephoneNumber, address);

        } catch (SQLException se) { System.out.println(se + "en getRefugio"); }
        return refugio;
    }

    /*------------------
    MÉTODOS DE POST
    ------------------*/

    public static boolean publicarPost (Post post, int refugioID) {
        try {
            int saludID = getSaludID(post.getVerificacion().isVacunas(), post.getVerificacion().isDesparacitado());
            int habitacionID = getHabitacionID(post.getVerificacion().isOtrasMascotas(), post.getVerificacion().isNiños());
            int animalID = getAnimalID(post.getTipoMascota(), post.getTamaño());

            if (saludID == -1 || habitacionID == -1 || animalID == -1) {
                System.out.println("Ha habido un error al crear el post.");
                return false;
            }

            String query = "INSERT INTO mascota (mascota_nombre, mascota_edad, mascota_descripcion, mascota_recomendacion, refugio_id, salud_id, animal_id, habitacion_id, mascota_foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, post.getTitulo());
            stat.setString(2, post.getEdad());
            stat.setString(3, post.getDescripcion());
            stat.setString(4, "Recomendación.");
            //TODO: RefugioID
            stat.setInt(5, refugioID);
            stat.setInt(6, saludID);
            stat.setInt(7, animalID);
            stat.setInt(8, habitacionID);
            //TODO: Foto mascota
            stat.setString(9, null);
            stat.executeUpdate();

            //Busco el ID recién insertado para asignarselo a el objeto 'Post'
            query = "SELECT LAST_INSERT_ID()";
            stat = con.prepareStatement(query);
            ResultSet rs = stat.executeQuery();
            rs.next();
            post.setId(rs.getInt(1));
            
            System.out.println("El post se ha creado correctamente.");
            return true;

        } catch (SQLException sqe) { System.out.println(sqe + "en PublicarPost"); }
        return false;
    }

    public static boolean updatePost (Post post) {
        try {
            int saludID = getSaludID(post.getVerificacion().isVacunas(), post.getVerificacion().isDesparacitado());
            int habitacionID = getHabitacionID(post.getVerificacion().isOtrasMascotas(), post.getVerificacion().isNiños());
            int animalID = getAnimalID(post.getTipoMascota(), post.getTamaño());

            if (saludID == -1 || habitacionID == -1 || animalID == -1) {
                System.out.println("Ha habido un error al actualizar el post.");
                return false;
            }

            //Se busca el ID del refugio que corresponde a éste post.
            String query = "SELECT refugio_id FROM mascota WHERE mascota_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, post.getId());
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                System.out.println("El post especificado no está publicado.");
                return false;
            }
            int refugioID = rs.getInt(1);

            //Se actualizan todos los valores del post.
            query = "UPDATE mascota SET mascota_nombre = ?, mascota_edad = ?, mascota_descripcion = ?, mascota_recomendacion = ?, refugio_id = ?, salud_id = ?, animal_id = ?, habitacion_id = ?, mascota_foto = ? WHERE mascota_id = ?";
            stat = con.prepareStatement(query);
            stat.setString(1, post.getTitulo());
            stat.setString(2, post.getEdad());
            stat.setString(3, post.getDescripcion());
            stat.setString(4, "Recomendacion");
            stat.setInt(5, refugioID);
            stat.setInt(6, saludID);
            stat.setInt(7, animalID);
            stat.setInt(8, habitacionID);
            //TODO: Foto mascota
            stat.setString(9, post.getFoto().getAbsolutePath());
            stat.setInt(10, post.getId());
            stat.executeUpdate();

            System.out.println("El post se ha actualizado correctamente.");
            return true;

        } catch (SQLException sqe) { System.out.println(sqe + "en PublicarPost"); }
        return false;
    }

    public static Post getPost (int postID) {
        try {
            String query = "SELECT * FROM mascota INNER JOIN animal ON animal.animal_id = mascota.animal_id INNER JOIN habitacion ON habitacion.habitacion_id = mascota.habitacion_id INNER JOIN salud ON salud.salud_id = mascota.salud_id WHERE mascota.mascota_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, postID);
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                System.out.println("El post con ID '" + postID + "' no existe en la base de datos.");
                return null;
            }

            String nombre, descripcion, edad, tamanio, animal, fotopath;
            Opciones verificacion;
            boolean isVacunado, isDesparacitado, isNinios, isOtrasMascotas;

            isVacunado = rs.getBoolean("salud.salud_vacunado");
            isDesparacitado = rs.getBoolean("salud.salud_desparacitado");
            isNinios = rs.getBoolean("habitacion.habitacion_ninios");
            isOtrasMascotas = rs.getBoolean("habitacion.habitacion_otras_mascotas");
            tamanio = rs.getString("animal.animal_tamanio");
            animal = rs.getString("animal.animal_nombre");

            verificacion = new Opciones(isVacunado, isNinios, isOtrasMascotas, isDesparacitado);
            postID = rs.getInt("mascota.mascota_id");
            nombre = rs.getString("mascota.mascota_nombre");
            descripcion = rs.getString("mascota.mascota_descripcion");
            edad = rs.getString("mascota.mascota_edad");
            fotopath = rs.getString("mascota.mascota_foto");
            File foto = new File("fotopath");
            Post post = new Post(nombre, descripcion, verificacion, edad, tamanio, animal,foto);
            return post;

        } catch (SQLException sqe) { System.out.println(sqe + " en getPost"); }
        return null;
    }

    public static ArrayList<Post> allPostRefugio (int refugioID) {
        try {
            String query = "SELECT * FROM mascota INNER JOIN animal ON animal.animal_id = mascota.animal_id INNER JOIN habitacion ON habitacion.habitacion_id = mascota.habitacion_id INNER JOIN salud ON salud.salud_id = mascota.salud_id WHERE mascota.refugio_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, refugioID);
            ResultSet rs = stat.executeQuery();

            ArrayList<Post> result = new ArrayList<>();
            Post post;

            //Atributos de post.
            int postID;
            String nombre, descripcion, edad, fotopath, tamanio, animal;
            Opciones verificacion;
            boolean isVacunado, isDesparacitado, isNinios, isOtrasMascotas;

            while (rs.next()) {
                isVacunado = rs.getBoolean("salud.salud_vacunado");
                isDesparacitado = rs.getBoolean("salud.salud_desparacitado");
                isNinios = rs.getBoolean("habitacion.habitacion_ninios");
                isOtrasMascotas = rs.getBoolean("habitacion.habitacion_otras_mascotas");
                tamanio = rs.getString("animal.animal_tamanio");
                animal = rs.getString("animal.animal_nombre");

                verificacion = new Opciones(isVacunado, isNinios, isOtrasMascotas, isDesparacitado);
                postID = rs.getInt("mascota.mascota_id");
                nombre = rs.getString("mascota.mascota_nombre");
                descripcion = rs.getString("mascota.mascota_descripcion");
                edad = rs.getString("mascota.mascota_edad");
                fotopath = rs.getString("mascota.mascota_foto");
                File foto = new File("null");

                post = new Post(postID, nombre, descripcion, verificacion, edad, tamanio, animal, foto);
                System.out.println(post.toString());
                result.add(post);
                System.out.println("Adios");
            }
            System.out.println("Hola");

            if (result.isEmpty()) return null;
            else return result;
            
        } catch (SQLException sqe) {
            return null;
        }
    }
    public static ArrayList<Post> getAllPosts() {
        try {

            String query = "SELECT * FROM mascota INNER JOIN animal ON animal.animal_id = mascota.animal_id INNER JOIN habitacion ON habitacion.habitacion_id = mascota.habitacion_id INNER JOIN salud ON salud.salud_id = mascota.salud_id";
            PreparedStatement stat = con.prepareStatement(query);
            ResultSet rs = stat.executeQuery();

            ArrayList<Post> result = new ArrayList<Post>();
            Post post;

            //Atributos de post.
            int postID;
            String nombre, descripcion, edad, fotopath, tamanio, animal;
            Opciones verificacion;
            boolean isVacunado, isDesparacitado, isNinios, isOtrasMascotas;

            while (rs.next()) {
                isVacunado = rs.getBoolean("salud.salud_vacunado");
                isDesparacitado = rs.getBoolean("salud.salud_desparacitado");
                isNinios = rs.getBoolean("habitacion.habitacion_ninios");
                isOtrasMascotas = rs.getBoolean("habitacion.habitacion_otras_mascotas");

                verificacion = new Opciones(isVacunado, isNinios, isOtrasMascotas, isDesparacitado);
                postID = rs.getInt("mascota.mascota_id");
                nombre = rs.getString("mascota.mascota_nombre");
                descripcion = rs.getString("mascota.mascota_descripcion");
                edad = rs.getString("mascota.mascota_edad");
                fotopath = rs.getString("mascota.mascota_foto");
                tamanio = rs.getString("animal.animal_tamanio");
                animal = rs.getString("animal.animal_nombre");
                File foto = new File(fotopath);

                post = new Post(postID, nombre, descripcion, verificacion, edad, tamanio, animal, foto);
                result.add(post);
            }

            if (result.isEmpty()) return null;
            else return result;
        } catch (SQLException sqe) { System.out.println(sqe + " en getAllPosts"); }
        return null;
    }

    public static ArrayList<Post> filtrarPost (Opciones opciones, String tamanio, String animal) {
        try {
            int saludID = getSaludID(opciones.isVacunas(), opciones.isDesparacitado());
            int habitacionID = getHabitacionID(opciones.isOtrasMascotas(), opciones.isNiños());
            int animalID = getAnimalID(animal, tamanio);

            String query = "SELECT * FROM mascota INNER JOIN animal ON animal.animal_id = mascota.animal_id INNER JOIN habitacion ON habitacion.habitacion_id = mascota.habitacion_id INNER JOIN salud ON salud.salud_id = mascota.salud_id WHERE mascota.salud_id = ? AND mascota.habitacion_id = ? AND mascota.animal_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, saludID);
            stat.setInt(2, habitacionID);
            stat.setInt(3, animalID);
            ResultSet rs = stat.executeQuery();

            ArrayList<Post> result = new ArrayList<Post>();
            Post post;

            //Atributos de post.
            int postID;
            String nombre, descripcion, edad, fotopath;
            File foto;
            Opciones verificacion;
            boolean isVacunado, isDesparacitado, isNinios, isOtrasMascotas;

            while (rs.next()) {
                isVacunado = rs.getBoolean("salud.salud_vacunado");
                isDesparacitado = rs.getBoolean("salud.salud_desparacitado");
                isNinios = rs.getBoolean("habitacion.habitacion_ninios");
                isOtrasMascotas = rs.getBoolean("habitacion.habitacion_otras_mascotas");

                verificacion = new Opciones(isVacunado, isNinios, isOtrasMascotas, isDesparacitado);
                postID = rs.getInt("mascota.mascota_id");
                nombre = rs.getString("mascota.mascota_nombre");
                descripcion = rs.getString("mascota.mascota_descripcion");
                edad = rs.getString("mascota.mascota_edad");
                fotopath = rs.getString("mascota.mascota_foto");
                tamanio = rs.getString("animal.animal_tamanio");
                animal = rs.getString("animal.animal_nombre");
                foto = new File(fotopath);

                post = new Post(postID, nombre, descripcion, verificacion, edad, tamanio, animal, foto);
                result.add(post);
            }

            if (result.isEmpty()) return null;
            else return result;
            
        } catch (SQLException sqe) {
            System.out.println(sqe + "en FiltrarPost");
            return null;
        }
    }

    public static boolean eliminarPost (Post post) {
        return eliminarPost(post.getId());
    }

    public static boolean eliminarPost (int postID) {
        try {
            String query = "DELETE FROM mascota WHERE mascota_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, postID);
            stat.executeUpdate();
            System.out.println("El post con ID '" + postID + "' ha sido eliminado correctamente.");
            return true;

        } catch (SQLException sqe) { System.out.println(sqe + " en eliminarPost"); }
        return false;
    }
    public static int getRefugioID (int postID) {
        try {
            String query = "SELECT refugio_id FROM mascota WHERE mascota_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, postID);
            ResultSet rs = stat.executeQuery();

            if (!rs.next()) {
                System.out.println("El post con ID '" + postID + "' no existe.");
                return -1;
            }
            return rs.getInt(1);

        } catch (SQLException sqe) { System.out.println(sqe + " en getRefugioID"); }
        return -1;
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
                stat.executeUpdate();

                //Se busca el ID de la entrada recién creada.
                query = "SELECT LAST_INSERT_ID()";
                stat = con.prepareStatement(query);
                rs = stat.executeQuery();
                rs.next();
                saludID = rs.getInt(1);
            }
            
        } catch (SQLException sqe) { System.out.println(sqe + "en getSaludID"); }
        return saludID;
    }
    public static Refugio getRefugio (int refugioID) {
        try {
            String query = "SELECT * FROM refugio INNER JOIN administrador ON administrador.administrador_id = refugio.administrador_id INNER JOIN telefono ON telefono.telefono_id = refugio.telefono_id INNER JOIN direccion ON direccion.direccion_id = refugio.direccion_id WHERE refugio.refugio_id = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, refugioID);
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                System.out.println("El refugio con el ID '" + refugioID + "' no existe en la base de datos.");
                return null;
            }

            String nameRefugio = rs.getString("refugio.refugio_nombre");
            String telephoneNumber = rs.getString("telefono.telefono_telefono");
            String address = rs.getString("direccion.direccion_calle") + "," + rs.getString("direccion.direccion_altura") + "," + rs.getString("direccion.direccion_departamento");
            String password = rs.getString("administrador.administrador_contrasenia");

            Refugio refugio = new Refugio(refugioID, nameRefugio, password, telephoneNumber, address);
            return refugio;

        } catch (SQLException sqe) { System.out.println(sqe + " en getRefugio"); }
        return null;
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
                stat.executeUpdate();

                query = "SELECT LAST_INSERT_ID()";
                stat = con.prepareStatement(query);
                rs = stat.executeQuery();
                rs.next();
                habitacionID = rs.getInt(1);
            }
         }  catch (SQLException se) { System.out.println(se + "en getHabitacionID"); }

         return habitacionID;
    }
    
    private static int getAnimalID (String animal, String tamanio) {
        int animalID = -1;
        try {
            
            String query = "SELECT animal_id FROM animal WHERE animal_nombre = ? AND animal_tamanio = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setString(1, animal);
            stat.setString(2, tamanio);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) animalID = rs.getInt("animal_id");
            else {
                /*Todavía no existe tabla en 'animal' que corresponda a los atributos de la mascota que se quiere publicar.
                Se crea la entrada. */
                query = "INSERT INTO animal (animal_nombre, animal_tamanio) VALUES (?, ?)";
                stat = con.prepareStatement(query);
                stat.setString(1, animal);
                stat.setString(2, tamanio);
                stat.executeUpdate();

                //Se busca el ID de la entrada recién creada.
                query = "SELECT LAST_INSERT_ID()";
                stat = con.prepareStatement(query);
                rs = stat.executeQuery();
                rs.next();
                animalID = rs.getInt(1);
            }
            
        } catch (SQLException sqe) { System.out.println(sqe + "en getAnimalID"); }
        return animalID;
    }
    
    //Verifica que no exista ni usuario ni refugio con el nombre de usuario dado.
    public static boolean existeNombre (String username) {
        return existeUsuario(username) || existeRefugio(username);
    }

    public static boolean existeUsuario (String username) {
        try {
            ResultSet rs;
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
            ResultSet rs;
            String query = "SELECT " + UserField.ID.field + " FROM usuario WHERE " + UserField.ID.field + " = ?";
            PreparedStatement stat = con.prepareStatement(query);
            stat.setInt(1, ID);
            rs = stat.executeQuery();

            return rs.next();
        } catch (SQLException se) { System.out.println(se + "en existeUsuario"); }
        return false; 
    }

    public static boolean existeRefugio (String username) {
        ResultSet rs;
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

    public enum UserField {
        ID ("usuario_id"),
        NAME("usuario_nombre"),
        PASS("usuario_contrasenia"),
        EMAIL("usuario_email"),
        NUM("");

        public final String field;

        UserField (String field) {
            this.field = field;
        }
        
    }
}
