import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class BaseDato {
	private Stack<Entidad> entidades = new Stack<Entidad>();
    private String path;
    private boolean DEBUG = true; // Seteamos un debug mode, si está en TRUE mostramos todos los mensajes de debug
    
    /* Inicializamos la BD con una ruta para guardar las entidades */
    BaseDato(String path) {
        this.path = path;
    }
    
    /* Agregamos una entidad */
    public void addEntidad(Entidad en) {
        entidades.add(en);
    }
    
    /* Eliminamos una entidad */
    public void removeEntidad(Entidad en) {
        entidades.remove(en);
    }
    
    /* Mostramos las entidades que existen en la BD */
    public void mostrarEntidad() {
        Stack<Entidad> mostrarEntidades = entidades;
        
        while(!mostrarEntidades.empty()) {
            Entidad en = mostrarEntidades.pop();
            
            if (DEBUG)
                System.out.println("Entidad: " + en.getNombre());
        }
    }
    
    /* Verificamos si existen archivos de entidad, si existen agregamos las entidades a la DB */
    public void checkEntidades() throws FileNotFoundException, IOException {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(path + file.getName()));
                Entidad en = new Entidad();
                String line = br.readLine();
                String[] splitted = line.split(",");//linea dividida por ","
                String[] atrs = splitted[0].split(".");
                
                en.setNombre(splitted[0]);
                
                for (String attr : atrs) {
                    String[] atr = attr.split("-");
                    en.addAtributo(atr[0], Integer.valueOf(atr[1]), Integer.valueOf(atr[2]));
                }
                
                this.addEntidad(en);
            }
        }
    }
    
    /* Creamos los archivos de entidad, un archivo .txt por cada entidad */
    public void archivoEntidad() throws IOException {
        Stack<Entidad> crearEntidades = entidades;
        
        while(!crearEntidades.empty()) {//este vacio
            Entidad en = crearEntidades.pop();
            String nombreArchivo = path + en.getNombre() + ".txt";
            
            if (DEBUG)
                System.out.println("Archivo: " + nombreArchivo);
            
            BufferedWriter bf = new BufferedWriter(new FileWriter(nombreArchivo));
            
            bf.write(en.getNombre() + "," + en.getAtributosSql());
            bf.close();
        }
    }
}
