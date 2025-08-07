
import java.io.*;
import java.util.*;

class EICON_IsConnected {
    // static Scanner sc = new Scanner(System.in);
    static InputReader sc = new InputReader(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        Vertex[] vertices = readGraph(n, m);

        //nếu a có cạnh đi đến b thì b nằm trong danh sách kề của a
        for (int i = 0; i < q; ++i) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            Vertex vA = vertices[a];
            Vertex vB = vertices[b];
            if(vA.adjecentVertices.contains(vB)){
                sb.append("Y").append("\n");
            } else{
                sb.append("N").append("\n");
            }
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int numberOfVertices, int numberOfEdges) {
        Vertex[] vertices = new Vertex[numberOfVertices + 1];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = new Vertex(i);
        }

        // Read all edges
        for (int i = 0; i < numberOfEdges; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[v].addNeighbor(vertices[u]); // v sang u, add u vào danh sách kề
        }
        return vertices;
    }

    static class Vertex {
        int id;
        List<Vertex> adjecentVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            adjecentVertices.add(v);
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
