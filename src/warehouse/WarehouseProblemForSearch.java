package warehouse;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

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
        goalState = new WarehouseState(initialWarehouseState.getMatrix());

        // se o objectivo é a porta
        if (goalPosition.getLine() == initialWarehouseState.getLineExit() && goalPosition.getColumn() == initialWarehouseState.getColumnExit()){
            // mete o agente uma célula no objetivo final
            goalState.setCellAgent(goalPosition.getLine(), goalPosition.getColumn());
        }
        else{
            // mete o agente uma célula á direita do objetivo final
            goalState.setCellAgent(goalPosition.getLine(), goalPosition.getColumn() + 1);
        }
    }

    @Override
    public List<S> executeActions(S state) {
        List<S> sucessors = new LinkedList<S>();
        for (Action action : actions) {
            //verifica se a ação é válida
            if (action.isValid(state)) {
                //se for faz clone e adiciona-a á lista de sucessores
                S sucessor = (S) state.clone();
                action.execute(sucessor);
                sucessors.add(sucessor);
            }
        }
        return sucessors;
    }

    public boolean isGoal(S state) {
        //vê se o agente já se encontra na célula final
        return state.getLineAgent() == goalState.getLineAgent() && state.getColumnAgent() == goalState.getColumnAgent();
    }

    public WarehouseState getGoalState() {
        return goalState;
    }
}
