package Principal;

import java.io.FileNotFoundException;
import Exepciones.FueraDeRango;
import Exepciones.ListaVacia;
import Exepciones.NombreNulo;
import ListaArbol.ListaArbol;
import Listachar.Lista;
import Tabla.ListaTabla;
import Tabla.Nodo;

import javax.swing.*;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.toBinaryString;

public class CompresorDeTexto {
    
    private Scanner SC=new Scanner(System.in);

    /**
     *
     * @param mensaje
     * @return
     * @throws FueraDeRango
     * @throws ListaVacia
     * @throws IOException
     * @throws NombreNulo
     * @throws ClassNotFoundException
     */
    public String[] Comprimir(String mensaje) throws FueraDeRango, ListaVacia, IOException, NombreNulo, ClassNotFoundException {
        Lista cadena=new Lista();
        cadena.toLista(mensaje);
        cadena.recorrer();
        ListaArbol lista=new ListaArbol();
        lista.toListaArbol(cadena);
        lista.imprimir();
        lista.obtenerArbol();
        //Creamos una lista para almacenar el caracter y su direccion
        ListaTabla tabla=new ListaTabla();
        tabla=lista.obtenerHojas("", tabla, lista.getInicio().getArbol().getRaiz());

        String direcciones=tabla.unirTodo(mensaje);
        //nombre del archivo
        System.out.println("escribir el nombre del archivo a generar: ");
        String archivo = SC.next();
        if(archivo.length()==0){
            System.out.println("DEBES ESCRIBIR UN NOMBRE VALIDO!! ");
            throw new NombreNulo();
        }
        //Escibimos en el nuevo archivo la cadena de caracteres
        String codificado=escribir(direcciones,tabla,archivo+".Allan");
        //Creamos un array de string para retornar las direcciones y los caracteres
        return new String[]{direcciones,codificado};
    }
    public String Descomprimir() throws IOException,NombreNulo {
        Scanner s = new Scanner(System.in);
        System.out.println("nombre del archivo NombreDelArchivo descomprimir");
        String NombreDelArchivo = s.nextLine();

            File doc = new File(NombreDelArchivo + ".Allan");
            FileReader fr = new FileReader(doc);
            BufferedReader lector = new BufferedReader(fr);

            String apoyo2 = lector.readLine();
            Integer falta = (Integer) Integer.parseInt(String.valueOf((lector.readLine())));
            String apoyo = "";
            String mensaje = "";
            byte bit2;
            //los convertimos NombreDelArchivo binario
            for (int i = 0; i < apoyo2.length(); i++) {
                bit2 = (byte) apoyo2.charAt(i);
                apoyo = Integer.toBinaryString(bit2 & 0xFF);
                if (apoyo.length() < 8) {
                    apoyo = rellenar(apoyo);
                    if (i == (apoyo2.length() - 1))
                        apoyo = quitar(apoyo, falta);
                }
                mensaje += apoyo;
            }
            //Creamos una listaTabla para cargar los caracteres y sus direcciones
            ListaTabla tabla = new ListaTabla();
            apoyo = lector.readLine();
            while (apoyo != null) {
                tabla.agregar(apoyo.charAt(0) + "", quitar(apoyo, 1));
                apoyo = lector.readLine();
            }
            System.out.println("archivo cargado con exito");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("===================MENSAJE ORIGINAL===================");
        System.out.println(" ");
            apoyo = "";
            String original = "";
            apoyo2 = "";
            //Decodificamos direcciones para obtener el mensaje original
            for (int i = 0; i < mensaje.length(); i++) {
                apoyo = apoyo + mensaje.charAt(i);
                apoyo2 = tabla.LetraDe(apoyo);
                if (apoyo2 != null) {
                    original = original + apoyo2;
                    apoyo = "";
                    apoyo2 = "";
                }
            }
            return original;
        }



    private String escribir(String mensaje,ListaTabla tabla,String nombre) throws  IOException{
        File f=new File(nombre);
        FileWriter NuevaFila=new FileWriter(f);
        BufferedWriter ProcteccionEscritura=new BufferedWriter(NuevaFila);
        PrintWriter escritor=new PrintWriter(ProcteccionEscritura);
        String bits="";
        String codificado="";
        int falta=0;
        byte bin;
        char car;

         for(int i=0;i<mensaje.length();i++){
             if(bits.length()==8){
                 bin = (byte)Short.parseShort(bits, 2);
                 car=(char)(bin & 0xFF);
                 codificado+=car;
                 bits="";
                 i--;
            }else
                bits+=mensaje.charAt(i);
        }
        if(bits.length()!=0){
            bin = (byte)Short.parseShort(bits, 2);
            car=(char)(bin & 0xFF);
            codificado+=car;
            falta=8-bits.length();
        }
        escritor.write(codificado+"\n");
        escritor.write(falta+"\n");
        Nodo aux=tabla.getInicio();
        //ListaTabla y direccion
        while(aux!=null){
            escritor.write(aux.getDato()+aux.getUbicacion()+"\n");
            aux=aux.getSiguiente();
        }
        escritor.close();
        ProcteccionEscritura.close();
        System.out.println( "Archivo generado exitosamente!!!\n nombre: "+nombre);

        return codificado;
    }

	 private String rellenar(String cadena){
		 String nueva="";
		 for(int i=0;i<8-cadena.length();i++){
			 nueva+="0";
		 }
		 nueva+=cadena;
		 return nueva;
	 }

	 private String quitar(String cad,Integer n){
		 String nueva="";
		 for(int i=n;i<cad.length();i++)
			 nueva+=cad.charAt(i);
		 return nueva;
	 }
    
}
