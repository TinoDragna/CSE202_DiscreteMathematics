import java.io.*;
import java.util.*;

class VD {
    // static Scanner sc = new Scanner(System.in);
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        for (int i = 0; i < q; i++) {
            int v = sc.nextInt();
            graph[v].value = sc.nextInt();
        }

        List<Vertex> visitedV = new ArrayList<>();
        dfs(graph[0], visitedV);

        for (Vertex v : graph) {
            sb.append(v.id).append(" ").append(v.value).append("\n");
        }
        

        System.out.println(sb);

    }

    static void dfs(Vertex v, List<Vertex> visitedV) {
        visitedV.add(v);
        v.discovered = true;
        for (Vertex w : v.adjList) {
            if (!w.discovered) {                
                dfs(w, visitedV);
                v.value+=w.value;
            }
            // v.value+=w.value;
        }
    }

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i, 0);
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
        int value;
        boolean discovered;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id, int value) {
            this.id = id;
            this.value = value;
        }

        public void addAdjNeghbor(Vertex v) {
            adjList.add(v);
        }

        public int size() {
            return adjList.size();
        }
    }


//     8 7 5
// 0 1
// 0 2
// 1 3
// 1 4
// 4 5 
// 4 6
// 4 7
// 2 1
// 3 5
// 5 1
// 6 2
// 7 3



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
