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
    private Entidad entidadActual; // Creamos una variable entidad para crear registros. Si el DB tiene una entidad se convierte en manager de esa entidad
    private Stack<String> archivos = new Stack<String>();
    
    /* Inicializamos la BD con una ruta para guardar las entidades */
    BaseDato(String path) {
        this.path = path;
    }
    
    // Devuelve la entidad actual de la BD, si la BD es un manager.
    public Entidad getToPersist() {
        return entidadActual;
    }
    
 // Seteamos una entidad actual para convertir el BD en un manager de esa entidad.
    public void setToPersist(Entidad en) {
        this.entidadActual = en;
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
    public void mostrarEntidades() {
        Stack<Entidad> mostrarEntidades = entidades;
        
        while(!mostrarEntidades.empty()) {//si mostrar entidades esta vacia
            Entidad en = mostrarEntidades.pop();
            
            if (DEBUG)
                System.out.println("Entidad: " + en.getNombre());
        }
    }
    
    /* Verificamos si existen archivos de entidad, si existen agregamos las entidades a la BD */
    public void checkEntidades() throws FileNotFoundException, IOException {
        File carpeta = new File(path);
        File[] listaDeArchivos = carpeta.listFiles();
        
        if (listaDeArchivos.length > 0) {
        for (File file : listaDeArchivos) {
            if (file.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(path + file.getName()));
                Entidad en = new Entidad();
                String linea = br.readLine();
                String[] separado = linea.split(",");//linea dividida por ","
                String[] atrs = separado[0].split(".");
                
                en.setNombre(separado[0]);
                
                for (String attr : atrs) {
                    String[] atr = attr.split("-");
                    en.addAtributo(atr[0], Integer.valueOf(atr[1]), Integer.valueOf(atr[2]));
                }
                
                this.addEntidad(en);
            }
        }
    }
  }      
    
    /* Revisamos si existen archivos de la entidad actual y los agregamos a la pila de archivos */
    public void checkArchivos() throws FileNotFoundException, IOException {
        File archivo = new File(path + entidadActual.getNombre() + ".dat");
        
        if (archivo.isFile()) {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = br.readLine();
            
            archivos.add(linea);
        }
    }
    
    /* Agregamos un registro en la pila archivos */
    public boolean addRegistro(String attrs) { // El parametro es un string con los atributos separados por coma.
        String[] separado = attrs.split(",");
        
        /* Si los atributos no sobrepasan el numero de atributos de la entidad lo guardamos */
        if (separado.length <= entidadActual.getAtributos().size()) {
            archivos.add(attrs);
            return true;
        }
        
        return false;
    }
    
    /* Removemos un registro de la pila archivos */
    public boolean removeRegistro(String attrs) { // El parametro es un string con los atributos separados por coma.
        String[] separado = attrs.split(",");
        
        /* Si los atributos no sobrepasan el numero de atributos de la entidad lo buscamos */
        if (separado.length <= entidadActual.getAtributos().size()) {
            Stack<String> borrar = archivos;
            Stack<String> nuevoArchivo = new Stack<String>(); // Creamos una nueva pila.
            
            while(!borrar.empty()) {
                String registro = borrar.pop();
                
                if (!registro.equals(attrs)) { // Si el registro es indiferente al que debe ser eliminado lo agregamos a la nueva pila
                    nuevoArchivo.add(registro);
                }
                
            }
            
            archivos = nuevoArchivo; // Los archivos se cambian por la nueva pila que contiene los registros sin el que debia ser eliminado.
            return true;
        }
        
        return false;
    }
    
    /* Guardamos los datos en los archivos */
    public void continuar() throws IOException {
        if (entidadActual != null) { // Si la BD es manager guardos los registros en el archivo .dat
            Stack<String> aContinuar = archivos;
            
            /* Vaciamos el archivo de registros para agregarlos nuevamente */
            String nombreArchivo = path + entidadActual.getNombre() + ".dat";
            BufferedWriter bf = new BufferedWriter(new FileWriter(nombreArchivo));
            bf.write("");
            bf.close();
            
            while(!aContinuar.empty()) {
                String registro = aContinuar.pop();

                if (DEBUG)
                    System.out.println("Archivo: " + nombreArchivo);

                
                bf = new BufferedWriter(new FileWriter(nombreArchivo, true));

                bf.write(registro);
                bf.newLine();
                bf.close();
            }
            
        } else { // Si la BD no es manager agregamos las entidades nuevas a la carpeta.
            Stack<Entidad> mostrarEntidades = entidades;
        
            while(!mostrarEntidades.empty()) {
                Entidad en = mostrarEntidades.pop();
                String nombreArchivo = path + en.getNombre() + ".txt";

                if (DEBUG)
                    System.out.println("Archivo: " + nombreArchivo);

                BufferedWriter bf = new BufferedWriter(new FileWriter(nombreArchivo));

                bf.write(en.getNombre() + "," + en.getAtributosSql());
                bf.close();
            }
        }
    }
}
