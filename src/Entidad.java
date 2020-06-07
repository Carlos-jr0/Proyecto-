
import java.util.Stack;


public class Entidad {
	 private String nombre;
	 /*Clase stack llamada tipo lifo, ultiimo en entrar primero en salir, sentencia para 
	 verificar se es equilibrado o no en simbolos*/
	    private Stack<Atributo> atributos = new Stack<Atributo>();
	    
	    public String getNombre() {
	        return nombre;
	    }
	    
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    
	    /* Devolvemos los atributos */
	    public Stack getAtributos() {
	        return atributos;
	    }
	    
	    /* Agregamos un atributo en la entidad */
	    public void addAtributo(String nombre, int tipo, int longitud) {
	        this.setAtributo(new Atributo(nombre, tipo, longitud));
	    }
	    
	    /* Seteamos un atributo en la entidad */
	    private void setAtributo(Atributo attr) {
	        this.atributos.add(attr);
	    }
	    
	    /* Obtenemos un string de todos los atributos para guardarlos en los archivos .txt */
	    public String getAtributosSql() {
	        String resultado = "";
	        Stack<Atributo> mostrarAtrs = atributos;
	        
	        while (!mostrarAtrs.empty()) {//comprueba si esta vacia
	            Atributo atr = mostrarAtrs.pop();
	            
	            resultado += atr.getNombre() + "-" + atr.getTipo() + "-" + atr.getLongitud() + ".";
	        }
	        
	        return resultado;
	    }	

}
