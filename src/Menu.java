import java.io.File;
import java.io.IOException;


public class Menu {
	private static String CURRENT_DIR = System.getProperty("user.dir");
    private static String ENTITIES_DIR = CURRENT_DIR + File.separator + "entidades" + File.separator;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BaseDato db = new BaseDato(ENTITIES_DIR);
        //db.checkEntidades();
        
        Entidad en = new Entidad();
        en.setNombre("alumno");
        en.addAtributo("nombre", Atributo.TYPE_STRING, 100);
        en.addAtributo("fecha_nacimiento", Atributo.TYPE_DATE, 0);
        en.addAtributo("carne", Atributo.TYPE_LONG, 0);
        en.addAtributo("telefono", Atributo.TYPE_STRING, 15);
        en.addAtributo("peso", Atributo.TYPE_DOUBLE, 0);
        db.addEntidad(en);
        
        en = new Entidad();
        en.setNombre("curso");
        en.addAtributo("nombre", Atributo.TYPE_STRING, 100);
        db.addEntidad(en);
        
        db.mostrarEntidad();
        db.archivoEntidad();
    }

}
