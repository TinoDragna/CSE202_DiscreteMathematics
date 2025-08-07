import java.io.*;
import java.util.*;

class B10_EIUBFS1_BFSDirectedGraph {
    // static Scanner sc = new Scanner(System.in);
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        List<Vertex> visitedV = bfs(graph[0]);

        for (Vertex v : visitedV) {
            sb.append(v.id).append(" ");
        }

        System.out.println(sb);

    }

    static List bfs(Vertex v){
        Queue<Vertex> q = new ArrayDeque<>();
        List<Vertex> visitedV = new ArrayList<>();
        q.add(v);
        v.discovered = true;
        while (!q.isEmpty()) { 
            Vertex w = q.remove();
            // sb.append(w.id);
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
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjNeghbor(vertices[v]);
            // vertices[v].addAdjNeghbor(vertices[u]);
        }

        for (Vertex v : vertices) {
            v.adjList.sort((v1, v2) -> {
                return Integer.compare(v1.id, v2.id);
            });
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

        public void addAdjNeghbor(Vertex v) {
            adjList.add(v);
        }

        public int size() {
            return adjList.size();
        }
    }

    static class InputReader {

        StringTokenizer tokenizer;

        BufferedReader reader;

        String token;

        String temp;

        public InputReader(InputStream stream) {

            tokenizer = null;

            reader = new BufferedReader(new InputStreamReader(stream));

        }

        public InputReader(FileInputStream stream) {

            tokenizer = null;

            reader = new BufferedReader(new InputStreamReader(stream));

        }

        public String nextLine() throws IOException {

            return reader.readLine();

        }

        public String next() {

            while (tokenizer == null || !tokenizer.hasMoreTokens()) {

                try {

                    if (temp != null) {

                        tokenizer = new StringTokenizer(temp);

                        temp = null;

                    } else {

                        tokenizer = new StringTokenizer(reader.readLine());

                    }

                } catch (IOException e) {

                }

            }

            return tokenizer.nextToken();

        }

        public double nextDouble() {

            return Double.parseDouble(next());

        }

        public int nextInt() {

            return Integer.parseInt(next());

        }

        public long nextLong() {

            return Long.parseLong(next());

        }

    }
}
