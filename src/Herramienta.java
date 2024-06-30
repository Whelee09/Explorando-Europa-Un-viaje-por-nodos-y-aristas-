import java.util.List;

public class Herramienta {
    private int NODOS;
    private final String ruta = "src\\trayectos.txt";
    public Grafo_Matriz_Adyacencia graph;
    Data d;

    Herramienta() {
        d = new Data();
        graph = new Grafo_Matriz_Adyacencia(NODOS);
    }

    public void construirGrafo(int eleccion) {
        Data d = new Data();
        NODOS = 24;
        List<String> datos = d.getDataFromTxt(ruta);

        for (int i = 0; i < datos.size(); i++) {
            String[] destokenizado = datos.get(i).split("-");
            String codOrigen = destokenizado[0];
            String codDestino = destokenizado[1];
            float lowRange = Float.parseFloat(destokenizado[2]);
            float highRange = Float.parseFloat(destokenizado[3]);

            int precio = (int) calcPrecio(lowRange, highRange);
            graph.agregarArista(codOrigen, codDestino, precio);

        }

        graph.printGrafo();

        //graph.getPrim();
    }


    private float calcPrecio(float low, float high){
        float l = low;
        float h = high;
        //TODO hacerlo con algo random o algo asi que no sea solo el promedio
        return ((l + h)/2); 
    }
    public void calcularRuta(boolean Fastest) {
        // if(Fastest){
        // Fastest();
        // }
    }

    public void Fastest() {
    }

}
