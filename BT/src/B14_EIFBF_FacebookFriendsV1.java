import java.io.*;
import java.util.*;

class B14_EIFBF_FacebookFriendsV1 {
    static InputReader sc;
    static StringBuilder sb = new StringBuilder();
    static int maxID = 0;
    static int nam = 0;
    static int nu = 0;

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        List<Vertex> represent = new ArrayList<>();

        for (int i = 1; i < graph.length; i++) {
            if (!graph[i].discovered) {
                List<Vertex> visitedV = new ArrayList<>();
                dfs(graph[i], visitedV);
                represent.add(graph[maxID]);
                graph[maxID].nam = nam;
                graph[maxID].nu = nu;
            }
            maxID = 0;
            nam = 0;
            nu = 0;
        }

        represent.sort((v1, v2) -> {
            return Integer.compare(v1.id, v2.id);
        });

        for (Vertex v : represent) {
            sb.append(v.id).append(" ");
            sb.append(v.nam).append(" ").append(v.nu).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(Vertex v, List<Vertex> visitedV) {
        visitedV.add(v);
        v.discovered = true;
        if (v.id > maxID) {
            maxID = v.id;
        }
        if (v.gender.equalsIgnoreCase("Nam")) {
            nam++;
        } else {
            nu++;
        }
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                dfs(w, visitedV);
            }
        }
    }

    // static List bfs(Vertex v, List<Vertex> visitedV) {
    // Queue<Vertex> q = new ArrayDeque<>();
    // q.add(v);
    // v.discovered = true;
    // while (!q.isEmpty()) {
    // Vertex w = q.remove();
    // if (w.id > maxID) {

    // }
    // visitedV.add(w);
    // for (Vertex x : w.adjList) {
    // if (!x.discovered) {
    // q.add(x);
    // x.discovered = true;
    // }
    // }
    // }
    // return visitedV;
    // }

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex + 1];
        for (int i = 1; i < vertices.length; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(i, gender, 0, 0);
        }

        for (int i = 0; i < nOfEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjNeghbor(vertices[v]);
            vertices[v].addAdjNeghbor(vertices[u]);
        }

        for (int i = 1; i < vertices.length; i++) {
            vertices[i].adjList.sort((v1, v2) -> {
                return Integer.compare(v1.id, v2.id);
            });
        }

        return vertices;
    }

    static class Vertex {
        int id;
        String gender;
        boolean discovered;
        List<Vertex> adjList = new ArrayList<>();
        int nam;
        int nu;

        public Vertex(int id, String gender, int nam, int nu) {
            this.id = id;
            this.gender = gender;
            this.nam = nam;
            this.nu = nu;
        }

        public void addAdjNeghbor(Vertex v) {
            if (!adjList.contains(v)) {
                adjList.add(v);
            }
        }

    }

    static class InputReader {

        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1) {
                throw new InputMismatchException();
            }
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0) {
                    return -1;
                }
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }

}
