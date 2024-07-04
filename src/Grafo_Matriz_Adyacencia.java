
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Grafo_Matriz_Adyacencia {

    private int pesoCalculadoConDijkstra = -1;

    private int V; //numero de vertices
    private int A; //numero de aristas
    private int[][] mAdyac;
    public int[][] mPesos;//para poder accederlo desde herrameintas luego tal vez le ponemos un getter
    private int[][] mRecorridos;
    int[][] mAuxPesos;
    private int INF = 9999;//Integer.MAX_VALUE;
    Grafo_Lista_Adyacencia l;
    HashMap<String, Integer> table = new HashMap<>();
    HashMap<String, String> table1 = new HashMap<>();
    Ciudad objCiudades;
    List<Ciudad> cities = new ArrayList<>();
    List<Ciudad> debug = new LinkedList<>();


    public Grafo_Matriz_Adyacencia(int nodos) {
        Data d = new Data();
        cities = d.getDataCiudades("src//ciudades.txt");
        this.V = cities.size();//nodos;
        this.A = 0;
        this.mAdyac = new int[nodos][nodos];
        this.mPesos = new int[nodos][nodos];
        llenarMatrizPesos();
        l = new Grafo_Lista_Adyacencia(V);
    }

    private void llenarMatrizPesos() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    mPesos[i][j] = 0;
                } else {
                    mPesos[i][j] = INF;
                }
            }
        }
    }

    public int dijkstra(int origen, int destino) {
        int[] costoMinimo = new int[V];
        Arrays.fill(costoMinimo, INF);
        boolean[] visited = new boolean[V];
        int[] antecesores = new int[V];
        Arrays.fill(antecesores, -1); // Inicializar todos los antecesores como -1
        costoMinimo[origen] = 0;

        while (true) {
            int minIdx = -1;
            int minCosto = INF;

            // Encontrar el nodo no visitado con el costo mínimo
            for (int i = 0; i < V; i++) {
                if (!visited[i] && costoMinimo[i] < minCosto) {
                    minIdx = i;
                    minCosto = costoMinimo[i];
                }
            }

            if (minIdx == -1) {
                break; // 
            }
            visited[minIdx] = true;
            
            // Actualiza los costos mínimos y los antecesores
            for (int i = 0; i < V; i++) {
                if (!visited[i] && mAdyac[minIdx][i] == 1) {
                    int nuevoCosto = costoMinimo[minIdx] + mPesos[minIdx][i];
                    if (nuevoCosto < costoMinimo[i]) {
                        costoMinimo[i] = nuevoCosto;
                        antecesores[i] = minIdx;
                    }
                }
            }

            if (minIdx == destino) {
                break; // llegamos al destino :D
            }
        }
        System.out.println("Distancia minima desde " + cities.get(origen).getNombre() + " hasta " + cities.get(destino).getNombre()  +  ", el costo minimo es: " + costoMinimo[destino]);
        return costoMinimo[destino];
        //imprimirCamino(origen, destino, antecesores);
    }

    public int getMinIdx(boolean[] visited, int[] costoMinimo, int origen, int acum) {
        int minIndex = -1;
        int min = INF;

        for (int i = 0; i < V; i++) {
            if (!visited[i] && ((mPesos[origen][i] + acum) < costoMinimo[i]) && mAdyac[origen][i] == 1) {
                costoMinimo[i] = mPesos[origen][i] + acum;
                if (mPesos[origen][i] + acum < min) {
                    min = costoMinimo[i];
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public void imprimirCamino(int origen, int destino, int[] antecesores) {
        Stack<Integer> camino = new Stack<>();
        int actual = destino;

        while (actual != -1) {
            camino.push(actual);
            actual = antecesores[actual];
        }

        System.out.print("Camino desde " + cities.get(origen).getNombre() + " hasta " + cities.get(destino).getNombre() + ": ");
        while (!camino.isEmpty()) {
            System.out.print(cities.get(camino.peek()).getNombre());
            if (/*!camino.isEmpty()*/camino.size()>1 ) {
                System.out.print("  $ " +(mPesos[camino.pop()][camino.peek()]));
                System.out.print(" -> ");
            }else{
                camino.pop();
            }
        }
        System.out.println();
    }

    public void initMAuxPesosYMRec() {
        this.mRecorridos = new int[V][V];
        this.mAuxPesos = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                mAuxPesos[i][j] = mPesos[i][j];
                if (i != j) {
                    mRecorridos[i][j] = j;
                } else {
                    mRecorridos[i][j] = 0;
                }
            }
        }
    }
    
    public void printMRecorrido() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    System.out.print(table.get(mRecorridos[i][j]) + "\t");
                } else {
                    System.out.print("- \t");
                }
            }
            System.out.println("");
        }
    }

    public int findId(String a){
        //System.out.println("entre al find ID");
        boolean coincidencia = false;
        int i=0;
        int index;
        do{ 
            if (cities.get(i).getCod().equals(a)) {
               // System.out.println("match");
                coincidencia = true;
                index = i;
                return index;
            }
            i++;
        }while(i<cities.size() && (coincidencia == false));
        //System.out.println("di " + i + " vueltas");
        //no deberia llegar aqui
        return -1;
    }


    public void agregarArista(String a, String b, int peso) {
        int u = findId(a);
        int v = findId(b);
        System.out.println("u es: " + u + " y v es:" + v);

        l.agregarArista(u, v);

        if (u != v) {
            mAdyac[u][v] = 1;
            mAdyac[v][u] = 1;
            mPesos[u][v] = peso;
            mPesos[v][u] = peso;
            A++;
        }
    }

    public void printListaListas() {
        l.printGrafo();
    }


    public void printGrafo() {
        for (int i = 0; i < V; i++) {
            System.out.println("Fila " + i + ":");
            for (int j = 0; j < V; j++) {
                System.out.print( mAdyac[i][j] + " ");
            }
            System.out.println("");
        }
    }


    public void printMatrizPeso() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(mAuxPesos[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    public boolean hayCiclo(LinkedList<Integer>[] adj, int fila, int col, boolean[] visitado) {
        visitado[fila] = true;

        for (Integer siguiente : adj[fila]) {
            if (!visitado[siguiente]) {
                if (hayCiclo(adj, siguiente, fila, visitado)) {
                    return true;
                }
            } else if (siguiente != col) {
                return true;
            }
        }

        return false;
    }

    public int[] findMinArista() {
        int min = INF;
        int idxFila = -1;
        int idxCol = -1;
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (/*i != j &&*/mAuxPesos[i][j] < min) {//lo comentado es para solo verificar la triangulasr superior de la matriz, hbaria que hacer otras cosas para que sea bidireccinoal
                    min = mAuxPesos[i][j];
                    idxFila = i;
                    idxCol = j;
                }
            }
        }
        mAuxPesos[idxFila][idxCol] = INF;
        mAuxPesos[idxCol][idxFila] = INF;

        return new int[]{min, idxFila, idxCol};
    }
    public int getPesoDijk(){
        return pesoCalculadoConDijkstra;
    }
    private void setPesoDijk(int peso){
         pesoCalculadoConDijkstra = peso;
    }
}



