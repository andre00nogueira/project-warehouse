package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.LinkedList;

public class WarehouseProblemForGA implements Problem<WarehouseIndividual> {

    //TODO this class might require the definition of additional methods and/or attributes
    private ArrayList<Request> requests;
    private int numeroProdutos;

    public WarehouseProblemForGA(WarehouseAgentSearch agentSearch) {
        //TODO
        numeroProdutos=agentSearch.getNumProducts();
        requests= new ArrayList<>();
        System.arraycopy(agentSearch.getRequests(), 0, requests, 0, agentSearch.getRequests().size());
        requests=agentSearch.getRequests();
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public WarehouseIndividual getNewIndividual() {
        return new WarehouseIndividual(this,requests.size());
    }

    @Override
    public String toString() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
