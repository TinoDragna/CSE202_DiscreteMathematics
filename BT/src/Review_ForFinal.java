
import java.util.*;

public class Review_ForFinal {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = n - 1;
        Vertex[] graph = readGraph(n, m);

        dfs25(graph[0], 0);
        for (Vertex v : graph) {
            v.discovered = false;
        }

        int temp = minID;
        maxDistance = 0;
        minID = Integer.MAX_VALUE;

        dfs25(graph[temp], 0);
        System.out.println(Math.min(temp, minID));

    }

    static void dfs(Vertex v) {
        v.discovered = true;
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                dfs(w);
            }
        }
    }

    static void dfs20(Vertex v) {
        v.discovered = true;
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                w.level = v.level + 1;
                dfs20(w);
            }
        }
    }

    static String cycle = "";

    static void dfs15(Vertex v, String path) {
        v.discovered = true;
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                dfs15(v, path + " " + w.id);
            } else if (w.id == 0 && path.length()>=5) {
                cycle = path;
            }
        }
    }

    static long maxDistance = 0;
    static int minID = Integer.MAX_VALUE;

    static void dfs25(Vertex v, long distanceFromRoot) {
        v.discovered = true;
        if (distanceFromRoot > maxDistance) {
            maxDistance = distanceFromRoot;
            minID = v.id;
        } else if (distanceFromRoot == maxDistance) {
            minID = Math.min(minID, v.id);
        }

        for (Edge w : v.adjList_) {
            if (!w.endpoint.discovered) {
                dfs25(w.endpoint, distanceFromRoot + w.weight);
            }
        }
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

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < nOfEdges; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];

            u.addAdjNeghbor(v);
            v.addAdjNeghbor(u);
        }
        return vertices;

    }

    static class Vertex {
        int id;
        boolean discovered;
        int level;
        List<Vertex> adjList = new ArrayList<>();
        List<Edge> adjList_ = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjNeghbor(Vertex v) {
            adjList.add(v);
        }

        public void addAdjNeghbor_(Vertex v, int w) {
            adjList_.add(new Edge(v, w));
        }

    }

    static class Edge {
        Vertex endpoint;
        int weight;

        public Edge(Review_ForFinal.Vertex endpoint, int weight) {
            this.endpoint = endpoint;
            this.weight = weight;
        }

    }

}
