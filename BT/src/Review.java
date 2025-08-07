import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Review {
    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = readGraph(n, m);
        // int nConnectedComponents = 0;
        // for (int i = 0; i < n; i++) {
        // Vertex v = vertices[i];
        // if (!v.discovered) {
        // nConnectedComponents++;
        // List<Vertex> visited = new ArrayList<>();
        // dfs(v, visited);
        // for (Vertex x : visited) {
        // x.connectedComponentsIndex = nConnectedComponents;
        // }
        // }
        // }
        List<Vertex> visitedVertives = bfs(vertices[0]);
        int length = vertices[0].level;
        List<Vertex> path = new ArrayList<>();
        Vertex current = vertices[0];
        do {
            path.add(current);
            current = current.parent;
        } while (current != vertices[0]);
        System.out.println(sb);
    }

    static void dfs(Vertex vertex, List<Vertex> visitedVertices) {
        visitedVertices.add(vertex);
        vertex.discovered = true;
        for (Vertex w : vertex.adjList) {
            if (!w.discovered) {
                w.parent = vertex;
                dfs(w, visitedVertices);
            }
        }
    }

    static List<Vertex> bfs(Vertex vertex) {
        Queue<Vertex> q = new ArrayDeque<>();
        List<Vertex> visitedVertives = new ArrayList<>();
        q.add(vertex);
        vertex.discovered = true;
        while (!q.isEmpty()) {
            Vertex w = q.remove();
            for (Vertex x : w.adjList) {
                if (!x.discovered) {
                    x.level = w.level + 1;
                    x.parent = w;
                    q.add(x);
                    visitedVertives.add(x);
                    x.discovered = true;
                }
            }
        }
        return visitedVertives;
    }

    static Vertex[] readGraph(int numberOfVertices, int numberOfEdges) {
        Vertex[] vertices = new Vertex[numberOfVertices];
        for (int i = 0; i < numberOfVertices; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < numberOfEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }

        for (int i = 0; i < numberOfVertices; ++i) {
            Collections.sort(vertices[i].adjList, (v1, v2) -> v1.id - v2.id);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        int connectedComponentsIndex = 0;
        int level;
        Vertex parent;
        boolean discovered;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            adjList.add(v);
        }
    }

    // EIPEOYMK_PeopleYouMayKnow_Other
    static void bfs1(Vertex v, Map<Integer, List<Vertex>> levelList) { 
        Queue<Vertex> q = new ArrayDeque<>();
        List<Vertex> level0 = new ArrayList<>();
        levelList.put(0, level0);
        q.add(v);
        v.discovered = true;
        while (!q.isEmpty()) {
            Vertex w = q.remove();
            for (Vertex x : w.adjList) {
                if (!x.discovered) {
                    x.level = w.level + 1;
                    List<Vertex> levelX = levelList.get(x.level);
                    if (levelX == null) {
                    levelX = new ArrayList<>();
                    levelList.put(x.level, levelX);
                    }
                    q.add(x);
                    x.discovered = true;
                }
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
