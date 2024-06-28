public class Herramienta {
    private int NODOS;
    public Grafo_Matriz_Adyacencia grafo;

    Herramienta() {
        
    }

    public void construirGrafo(){

        Data d = new Data();
        d.armarGrafo("src\\trayectos.txt");
    
        
    }

    public void calcularRuta(boolean Fastest){
        if(Fastest){
            Fastest();
        }
    }

    public void Fastest(){

        // int origen = grafo.table.get(Data.abrevOrigen);
        // int destino = grafo.table.get(Data.abrevDestino);
        // grafo.dijkstra(origen, destino);
    }

}
