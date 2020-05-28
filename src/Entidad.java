import java.util.List;


public class Entidad {
	private String nombre;
	private List<Atributo> atributos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Atributo> getAtributos() {
		return atributos;
	}
	public void setAtributos(Atributo atr) {
		this.atributos.add(atr);
	}
	
	

}
