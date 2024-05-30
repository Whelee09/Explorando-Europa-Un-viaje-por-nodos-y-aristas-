import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Main{
    public static void main(String[] args) {
       // System.out.println("hello world");


       // Tour tour = new Tour();

       try {
        muestraContenido("./main.txt");
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
    }

        public static void muestraContenido(String archivo) throws FileNotFoundException, IOException { 
    	String cadena; 
        FileReader f = new FileReader(archivo); 
        BufferedReader b = new BufferedReader(f); 
        while((cadena = b.readLine())!=null) { 
        	System.out.println(cadena); 
            //TODO
            //que no solo las lea sino que tambien las gaurde para tratar lso datos
        } 
        b.close(); 
	}
}