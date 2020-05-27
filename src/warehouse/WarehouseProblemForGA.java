package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class WarehouseProblemForGA implements Problem<WarehouseIndividual> {

    private ArrayList<Request> requests;
    private LinkedList<Cell> shelves;
    private HashMap<String, Pair> pairs;
    private Cell door;
    private int numProducts;

    public WarehouseProblemForGA(WarehouseAgentSearch agentSearch) {
        requests = new ArrayList<>(agentSearch.getRequests());
        shelves = new LinkedList<>(agentSearch.getShelves());
        numProducts = agentSearch.getNumProducts();
        door = agentSearch.getExit();
        pairs = new HashMap<String, Pair>();
        for (int i = 0; i < agentSearch.getPairs().size(); i++) {
            Pair pair = (Pair) agentSearch.getPairs().get(i);
            String key = pair.getCell1().getLine() + "," + pair.getCell1().getColumn() + "-" + pair.getCell2().getLine() + "," + pair.getCell2().getColumn();
            pairs.put(key, pair);
        }

    }

    @Override
    public WarehouseIndividual getNewIndividual() {
        return new WarehouseIndividual(this, shelves.size());
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public LinkedList<Cell> getShelves() {
        return shelves;
    }

    public Cell getDoor() {
        return door;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public Pair getPair(Cell first, Cell second) {
        String key = first.getLine() + "," + first.getColumn() + "-" + second.getLine() + "," + second.getColumn();
        Pair pair = pairs.get(key);
        if (pair == null) {
            key = second.getLine() + "," + second.getColumn() + "-" + first.getLine() + "," + first.getColumn();
            pair = pairs.get(key);
        }
        return pair;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("# of products: ");
        sb.append(numProducts);
        sb.append("\n");
        sb.append("Requests: ");
        for (Request request : requests) {
            sb.append(request);
        }
        return sb.toString();
    }

}
