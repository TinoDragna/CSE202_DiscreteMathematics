import java.util.*;

public class Review_ForTest2 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    }

    static List bfs_(Vertex_ v) {
        Queue<Vertex_> q = new ArrayDeque<>();
        List<Vertex_> visitedV = new ArrayList<>();
        q.add(v);
        v.discovered = true;
        while (!q.isEmpty()) {
            Vertex_ w = q.remove();
            visitedV.add(w);
            for (Vertex_ x : w.adjList) {
                if (!x.discovered) {
                    q.add(x);
                    x.discovered = true;
                }
            }
        }
        return visitedV;
    }

    static List bfs(Vertex_ v) {
        Queue<Vertex_> q = new ArrayDeque<>();
        List<Vertex_> visitedV = new ArrayList<>();
        q.add(v);
        v.discovered = true;
        while (!q.isEmpty()) {
            Vertex_ w = q.remove();
            visitedV.add(w);
            for (Vertex_ x : w.adjList) {
                if (!x.discovered) {
                    q.add(x);
                    x.discovered = true;
                }
            }
        }
        return visitedV;
    }

    static void dfs_(Vertex_ v) {
        v.discovered = true;
        for (Vertex_ w : v.adjList) {
            if (!w.discovered) {
                dfs_(w);
            }
        }
    }

    static Vertex_[] readGraph(int nOfVertex, int nOfEdge) {
        Vertex_[] vertices = new Vertex_[nOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex_(i);
        }

        for (int i = 0; i < nOfEdge; i++) {
            Vertex_ u = vertices[sc.nextInt()];
            Vertex_ v = vertices[sc.nextInt()];
            u.addAdjNeghbor(v);
            v.addAdjNeghbor(u);
        }
        return vertices;
    }

    static Vertex[] readGraph2(int nOfVertex, int nOfEdge) {
        Vertex[] vertices = new Vertex[nOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nOfEdge; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];
            int w = sc.nextInt();
            u.addAdjNeghbor(v, w);
            v.addAdjNeghbor(u, w);
        }
        return vertices;
    }

    static void dijkstra_(Vertex[] vertices) {
        Queue<Vertex> q = new PriorityQueue<>();
        q.add(vertices[0]);
        vertices[0].cost = 0;
        while (!q.isEmpty()) {
            Vertex minVertex = q.remove();
            if (minVertex.discovered) {
                continue;
            }
            minVertex.discovered = true;
            Vertex origin = vertices[minVertex.id];
            for (Edge e : origin.adjList) {
                Vertex v = e.enpoint;
                int w = e.weight;
                if (v.discovered == false && origin.cost + w < v.cost) {
                    v.cost = origin.cost + w;
                    v.previous = origin;

                    Vertex clone = new Vertex(v.id);
                    clone.cost = v.cost;

                    q.add(clone);
                }
            }

        }
    }

    static void dijkstra(Vertex[] vertices) {
        Queue<Vertex> q = new PriorityQueue<>();
        q.add(vertices[0]);
        vertices[0].cost = 0;
        while (!q.isEmpty()) {
            Vertex minVertex = q.remove();
            if (minVertex.discovered) {
                continue;
            }
            minVertex.discovered = true;
            Vertex origin = new Vertex(minVertex.id);
            for (Edge e : origin.adjList) {
                Vertex v = e.enpoint;
                int w = e.weight;
                if (v.discovered == false && origin.cost + w < v.cost) {
                    v.cost = origin.cost + w;
                    v.previous = origin;

                    Vertex clone = new Vertex(v.id);
                    clone.cost = v.cost;

                    q.add(clone);
                }
            }
        }
    }

    static class Vertex_ {
        int id;
        boolean discovered;
        List<Vertex_> adjList = new ArrayList<>();

        public Vertex_(int id) {
            this.id = id;
        }

        public void addAdjNeghbor(Vertex_ v) {
            adjList.add(v);
        }

    }

    static class Vertex {
        int id;
        int cost = Integer.MAX_VALUE;
        boolean discovered;
        Vertex previous;
        List<Edge> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjNeghbor(Vertex v, int w) {
            adjList.add(new Edge(v, w));
        }
    }

    static class Edge {
        Vertex enpoint;
        int weight;

        public Edge(Vertex enpoint, int weight) {
            this.enpoint = enpoint;
            this.weight = weight;
        }

    }

}
