package warehouse;

import agentSearch.Problem;

import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

    //TODO this class might require the definition of additional methods and/or attributes

    public WarehouseProblemForSearch(S initialWarehouseState, Cell goalPosition) {
        super(initialWarehouseState);

        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<S> executeActions(S state) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean isGoal(S state) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
