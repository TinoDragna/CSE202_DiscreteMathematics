
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//B1-B24
public class Rewite_ForTest1 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

    }

    static List bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        List<Vertex> visitedV = new ArrayList<>();

        q.add(v);
        v.discovered = true;
        while (!q.isEmpty()) {
            Vertex w = q.remove();
            visitedV.add(w);
            for (Vertex x : w.adjList) {
                if (!x.discovered) {
                    q.add(x);
                    x.discovered = true;
                }
            }
        }
        return visitedV;
    }

    static List bfss(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        List<Vertex> visitedV = new ArrayList<>();

        q.add(v);
        v.discovered = true;
        while (!q.isEmpty()) {
            Vertex w = q.remove();
            visitedV.add(w);
            for (Vertex x : w.adjList) {
                if (!x.discovered) {
                    q.add(x);
                    x.discovered = true;
                }
            }
        }
        return visitedV;
    }

    static void dfs(Vertex v, List<Vertex> vistedV) {
        vistedV.add(v);
        v.discovered = true;
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                dfs(w, vistedV);
            }
        }
    }

    static void dfss(Vertex v, List<Vertex> visitedV) {
        visitedV.add(v);
        v.discovered = true;
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
               dfs(w, visitedV); 
            }
        }
    }

    static Vertex[] readGraph(int nOfEdges, int nOfVertex) {
        Vertex[] vertices = new Vertex[nOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nOfEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }

        for (Vertex v : vertices) {
            v.adjList.sort((v1, v2) -> {
                return Integer.compare(v1.id, v2.id);
            });
        }
        return vertices;

    }

    static Vertex[] readGraphh (int nOfVertex, int nOfEdges){
        Vertex[] vertices = new Vertex[nOfVertex];
        for (int i = 0; i < nOfVertex; i++) {
            vertices[i] = new  Vertex(i);
        }

        for (int i = 0; i < nOfEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);

        }
        return vertices;
    }

    static class Vertex {
        int id;
        boolean discovered;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            adjList.add(v);
        }
    }

}
