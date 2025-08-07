import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

class B29_EIMULEMA_MultiLevelMarketing {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);

        int n = sc.nextInt();
        int m = n - 1;
        Vertex[] graph = readGraph(n, m);

        dfs(graph[0]);

        for (Vertex v : graph) {
            sb.append(v.id + " " + v.commission).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.discovered = true;
        v.commission += v.profit * 15 / 100;
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                dfs(w);
                v.commission += w.commission / 2;
            }
        }
    }

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            double profit = sc.nextDouble();
            vertices[i] = new Vertex(i, profit);
        }

        for (int i = 0; i < nOfEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjNeghbor(vertices[v]);
            // vertices[v].addAdjNeghbor(vertices[u]);
        }

        // for (int i = 1; i < vertices.length; i++) {
        // vertices[i].adjList.sort((v1, v2) -> {
        // return Integer.compare(v1.id, v2.id);
        // });
        // }

        return vertices;
    }

    static class Vertex {
        int id;
        boolean discovered;
        double profit; // doanh thu
        int level;
        Vertex parent;
        int commission; // hoa hồng từ người được giới thiệu
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id, double profit) {
            this.id = id;
            this.profit = profit;
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
