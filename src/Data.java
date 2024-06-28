import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {
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

    public List<Ciudad> getDataCiudades(String ruta){
        List<String> datos = getDataFromTxt(ruta);
        List<Ciudad> CIUDADES = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            String[] destokenizado = datos.get(i).split("-");
            String cod = destokenizado[0];
            String nombre = destokenizado[1];
            //System.out.println(cod + " " + i + " " + nombre);
            CIUDADES.add(new Ciudad(cod, i, nombre));
           // System.out.println("la cantidad de ciudades es" +  CIUDADES.size());
        }
        return CIUDADES;
    }

    public void armarGrafo(String ruta){
        int NODOS = 24;
        Grafo_Matriz_Adyacencia grafo = new Grafo_Matriz_Adyacencia(NODOS);
        List<String> datos = getDataFromTxt(ruta);

        for (int i = 0; i < datos.size(); i++) {
            String[] destokenizado = datos.get(i).split("-");
            String codOrigen = destokenizado[0];
            String codDestino = destokenizado[1];
            float lowRange = Float.parseFloat(destokenizado[2]);
            float highRange = Float.parseFloat(destokenizado[3]);
            
            int precio = (int)calcPrecio(lowRange,highRange);
            grafo.agregarArista(codOrigen, codDestino,precio);
            
        }

        grafo.printGrafo();

    }

    private float calcPrecio(float low, float high){
        float l = low;
        float h = high;
        //TODO hacerlo con algo random o algo asi que no sea solo el promedio
        return ((l + h)/2); 
    }
    public void updateListaCiudades() {
        for (String trayecto : recorridos) {
            String[] destokenizado = trayecto.split("-");
            if (!ciudades.contains(destokenizado[0])) {
                ciudades.add(destokenizado[0]);
               // System.out.println("entre");
            }
            if (!ciudades.contains(destokenizado[1])) {
                ciudades.add(destokenizado[1]);
                //System.out.println("entre2");

            }
        }
    }

    public List<String> getDataFromTxt(String ruta) {
        try {
            return muestraContenido(ruta);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<String> muestraContenido(String archivo) throws FileNotFoundException, IOException {
        List<String> txtData = new ArrayList<>();
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            txtData.add(cadena);
            //recorridos.add(cadena);
        }
        b.close();
        return txtData;
    }
}
