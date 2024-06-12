public class Herramienta {
    private int NODOS;
    public Grafo_Matriz_Adyacencia grafo;

    Herramienta() {
        
    }

    public void construirGrafo(){
        Data d = new Data();
        d.readFileData("src\\trenes.txt");
        d.updateListaCiudades();
        NODOS = 26;//d.getCiudades().size();//TODO arreglar esto, hay error porque aun los 25 ciudades/destinos no estan en
        //el txt pero si en la tabla, tal vez buscamos una forma de agregarlo a la tabla cada vez que la leamos
        
        System.out.println("La cantidad de nods es:" + NODOS);
        grafo = new Grafo_Matriz_Adyacencia(NODOS);
        System.out.println("EL valor del limite es" + d.recorridos.size());

        for (int i = 0; i < d.recorridos.size(); i++) {
            String[] destokenizado = d.recorridos.get(i).split("-");
            int costo = Integer.parseInt(destokenizado[2]);//TODO hacerlo por rangos y randoms
            grafo.agregarArista(destokenizado[0], destokenizado[1],costo );
            grafo.agregarArista( destokenizado[1],destokenizado[0], costo);
            System.out.println("pues sigo por aqui");
        }
    }

    public void calcularRuta(boolean Fastest){
        if(Fastest){
            Fastest();
        }
    }

    public void Fastest(){

        int origen = grafo.table.get(Data.abrevOrigen);
        int destino = grafo.table.get(Data.abrevDestino);
        grafo.dijkstra(origen, destino);
    }

}
