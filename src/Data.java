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
            System.out.println("el nombre de la ciudad x es " + nombre );
            CIUDADES.add(new Ciudad(cod, i, nombre));
        }
        System.out.println("por ejemplo retonro la ciudad "   + CIUDADES.getFirst());
        return CIUDADES;
    }

    public void updateListaCiudades() {
        for (String trayecto : recorridos) {
            String[] destokenizado = trayecto.split("-");
            if (!ciudades.contains(destokenizado[0])) {
                ciudades.add(destokenizado[0]);
            }
            if (!ciudades.contains(destokenizado[1])) {
                ciudades.add(destokenizado[1]);
            }
        }
    }

    public List<Ciudad> getCiudadesAVisitar(String ruta) {
        List<String> datos = getDataFromTxt(ruta);
        List<Ciudad> citiesToVisit = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            String[] destokenizado = datos.get(i).split("-");
            int id = Integer.parseInt(destokenizado[0]);
            String nombre = destokenizado[1];
            citiesToVisit.add(new Ciudad(null, id, nombre));
        }
        return citiesToVisit;
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
