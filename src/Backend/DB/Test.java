package Backend.DB;
import Backend.Models.Opciones;
import Backend.Models.Post;
import Backend.Models.Refugio;
import Backend.Models.Usuario;
import java.util.ArrayList;

public class Test {
    public static void main (String args[]) {
        DB db = new DB();
        eliminar();
    }
    private static void crearUsuario() {
        Usuario user = new Usuario();
        user.setNombre("catadelia");
        user.setContraseña("luminosito");
        user.setNumero_contacto("1130697409");

        DB.registerUsuario(user);
        
    }

    private static void getUsuario() {
        Usuario user = DB.getUsuario("catadelia");
        System.out.println(user.getNombre());
    }

    private static void createRefugio() {
        Refugio ref = new Refugio();
        ref.setNombre("refugio32");
        ref.setContraseña("contraseña");
        ref.setDireccion("gorriti,3647,PB B");
        ref.setNumero_contacto("1111111111");
        DB.crearRefugio(ref);

    }
    private static void getRefugio() {
        Refugio ref = DB.getRefugio("refugio32");
        System.out.println(ref.getDireccion());
    }

    private static void createPost() {
        Post post = new Post();
        post.setTitulo("Morgana");
        post.setEdad("3 años");
        post.setTamaño("Pequeño");
        post.setTipoMascota("Gato");
        Opciones verificacion = new Opciones(true, false, false, false);
        post.setVerificacion(verificacion);
        DB.publicarPost(post, 1);
    }

    public static void actualizarPost() {
        Post post = DB.getPost(1);
        post.setTitulo("Ivan");
        DB.updatePost(post);
    }

    public static void getByRefugioID() {
        ArrayList<Post> posts = DB.filtrarPost(1);
        for (Post i : posts) System.out.println(i.getTitulo());
    }
    public static void eliminar() {
        DB.eliminarPost(1);
    }

}
