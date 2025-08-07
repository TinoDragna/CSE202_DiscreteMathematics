import java.io.*;
import java.util.*;

class B17_EICONP3_ConnectedComponents3 {
    // static Scanner sc = new Scanner(System.in);
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();
    static int minID = Integer.MAX_VALUE;
    static int nOfVertex = 0;
    static int nOfEdge = 0;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        List<Represent> represent = new ArrayList<>();

        for (int i = 0; i < graph.length; i++) {
            if (!graph[i].discovered) {
                List<Vertex> visitedV = new ArrayList<>();
                dfs(graph[i], visitedV);
                represent.add(new Represent(minID, nOfVertex, nOfEdge));
            }
            minID = Integer.MAX_VALUE;
            nOfVertex = 0;
            nOfEdge = 0;
        }

        represent.sort((v1, v2) -> {
            return Integer.compare(v1.id, v2.id);
        });

        for (Represent v : represent) {
            sb.append(v.toString());
        }

        System.out.println(sb);

    }

    static void dfs(Vertex v, List<Vertex> visitedV) {
        visitedV.add(v);
        v.discovered = true;
        minID = Math.min(v.id, minID);
        nOfVertex++;
        nOfEdge += v.adjList.size();
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                dfs(w, visitedV);
            }
        }
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
            vertices[v].addAdjNeghbor(vertices[u]);
        }

        for (Vertex v : vertices) {
            v.adjList.sort((v1, v2) -> {
                return Integer.compare(v1.id, v2.id);
            });
        }

        return vertices;
    }

    static class Represent {
        int id;
        int nOfVertex;
        int nOfEdge;

        public Represent(int id, int nOfVertex, int nOfEdge) {
            this.id = id;
            this.nOfVertex = nOfVertex;
            this.nOfEdge = nOfEdge;
        }

        @Override
        public String toString() {
            return id + " " + nOfVertex + " " + nOfEdge/2 + "\n"; // deg v = 2E
                                                                // bậc của đỉnh = 2 x số cạnh
        }

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
