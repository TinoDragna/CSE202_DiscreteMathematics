import java.io.*;
import java.util.*;

class EILOCALA_LocalAreaNetwork {

    //   (0)        lần 1: từ đỉnh 0;   maxDistance = 3; minID = 4
    //   / \        lần 2: từ minID;    maxDistance = 4; minID = 1
    // (1) (2)
    //      |
    //     (3)
    //      |
    //     (4)


    static InputReader sc;
    static StringBuilder sb = new StringBuilder();
    static long maxDistance = 0;
    static int minID = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int n = sc.nextInt();
        int m = n - 1;
        Vertex[] graph = readGraph(n, m);

        dfs(graph[0], 0);

        for (Vertex v : graph) {
            v.discovered = false;
        }

        maxDistance = 0;
        int tempMinID = minID;
        minID = Integer.MAX_VALUE;

        dfs(graph[tempMinID], 0);
        System.out.println(Math.min(tempMinID, minID) + " " + maxDistance);
    }

    static void dfs(Vertex v, long distanceFromRoot) {
        v.discovered = true;
        if (distanceFromRoot > maxDistance) {
            maxDistance = distanceFromRoot;
            minID = v.id;
        } else if (distanceFromRoot == maxDistance) {
            minID = Math.min(minID, v.id);
        }
        for (Edge w : v.adjList) {
            if (!w.endpoint.discovered) {
                dfs(w.endpoint, distanceFromRoot + w.weight); // distanceFromRoot đang thay đổi theo mỗi lần đệ quy
            }
        }
    }

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nOfEdges; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];
            int w = sc.nextInt();
            u.addVertex(v, w);
            v.addVertex(u, w);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        boolean discovered;
        int level;
        int maxDistance;
        public List<Edge> adjList = new ArrayList<Edge>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addVertex(Vertex vertex, int w) {
            adjList.add(new Edge(vertex, w));
        }

    }

    static class Edge {
        Vertex endpoint;
        int weight;

        public Edge(Vertex endpoint, int weight) {
            this.endpoint = endpoint;
            this.weight = weight;
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
