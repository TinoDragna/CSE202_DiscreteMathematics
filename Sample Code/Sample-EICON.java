import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main2 {

	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int q = sc.nextInt();
		Vertex[] vertices = readGraph(n, m);
		// Nếu a có cạnh đi đến b thì b nằm trongdanh sách kề của a
		for (int i = 0; i < q; ++i) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			Vertex vA = vertices[a];
			Vertex vB = vertices[b];
			if (vA.adjecentVertices.contains(vB)) {
				System.out.println("Y");
			} else {
				System.out.print("N");
			}
		}
	}

	static Vertex[] readGraph(int numberOfVertices, int numberOfEdges) {
		Vertex[] vertices = new Vertex[numberOfVertices + 1];
		for (int i = 1; i <= vertices.length; ++i) {
			vertices[i] = new Vertex(i);
		}
		// Read all edges
		for (int i = 0; i < numberOfEdges; ++i) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			// vertices[u].addNeighbor(vertices[v]);
			vertices[v].addNeighbor(vertices[u]);
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

}
