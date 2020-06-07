
public class Atributo {
	private String nombre;
	private int tipo;
	private int longitud;
	 // Seteamos los tipos de dato que usara el atributo,permite el acceso a los metodos y variables
	public final static int TYPE_INT=1;
	public final static int TYPE_LONG=2;
	public final static int TYPE_STRING=3;
	public final static int TYPE_DOUBLE=4;
	public final static int TYPE_FLOAT=5;
	public final static int TYPE_DATE=6;
	public final static int TYPE_CHAR=7;
	
	 /* Inicializamos el atributo con un nombre, un tipo y un tamaño si aplica */
    public Atributo(String nnombre, int tipo, int longitud) {
        this.nombre = nombre;
        this.tipo = tipo;
        
        /* Si aplica un tamaño lo agregamos */
        if (this.longitudPermitida()) {
            this.longitud = longitud;
        }
    }
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	 /* Si el tipo es string se permite un tamaño */
	public boolean longitudPermitida(){
		return tipo==TYPE_STRING;
	}

}
