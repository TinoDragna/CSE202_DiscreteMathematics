import com.sun.tools.javac.Main;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class VD20_5_2025 {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] graph = readGraph(n, m);

        Vertex root = null;
        for (Vertex v : graph) {
            if (v.parent == null) {
                root = v;
                break;
            }
        }
        dfs(root);
        int maxLevel = -1;

        for (Vertex v : graph) {
            if (v.level > maxLevel) {
                maxLevel = v.level;
            }
        }

        System.out.println(maxLevel + 1);
    }

    static void findUnvisitedMinDistanceVertex(Vertex[] vertices) {
        Vertex result = null;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].marked = false) {
                if (result == null) {

                } else if (vertices[i].cost < result.cost) {
                    result = vertices[i];
                }
            }
        }

    }

    static List<int[]> findMSTPrim(Vertex[] vertices) {
        // choose vertex 0
        vertices[0].cost = 0;
        int remainingUnmarkedNode = vertices.length;
        List<int[]> edge = new ArrayList<>();
        int totalCost = 0;
        PriorityQueue<Vertex> q = new PriorityQueue<Main.Vertex>();
        q.add(vertices[0]);
        while (remainingUnmarkedNode > 0) {
            Vertex minNode = q.poll();

            // findUnvisitedMinDistanceVertex(vertices);
            if (minNode == null) {
                System.out.println(-1);
                return null;
            } else {
                if (vertices[minNode.id].discovered) {
                    continue;
                }
                minNode = vertices[minNode.id];
                minNode.discovered = true;
                remainingUnmarkedNode--;
                if (minNode.id != 0) {
                    int[] edge = new int[] { minNode.id, minNode.parent.id, minNode.cost };
                    edges.add(edge);
                    totalCost += minNode.cost;
                }
                for (Edge e : minNode.adjList) {
                    if (e.endpoint.marked == false && e.weight < e.endpoint.cost) {
                        e.endpoint.cost = e.weight;
                        e.endpoint.parent = minNode;
                        // add đỉnh vào lại priorityQueueQueue
                        Vertex cloned = new Vertex(e.endpoint.id);
                        cloned.cost = e.endpoint.cost;
                        q.add(cloned);

                    }
                }
            }
        }

    }

    static Vertex[] readGraph(Scanner sc) {
        int n = sc.nextInt();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];
            int w = sc.nextInt();
            u.addAdjNeghbor(v, w);
            v.addAdjNeghbor(u, w);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        boolean discovered;
        Vertex parent;
        int cost = Integer.MAX_VALUE;
        List<Edge> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjNeghbor(Vertex vertex, int w) {
            adjList.add(new Edge(vertex, w));
        }
    }

    static class Edge {
        public int weight;
        public Vertex endpoint;

        public Edge(Vertex endpoint, int w) {
            this.endpoint = endpoint;
            this.weight = w;
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
