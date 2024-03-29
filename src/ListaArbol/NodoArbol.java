package ListaArbol;

/**
 *
 * @author Mary Figueroa
 */
public class NodoArbol {
    
    private String dato;
	private Integer frecuencia;
	private NodoArbol izq;
	private NodoArbol der;
	
	public Integer getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public NodoArbol getIzq() {
		return izq;
	}
	public void setIzq(NodoArbol izq) {
		this.izq = izq;
	}
	public NodoArbol getDer() {
		return der;
	}
	public void setDer(NodoArbol der) {
		this.der = der;
	}

	NodoArbol(String d, Integer n){
		dato=d;
		frecuencia=n;
		izq=null;
		der=null;
	}
    
}
