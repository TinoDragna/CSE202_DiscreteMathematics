import java.io.*;
import java.util.*;

class B18_EIFOLTRE_InCayThuMuc_DonGian {
    // static Scanner sc = new Scanner(System.in);
    static InputReader sc;
    static StringBuilder sb = new StringBuilder();
    static Map<String, Vertex> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int n = sc.nextInt();
        // int m = sc.nextInt();

        Vertex[] graph = readGraph(n - 1, 0);

        String root = sc.next();

        dfs(map.get(root));

        System.out.println(sb);

    }

    static void dfs(Vertex v) {
        for (int i = 0; i < v.level; i++) {
            // for i -> level
            sb.append("---");
        }
        sb.append("-" + v.id).append("\n");
        v.discovered = true;
        v.adjList.sort((v1, v2) -> v1.id.compareToIgnoreCase(v2.id));
        for (Vertex w : v.adjList) {
            if (!w.discovered) {
                w.level = v.level + 1;
                dfs(w);
            }
        }
    }

    static Vertex[] readGraph(int nOfVertex, int nOfEdges) {
        Vertex[] vertices = new Vertex[nOfVertex];

        for (int i = 0; i < nOfVertex; i++) {
            String u = sc.next();
            String v = sc.next();

            Vertex uu = map.getOrDefault(u, new Vertex(u));
            map.put(u, uu);
            Vertex vv = map.getOrDefault(v, new Vertex(v));
            map.put(v, vv);

            uu.addAdjNeghbor(vv);
            vv.addAdjNeghbor(uu);
        }

        // for (Vertex v : vertices) {
        // v.adjList.sort((v1, v2) -> {
        // return Integer.compare(v1.id, v2.id);
        // });
        // }

        return vertices;
    }

    static class Vertex {
        String id;
        boolean discovered;
        List<Vertex> adjList = new ArrayList<>();
        int level; // distance

        public Vertex(String id) {
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
