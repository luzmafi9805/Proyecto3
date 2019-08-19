package Company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Archivos {
    
    public String LeerTxt(String direccion) throws FileNotFoundException {
        String texto = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            StringBuilder temp= new StringBuilder();
            String bfRead;
            while ((bfRead = bf.readLine())!=null){
                temp.append(bfRead);

            }
            texto= temp.toString();
        }catch (Exception e){
            System.out.println("No se encontro archivo");

        }

        return texto;
    }
    
}
