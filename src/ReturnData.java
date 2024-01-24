public class ReturnData {
    private final int cost;
    private final String tour;

    ReturnData(int cost, String tour) {
        this.cost = cost;
        this.tour = tour;
    }

    public int getCost() {
        return cost;
    }

    public String getTour() {
        return tour;
    }

    @Override
    public String toString() {
        return "ReturnData [cost=" + this.cost + ", tour=" + this.tour + "]";
    }
}
