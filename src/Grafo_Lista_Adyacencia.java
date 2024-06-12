
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Grafo_Lista_Adyacencia {

    private int V; //numero de vertices
    private int A; //numero de aristas
    private LinkedList<Integer>[] adj;
    HashMap<Integer, String> table = new HashMap<>();
    // private LinkedList<LinkedList<Integer>> adj2;

    public Grafo_Lista_Adyacencia(int nodos) {
        this.V = nodos;
        this.A = 0;
        this.adj = new LinkedList[nodos];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
        table.put(0, "A");
        table.put(1, "B");
        table.put(2, "C");
        table.put(3, "D");
        table.put(4, "E");
        table.put(5, "F");
        table.put(6, "G");
        table.put(7, "H");
        table.put(8, "I");
    }
    
    public LinkedList<Integer>[] getListAdj (){
        return adj;
    }

    public void agregarArista(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
        A++;
    }

    public void printGrafo() {
        for (int i = 0; i < V; i++) {
            System.out.print("Fila " + table.get(i) + ": ");
            for (int j = 0; j < adj[i].size(); j++) {
                if (j != 0) {
                    System.out.print(" -> ");
                }
                System.out.print(table.get(adj[i].get(j)));
            }
            System.out.println();
        }
    }

    public void bfs(int s) {
        boolean[] visited = new boolean[V];

        Queue<Integer> q = new LinkedList<>();
        visited[s] = true;
        q.offer(s);

        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");

            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
    }

    public void dfs(int s) {
        boolean[] visited = new boolean[V];

        Stack<Integer> stack = new Stack();
        visited[s] = true;
        stack.push(s);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            System.out.print(u + " ");

            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    stack.push(v);
                }
            }
        }
    }

}

