package warehouse;

import agentSearch.Heuristic;

public class HeuristicWarehouse extends Heuristic<WarehouseProblemForSearch, WarehouseState> {
    @Override
    public double compute(WarehouseState state){
        return state.computeTileDistance(problem.getGoalState());
    }

    @Override
    public String toString(){
        return "Distance to final cell";
    }
}