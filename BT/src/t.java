// public class t {
//     // distance 2 thằng a ->b, b->c, 2 thằng nhân nhau ->
// }


import java.util.*;

class t {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static Vertex[] vertices;
    static int shortest = Integer.MAX_VALUE;
    static int countPathB = 0;
    static int countNumPath = 0;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        vertices = readGraph(n, m);
        dijistra(a);
        shortest = vertices[c].distanceFromRoot;

        sb.append(countNumPath + " " + countPathB).append("\n");
        sb.append(shortest + " " + vertices[b].distanceFromRoot);
        System.out.println(sb);
    }

    static void dijistra(int root){
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(vertices[root]);
        vertices[root].distanceFromRoot = 0;
        int alt = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            current.visited = true;

            for (Edge e : current.edges) {
                if (!e.end.visited) {
                    if (e.end.distanceFromRoot == -1) {
                        e.end.distanceFromRoot = e.length + current.distanceFromRoot;
                    } else {
                        if (e.length + current.distanceFromRoot < e.end.distanceFromRoot) {
                            e.end.distanceFromRoot = e.length + current.distanceFromRoot;
                        }
                    }

                    if (e.end.distanceFromRoot < alt) {
                        alt = e.end.distanceFromRoot;
                    }

                    queue.add(e.end);
                }
            }
        }
    }

    static class Edge {
        Vertex end;
        int length;

        public Edge(Vertex end, int length) {
            this.end = end;
            this.length = length;
        }
    }

    static class Vertex implements Comparable<Vertex> {
        int id;
        boolean visited = false;
        int distanceFromRoot;
        List<Edge> edges = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
            this.distanceFromRoot = -1;
        }

        public void addEdge(Edge e){
            edges.add(e);
        }

        public int compareTo(Vertex o) {
            return Integer.compare(this.distanceFromRoot, o.distanceFromRoot);
        }
    }

    static Vertex[] readGraph(int n, int m){
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            vertices[u].addEdge(new Edge(vertices[v], w));
            vertices[v].addEdge(new Edge(vertices[u], w));
        }

        return vertices;
    }
}
