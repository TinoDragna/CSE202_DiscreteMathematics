import java.io.*;
import java.util.*;
class B1_EIHCON_IsThereAPath {
    // static  Scanner sc = new Scanner(System.in);
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            Vertex vA = graph[a];
            Vertex vB = graph[b];

            if (vA.adjList.contains(vB)) {
                sb.append("Y").append("\n");
            } else {
                boolean flag = false;
                for (Vertex adjVertex : vA.adjList) {
                    if (adjVertex.adjList.contains(vB)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    sb.append("Y").append("\n");
                } else {
                    sb.append("N").append("\n");
                }
            }
        }

        System.out.println(sb);

    }

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex + 1];
        for (int i = 1; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nOfEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // vertices[u].addAdjNeghbor(vertices[v]);
            vertices[v].addAdjNeghbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
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
