package ListaArbol;


public class Arbol {
    
    private NodoArbol raiz;

	public NodoArbol getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoArbol raiz) {
		this.raiz = raiz;
	}
	
	public Arbol(){
		raiz=null;
	}
	
	public Arbol(String s,Integer suma){
		raiz=new NodoArbol(s,suma);
	}
	
	public void AgregarHojas(NodoArbol izq,NodoArbol der){
		raiz.setIzq(izq);
		raiz.setDer(der);
	}

    public NodoArbol getRaiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
