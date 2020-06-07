
public class ArchivoDirecto {
	
	 private String path;
	 /*variable del sistema operativo para buscar los ejecutables necesarios desde
	 la linea de comandos o la ventana terminal*/
	    
	    ArchivoDirecto(String path) {
	        this.path = path;
	    }
	    //modificar la path
	    public void setPath(String path) {
	        this.path = path;
	    }
	    //devolver la path
	    public String getPath() {
	        return path;
	    }
	
}
