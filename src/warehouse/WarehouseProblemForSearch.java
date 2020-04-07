package warehouse;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

    //TODO this class might require the definition of additional methods and/or attributes
    private Cell goalCell;
    private List<Action> actions;

    public WarehouseProblemForSearch(S initialWarehouseState, Cell goalPosition) {
        super(initialWarehouseState);
        actions = new ArrayList<>(4);
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionLeft());
        actions.add(new ActionDown());
        goalCell=goalPosition;
    }

    @Override
    public List<S> executeActions(S state) {
        List<WarehouseState> sucessors= new LinkedList<>();
        for (Action action : actions) {
            if(action.isValid(state)){
                WarehouseState sucessor = state.clone();
                action.execute(sucessor);
                sucessors.add(sucessor);
            }
        }
        return (List<S>) sucessors;
    }

    public boolean isGoal(S state) {
        return state.hasThisCellClose(goalCell);
    }

    public Cell getGoalCell() {
        return goalCell;
    }
}
