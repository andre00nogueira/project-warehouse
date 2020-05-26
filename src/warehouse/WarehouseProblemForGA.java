package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class WarehouseProblemForGA implements Problem<WarehouseIndividual> {

    private ArrayList<Request> requests;
    private LinkedList<Cell> shelves;
    //private HashMap<Integer, Pair> pairs;
    private LinkedList<Pair> pairs;

    private Cell door;
    private int numProducts;

    public WarehouseProblemForGA(WarehouseAgentSearch agentSearch) {
        requests = new ArrayList<>(agentSearch.getRequests());
        shelves = new LinkedList<>(agentSearch.getShelves());
        numProducts = agentSearch.getNumProducts();
        door = agentSearch.getExit();
        /*pairs = new HashMap<Integer, Pair>();
        for (int i = 0; i < agentSearch.getPairs().size(); i++) {
            pairs.put(i, (Pair) agentSearch.getPairs().get(i));
        }*/
        pairs = new LinkedList<>(agentSearch.getPairs());

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

    /*public HashMap<Integer, Pair> getPairs() {
        return pairs;
    }*/

    public LinkedList<Pair> getPairs() {
        return pairs;
    }

    public Cell getDoor() {
        return door;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public Pair getPair(Cell first, Cell second) {
        for (Pair pair : pairs) {
            if (pair.getCell1().equals(first) && pair.getCell2().equals(second) || pair.getCell1().equals(second) && pair.getCell2().equals(first)){
        /*for (int i = 0; i < pairs.size(); i++) {
            Pair pair = pairs.get(i);
            if (pair.getCell1().equals(first) && pair.getCell2().equals(second) || pair.getCell1().equals(second) && pair.getCell2().equals(first)) {*/
                return pair;
            }
        }
        return null;
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
