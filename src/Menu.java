import java.io.File;
import java.io.IOException;


public class Menu {
	
	 /* Obtenemos la dirección actual del proyecto */
	private static String ACTUAL_DIR = System.getProperty("user.dir");
	// Ruta para los archivos de entidades
	private static String ENTIDAD_DIR = ACTUAL_DIR + File.separator + "entidades" + File.separator;
	// Ruta para los registros por entidad
	 private static String REGISTROS_DIR = ACTUAL_DIR + File.separator + "registros" + File.separator;
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BaseDato db = new BaseDato(ENTIDAD_DIR);
        db.checkEntidades();
        
        Entidad en = new Entidad();
        en.setNombre("alumno");
        en.addAtributo("nombre", Atributo.TYPE_STRING, 100);
        en.addAtributo("fecha_nacimiento", Atributo.TYPE_DATE, 0);
        en.addAtributo("carne", Atributo.TYPE_LONG, 0);
        en.addAtributo("telefono", Atributo.TYPE_STRING, 15);
        en.addAtributo("peso", Atributo.TYPE_DOUBLE, 0);
        db.addEntidad(en);
        
        Entidad curso = new Entidad();
        curso.setNombre("curso");
        curso.addAtributo("nombre", Atributo.TYPE_STRING, 100);
        db.addEntidad(curso);
        
        db.mostrarEntidades();
        db.continuar();
        
        System.out.println("LOS ATRIBUTOS .DAT SERAN CREADOS Y GUARDADOS EN LA DIRECCION:");
        BaseDato manager = new BaseDato(REGISTROS_DIR);
        manager.setToPersist(en);
        
        if (manager.addRegistro("Pedro, 15-02-1990, 7690183135, 45568978, 185.2")) {
            manager.addRegistro("Juan, 15-02-1991, 7690183135, 45568978, 185.2");
            manager.addRegistro("Solo, 15-02-1989, 7690183135, 45568978, 185.2");
            manager.removeRegistro("Juan, 15-02-1991, 7690183135, 45568978, 185.2");
            manager.continuar();
        } else {
            System.out.println("Hubo un error al agregar el registro.");
        }
        
     // Para guardador los cambios de la BD siempre que querras agregar una entidad o guardar registros, despues de agregarlos corres el continuar() de la clase BD.
        /* Para poder guardar registros de una entidad tenes que crear una nueva instancia de BD y convertir esa instancia en un manager de la entidad para hacer eso solo tenes que 
        setearle un entidad a la BD (con el método setToPersist()) */
    }
    
    }


