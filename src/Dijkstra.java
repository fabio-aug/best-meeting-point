import java.util.*;

class Dijkstra {
    private final int numberOfVertices;
    private final ArrayList<int[]>[] edges;

    Dijkstra(int numberOfVertices, ArrayList<int[]>[] edges) {
        this.numberOfVertices = numberOfVertices;
        this.edges = edges;
    }

    class EdgeWeight implements Comparable<EdgeWeight> {
        private int vertice;
        private int weight;

        EdgeWeight(int vertice, int weight) {
            this.vertice = vertice;
            this.weight = weight;
        }

        public int compareTo(EdgeWeight other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public int shortestPath(int from, int to) {
        PriorityQueue<EdgeWeight> listEdges = new PriorityQueue<>();

        int[][] distance = new int[this.numberOfVertices][2];

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        listEdges.add(new EdgeWeight(from, 0));
        distance[from][0] = 0;
        distance[from][1] = 0;

        while (!listEdges.isEmpty()) {
            int a = listEdges.poll().vertice;

            for (int[] neighbor : this.edges[a]) {
                int b = neighbor[0];
                int weight = neighbor[1];

                if (distance[b][0] > distance[a][0] + weight) {
                    distance[b][0] = distance[a][0] + weight;
                    distance[b][1] = a;
                    listEdges.add(new EdgeWeight(b, distance[b][0]));
                }
            }
        }

        return distance[to][0];
    }
}