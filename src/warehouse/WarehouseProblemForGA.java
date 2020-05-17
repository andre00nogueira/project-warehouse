package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.LinkedList;

public class WarehouseProblemForGA implements Problem<WarehouseIndividual> {

    //TODO this class might require the definition of additional methods and/or attributes
    private ArrayList<Request> requests;
    private LinkedList<Cell> shelves;
    private LinkedList<Pair> pairs;
    private Cell door;
    private int numProducts;


    public WarehouseProblemForGA(WarehouseAgentSearch agentSearch) {
        requests = new ArrayList<>(agentSearch.getRequests());
        shelves = new LinkedList<>(agentSearch.getShelves());
        pairs = new LinkedList<>(agentSearch.getPairs());
        numProducts = agentSearch.getNumProducts();
        door = agentSearch.getExit();
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
            if ((pair.getCell1() == first && pair.getCell2() == second) || (pair.getCell1() == second && pair.getCell2() == first)) {
                return pair;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
