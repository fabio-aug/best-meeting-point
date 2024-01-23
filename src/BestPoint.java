import java.util.ArrayList;

public class BestPoint {
    private int numberOfVertices;
    private ArrayList<int[]>[] edges;
    private int[] encounters;
    private int[] friends;

    @SuppressWarnings("unchecked")
    BestPoint(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.edges = new ArrayList[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++)
            this.edges[i] = new ArrayList<>();
    }

    public void addEdge(int from, int to, int cost) {
        this.edges[from].add(new int[] { to, cost });
        this.edges[to].add(new int[] { from, cost });
    }

    public void setEncounters(int[] encounters) {
        this.encounters = encounters;
    }

    public void setFriends(int[] friends) {
        this.friends = friends;
    }

    public void find() {
        Dijkstra dijkstra = new Dijkstra(this.numberOfVertices, this.edges);

        int local = -1;
        int coust = Integer.MAX_VALUE;
        for (int i = 0; i < this.encounters.length; i++) {
            int tempCost = 0;
            for (int j = 0; j < this.friends.length; j++) {
                tempCost += dijkstra.shortestPath(this.friends[j], this.encounters[i]);
            }
            if (tempCost < coust) {
                local = this.encounters[i];
                coust = tempCost;
            }
        }

        System.out.println("Melhor local: " + local);
        System.out.println("Custo total de locomoção: " + coust);
    }

    public static void main(String[] args) {
        BestPoint bp = new BestPoint(16);
        bp.setEncounters(new int[] { 2, 5, 7, 11 });
        bp.setFriends(new int[] { 0, 3, 4, 9, 13, 14, 15 });

        bp.addEdge(0, 1, 50);
        bp.addEdge(1, 2, 5);
        bp.addEdge(2, 3, 10);
        bp.addEdge(2, 5, 45);
        bp.addEdge(3, 4, 15);
        bp.addEdge(3, 7, 20);
        bp.addEdge(7, 8, 10);
        bp.addEdge(8, 13, 8);
        bp.addEdge(8, 15, 25);
        bp.addEdge(13, 15, 15);
        bp.addEdge(15, 12, 27);
        bp.addEdge(12, 11, 14);
        bp.addEdge(12, 14, 32);
        bp.addEdge(14, 9, 10);
        bp.addEdge(14, 10, 10);
        bp.addEdge(10, 6, 34);
        bp.addEdge(6, 5, 33);
        bp.addEdge(9, 5, 47);

        bp.find();
    }
}
