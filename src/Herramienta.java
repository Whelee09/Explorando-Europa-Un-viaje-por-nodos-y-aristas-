import java.util.ArrayList;
import java.util.List;

public class Herramienta {
    private int NODOS = 24;// arreglar esto para q sea dinamico
    private final int INF = Integer.MAX_VALUE;
    // private final String ruta = "src\\trayectos.txt";
    public Grafo_Matriz_Adyacencia graph;
    private int mPesosNueva[][] = new int[NODOS][NODOS];
    Data d;

    Herramienta() {
        d = new Data();
        graph = new Grafo_Matriz_Adyacencia(NODOS);
        initMPesosNueva();
    }

    public void construirGrafo(int eleccion) {
        Data d = new Data();
        NODOS = 24;
        List<String> datos = d.getDataFromTxt("src//trayectos.txt");

        for (int i = 0; i < datos.size(); i++) {
            String[] destokenizado = datos.get(i).split("-");
            String codOrigen = destokenizado[0];
            String codDestino = destokenizado[1];
            float lowRange = Float.parseFloat(destokenizado[2]);
            float highRange = Float.parseFloat(destokenizado[3]);

            int precio = (int) calcPrecio(lowRange, highRange);
            graph.agregarArista(codOrigen, codDestino, precio);
            System.out.println("estoy agg  " + codOrigen + " " + codDestino + " " + precio);

        }

        graph.printGrafo();
        // graph.getPrim();
    }

    private float calcPrecio(float low, float high) {
        float l = low;
        float h = high;
        // TODO hacerlo con algo random o algo asi que no sea solo el promedio
        return ((l + h) / 2);
    }

    public void calcularRuta() {
        // graph.dijkstra(0, 6);

        int nodoOrigen = 0;// leerlo desde un txt?
        int costoParaVmc = Integer.MAX_VALUE;// csoto para vecino mas cercano
        List<Ciudad> citiesToVisit = new ArrayList<>();
        // int cantCiudadesAVisitar = (citiesToVisit.size() - 1);// ???
        citiesToVisit = d.getCiudadesAVisitar("src//ciudadesAVisitar.txt");
        int idxId = 0;

        do {
            nodoOrigen = citiesToVisit.get(idxId).getId();// agarra la priera ciudad
            String mensaje = "de: " + citiesToVisit.get(idxId).getNombre() + " escogi a ";
            citiesToVisit.remove(idxId);// la eliminamos de la lista
            printCities(citiesToVisit);
            for (int i = 0; i < citiesToVisit.size(); i++) {// itera sobre el resto buscando la mas cercan

                int nodoDestino = citiesToVisit.get(i).getId();
                int pesoDeDijkstra = graph.dijkstra(nodoOrigen, nodoDestino);

                if (pesoDeDijkstra < costoParaVmc) {
                    costoParaVmc = pesoDeDijkstra;
                    idxId = i;// guardamos el idxId de la ciudad mas proxima
                    System.out.println("el idx guardado es " + idxId + " del if");
                }
            }
            System.out.println("El idxd es " + idxId);
            System.out.println("el size es" + citiesToVisit.size());
            //if (citiesToVisit.size() != 0) {
                System.out.println(mensaje + citiesToVisit.get(idxId).getNombre());
            // }else{
            //     System.out.println(mensaje);
            // }
        } while (citiesToVisit.size()>1);
    }

    private void printCities(List<Ciudad> ciudades) {
        for (int i = 0; i < ciudades.size(); i++) {
            System.out.print(" -> " + ciudades.get(i).getNombre());
        }
        System.out.println("\n");
    }

    private void initMPesosNueva() {
        for (int i = 0; i < mPesosNueva.length; i++) {
            for (int j = 0; j < mPesosNueva.length; j++) {
                mPesosNueva[i][j] = INF;
            }
        }
    }

    public void Fastest() {
    }

}
