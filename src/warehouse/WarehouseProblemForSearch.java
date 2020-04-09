package warehouse;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

    //TODO this class might require the definition of additional methods and/or attributes
    private WarehouseState goalState;
    private List<Action> actions;

    public WarehouseProblemForSearch(S initialWarehouseState, Cell goalPosition) {
        super(initialWarehouseState);
        //adiciona a actions todas as ações possíveis por parte do agente
        actions = new ArrayList<>(4);
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionLeft());
        actions.add(new ActionDown());
        //inicializa o goalState como o estado inicial
        goalState= new WarehouseState(initialWarehouseState.getMatrix());
        //mete o agente uuma célula á direita do objetivo final
        goalState.setCellAgent(goalPosition.getLine(),goalPosition.getColumn()+1);
    }

    @Override
    public List<S> executeActions(S state) {
        List<WarehouseState> sucessors= new LinkedList<>();
        for (Action action : actions) {
            //verifica se a ação é válida
            if(action.isValid(state)){
                //se for adiciona-a á lista de sucessores
                WarehouseState sucessor = state.clone();
                action.execute(sucessor);
                sucessors.add(sucessor);
            }
        }
        return (List<S>) sucessors;
    }

    public boolean isGoal(S state) {
        return state.equals(goalState);
    }

    public WarehouseState getGoalState() {
        return goalState;
    }
}
