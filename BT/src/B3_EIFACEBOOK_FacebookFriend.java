import java.io.*;
import java.util.*;

class B3_EIFACEBOOK_FacebookFriend {
    // static Scanner sc = new Scanner(System.in);
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        for (int i = 1; i < graph.length; i++) {
            sb.append(graph[i].size()).append(" ");
        }

        System.out.println(sb);

    }

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex+1];
        for (int i = 1; i < vertices.length; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(i, gender);
        }

        for (int i = 0; i < nOfEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjNeghbor(vertices[v]);
            vertices[v].addAdjNeghbor(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        String gender;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addAdjNeghbor(Vertex v) {
            if (!adjList.contains(v)) {
                adjList.add(v);
            }
        }

        public int size() {
            int size = 0;
            for (Vertex vertex : adjList) {
                if (!vertex.gender.equals(gender)) {
                    size++;
                }
            }
            return size;
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
