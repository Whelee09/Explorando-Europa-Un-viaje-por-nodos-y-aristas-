
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Grafo_Matriz_Adyacencia {

    private int V; //numero de vertices
    private int A; //numero de aristas
    private int[][] mAdyac;
    private int[][] mPesos;
    private int[][] mRecorridos;
    int[][] mAuxPesos;
    private int INF;
    Grafo_Lista_Adyacencia l;
    HashMap<String, Integer> table = new HashMap<>();
    HashMap<String, String> table1 = new HashMap<>();

    public Grafo_Matriz_Adyacencia(int nodos) {
        this.V = nodos;
        this.A = 0;
        INF = Integer.MAX_VALUE;
        this.mAdyac = new int[nodos][nodos];
        this.mPesos = new int[nodos][nodos];
        llenarMatrizPesos();
        //l = new Grafo_Lista_Adyacencia(V);
        table.put( "MAD",0);
        table.put( "BCN",1);
        table.put( "LHR",2);
        table.put( "CDG",3);
        table.put( "FCO",4);
        table.put( "AMS",5);
        table.put( "BER",6);
        table.put( "VIE",7);
        table.put( "LED",8);
        table.put( "LIS",9);
        table.put( "BOR",10);
        table.put( "VLC",11);
        table.put( "MPL",12);
        table.put( "PAR",13);
        table.put( "MCO",14);
        table.put( "LYS",15);
        table.put( "MCO",16);
        table.put( "BRN",17);
        table.put( "LON",18);
        table.put( "LUX",19);
        table.put( "FRA",20);
        table.put( "BER",21);
        table.put( "BRU",22);
        table.put( "MUC",23);
        table.put( "VIE",24);
        table.put( "PRG",25);


        table1.put( "MAD","Madrid");
        table1.put( "BCN","Barcelona");
        table1.put( "LHR","Londres");
        table1.put( "CDG","París");
        table1.put( "FCO","Roma");
        table1.put( "AMS","Ámsterdam");
        table1.put( "BER","Berlín");
        table1.put( "VIE","Viena");
        table1.put( "LED","San Petersburgo");
        table1.put( "LIS","Lisboa");
        //TODO terminar esta tabla
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

    public void dijkstra(int origen, int destino) {
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

        // Imprimir resultados
        System.out.println("Distancia minima desde" + table.get(origen) + "hasta" + table.get(destino) +  " \n y el costo minimo es: " + costoMinimo[destino]);
        imprimirCamino(origen, destino, antecesores);
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

        System.out.print("Camino desde " + table.get(origen) + " hasta " + table.get(destino) + ": ");
        while (!camino.isEmpty()) {
            System.out.print(table.get(camino.pop()));
            if (!camino.isEmpty()) {
                System.out.print(" -> ");
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

    public void FW() {
        initMAuxPesosYMRec();
        for (int i = 0; i < V; i++) {
            actualizarPesos(i);
        }
    }

    private void actualizarPesos(int pivote) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && mAuxPesos[pivote][i] != INF && mAuxPesos[j][pivote] != INF) {
                    int nuevaDistancia = mAuxPesos[pivote][i] + mAuxPesos[j][pivote];
                    if (nuevaDistancia < mAuxPesos[i][j]) {
                        mAuxPesos[i][j] = nuevaDistancia;
                        mRecorridos[i][j] = pivote;
                    }
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

    // public void fwGetCaminoMinBetween(String origen, String destino) {
    //     FW();
    //     ArrayList<Integer> listaR = new ArrayList<>();
    //     int origenIdx = table1.get(origen);
    //     int destinoIdx = table1.get(destino);
    //     listaR.add(origenIdx);

    //     while (true) {
    //         int siguiente = mRecorridos[origenIdx][destinoIdx];
    //         if (siguiente == INF) {
    //             break;
    //         }
    //         listaR.add(siguiente);
    //         if (siguiente == destinoIdx) {
    //             break;
    //         }
    //         origenIdx = siguiente;
    //     }

    //     System.out.println("El camino mas corto entre " + origen + " y " + destino + " es: ");
    //     int peso = 0;
    //     for (int i = 0; i < listaR.size(); i++) {
    //         System.out.print(table.get(listaR.get(i)));
    //         if (i < listaR.size() - 1) {
    //             peso += mPesos[listaR.get(i)][listaR.get(i + 1)];
    //         }
    //         if (i < listaR.size() - 1) {
    //             System.out.print("-");
    //         }
    //     }
    //     System.out.println("\nEl peso es: " + peso);
    // }

    public void agregarArista(String a, String b, int peso) {
        System.out.println("aun no me he detenido porque ORigen" + a + " des: " + b);
        int u = table.get(a);
        int v = table.get(b);

        System.out.println("u es: " + u + " y v es:" + v);

        //l.agregarArista(u, v);

        if (u != v) {
            mAdyac[u][v] = 1;
            //mAdyac[v][u] = 1;
            mPesos[u][v] = peso;
            //mPesos[v][u] = peso;
            A++;
        }
    }

    public void printListaListas() {
        l.printGrafo();
    }

    public void getPrim() {
        System.out.println("Usando prim debes unir ");
        int aristas = 0;
        Random rand = new Random();
        int fila = rand.nextInt(V);
        LinkedList<Integer> disponible = new LinkedList();
        disponible.add(fila);
        initMAuxPesosYMRec();

        LinkedList<Integer>[] adj1;
        adj1 = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj1[i] = new LinkedList<>();
        }
        int origen, destino;
        while (aristas < V - 1) {
            int[] res = findMinNext(disponible);
            origen = res[1];
            destino = res[2];
            adj1[origen].add(destino);
            adj1[destino].add(origen);

            // System.out.println("aun sin saber si es la optima xd");
            // System.out.println("peso:" + mPesos[res[1]][res[2]] + ". fila " + table.get(res[1]) + "  columna" + table.get(res[2]));
            if (!hayCiclo(adj1, origen, destino, new boolean[V])) {
                aristas++;
                //descomente esto para ver la ruta expresado en terminos de filas y columnas System.out.println("La optima es: ");
                //System.out.println("peso:" + mPesos[res[1]][res[2]] + ". fila " + table.get(res[1]) + "  columna" + table.get(res[2]));
                System.out.println(table.get(origen) + " - " + table.get(destino));
                mAuxPesos[origen][destino] = INF;
                mAuxPesos[destino][origen] = INF;
                disponible.add(destino);
                System.out.println("");
            } else {
                mAuxPesos[origen][destino] = INF;
                mAuxPesos[destino][origen] = INF;
                adj1[res[1]].removeLast();
                adj1[res[2]].removeLast();
            }
        }

    }

    private int[] findMinNext(LinkedList<Integer> disponible) {
        int min = INF;
        int idxFila = -1;
        int idxCol = -1;
        for (Integer siguiente : disponible) {
            for (int i = 0; i < V; i++) {
                if (siguiente != i && (mAuxPesos[siguiente][i] < min)) {
                    min = mAuxPesos[siguiente][i];
                    idxCol = i;
                    idxFila = siguiente;
                }
            }
        }
        //System.out.println("voy a retornar " + min + "  " + table.get(idxFila) + " " + table.get(idxCol));

        return new int[]{min, idxFila, idxCol};

    }

    public void printGrafo() {
        for (int i = 0; i < V; i++) {
            System.out.println("Fila " + i + ":");
            for (int j = 0; j < V; j++) {
                System.out.print(mAdyac[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void getKruskal() {
        LinkedList<Integer>[] adj1;
        adj1 = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj1[i] = new LinkedList<>();
        }
        initMAuxPesosYMRec();
        int aristas = 0;

        while (aristas < V - 1) {

            int[] aristaMin = findMinArista();
            int fila = aristaMin[1];
            int col = aristaMin[2];
            //System.out.println("weight:" + aristaMin[0] + ". fila " + table.get(fila) + "  columna" + table.get(col));// lo uso para debuggear 
            adj1[fila].add(col);
            adj1[col].add(fila);

            if (!hayCiclo(adj1, fila, col, new boolean[V])) {
                aristas++;
                System.out.println("peso:" + aristaMin[0] + ". fila " + table.get(fila) + "  columna" + table.get(col));
            } else {
                adj1[fila].removeLast();
                adj1[col].removeLast();
            }
            System.out.println("");
        }
//        for (int k = 0; k < V; k++) {
//            System.out.print("Fila " + table.get(k) + ": ");
//            for (int j = 0; j < adj1[k].size(); j++) {
//                if (j != 0) {
//                    System.out.print(" -> ");
//                }
//                System.out.print(table.get(adj1[k].get(j)));
//            }
//            System.out.println();
//        }

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
}

