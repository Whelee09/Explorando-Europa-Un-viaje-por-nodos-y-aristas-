import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static String abrevOrigen = "";
    public static String abrevDestino = "";
    public List<String> recorridos = new ArrayList<>();
    public List<String> ciudades = new ArrayList<>();

    public Data() {
        // lee todo el archivo y lo gaurda en, un string??
        // readFileData()
    }

    public void getDataFlightToEurope(String[] destinos) {
        // obtiene los datos de vuelos a europa
        System.out.println("HI");
    }

    public void getDataTrainInEurope(String[] destinos) {
        // obtiene los datos de trenes intereuropa y basiceamente tambien creamos un
        // grafo de trenes??

        System.out.println("HI");
    }

    public void getDataFlightInEurope(String[] destinos) {
        System.out.println("HI");
    }

    public void calcEconomicAsPosible() {
        // cuerpo
    }

    public void calcFastestAsPossible() {
        // cuerpo
    }

    public void calcConfortableAsPossible() {
        // cuerpo
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public void updateListaCiudades() {
        for (String trayecto : recorridos) {
            String[] destokenizado = trayecto.split("-");
            if (!ciudades.contains(destokenizado[0])) {
                ciudades.add(destokenizado[0]);
                System.out.println("entre");
            }
            if (!ciudades.contains(destokenizado[1])) {
                ciudades.add(destokenizado[1]);
                System.out.println("entre2");

            }
        }
    }

    public void readFileData(String ruta) {
        // "./main.txt"
        try {
            //String rutaActual = System.getProperty("user.dir");
            //System.out.println("Ruta actual del programaaa: " + rutaActual);
            muestraContenido(ruta );
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            recorridos.add(cadena);
            System.out.println(cadena);
        }
        b.close();
    }
}
