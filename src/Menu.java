import java.io.File;
import java.io.IOException;


public class Menu {
	
	 /* Obtenemos la dirección actual del proyecto */
	private static String ACTUAL_DIR = System.getProperty("user.dir");
	// Ruta para los archivos de entidades, tenemos que creear una carpeta con el nombre entidades para crear las entidades, esto debe ser donde se guarda el proyecto...
	private static String ENTIDAD_DIR = ACTUAL_DIR + File.separator + "entidades" + File.separator;
	// Ruta para los registros por entidad, tenemos que crear una carpeta co el nombre registros para crear los atributos, esto debe ser donde se guarda el proyecto...
	 private static String REGISTROS_DIR = ACTUAL_DIR + File.separator + "registros" + File.separator;
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BaseDato db = new BaseDato(ENTIDAD_DIR);//ruta de los archivos de entidades
        db.checkEntidades();//revisamos si existe archivos de la entidad y los agregamos a la pila de archivos
        
        //creamos entidades y las guardamos en la carpeta entidad...
        Entidad en = new Entidad();//instanciamos clase Entidad
        en.setNombre("alumno");//entidad alumno
        en.addAtributo("nombre", Atributo.TYPE_STRING, 100);
        en.addAtributo("fecha_nacimiento", Atributo.TYPE_DATE, 0);
        en.addAtributo("carne", Atributo.TYPE_LONG, 0);
        en.addAtributo("telefono", Atributo.TYPE_STRING, 15);
        en.addAtributo("peso", Atributo.TYPE_DOUBLE, 0);
        db.addEntidad(en);
        
        Entidad curso = new Entidad();//instancia de Entidad
        curso.setNombre("curso");//entidad curso
        curso.addAtributo("nombre", Atributo.TYPE_STRING, 100);//atributo, tipo y longitud
        db.addEntidad(curso);
        
        //mostramos las los nombres de las entidades creadas en la carpeta
        db.mostrarEntidades();
        //guardamos los datos creados en el archivo
        db.continuar();
        
        ////////////////////////////////////////////////////////
         System.out.println("LOS ATRIBUTOS .DAT SERAN CREADOS Y GUARDADOS EN LA DIRECCION:");
        //el manager es ahora entidad alumno manager.setToPersist(en)
         //si queremos agegar atributos a la entidad curso, solo devemos cambiar manager.setToPersist(curso) y cambiar los datos del if()
        BaseDato manager = new BaseDato(REGISTROS_DIR);
        manager.setToPersist(en);
        
        if (manager.addRegistro("Jose, 15-02-1990, 7690183135, 45568978, 185.2")) {//agregar Jose
            manager.addRegistro("Juan, 15-02-1991, 7690183135, 45568978, 185.2");//agregar Juan
            manager.addRegistro("Maria, 15-02-1989, 7690183135, 45568978, 185.2");//agregar Maria
            manager.removeRegistro("Juan, 15-02-1991, 7690183135, 45568978, 185.2");//eliminar Muan
            manager.removeRegistro("Maria, 15-02-1989, 7690183135, 45568978, 185.2");//eliminar Maria
            manager.continuar();//guardar los datos y cambios.....
        } else {
            System.out.println("Hubo un error al agregar el registro.");//si no los creo en el archivo mostrara el mensaje
        }
        
     // Para guardador los cambios de la BD siempre que querramos agregar una entidad o guardar registros, despues de agregarlos correr el continuar() de la clase BD.
        /* Para poder guardar registros de una entidad tenemos que crear una nueva instancia de BD y convertir esa instancia en un manager de la entidad para hacer eso solo tenemos que 
        setearle un entidad a la BD (con el método setToPersist()) */
    }
    
    }


