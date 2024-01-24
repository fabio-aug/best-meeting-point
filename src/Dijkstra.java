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

    public ReturnData shortestPath(int from, int to) {
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

        return new ReturnData(distance[to][0], showInfo(distance, to));
    }

    public String concatInfo(int[][] distance, int index) {
        String vertice = "Vértice: " + index;
        String cost = "Custo: " + distance[index][0];
        return vertice + " - " + cost;
    }

    public String showInfo(int[][] distance, int to) {
        String print = "\n" + concatInfo(distance, to);
        int index = distance[to][1];
        while (index != 0 && distance[index][0] != 0) {
            print = "\n" + concatInfo(distance, index) + print;
            index = distance[index][1];
        }
        print = concatInfo(distance, index) + print;
        return print;
    }
}