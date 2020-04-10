package warehouse;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WarehouseState extends State implements Cloneable {

    //TODO this class might require the definition of additional methods and/or attributes

    private int[][] matrix;
    private int lineAgent, columnAgent;
    private int lineExit;
    private int columnExit;
    private int steps;

    public WarehouseState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) { // para cada linha
            for (int j = 0; j < matrix.length; j++) { // para cada coluna
                this.matrix[i][j] = matrix[i][j]; // o valor da matrix atributo é igual ao valor da matriz argument
                if (this.matrix[i][j] == 1) {
                    lineExit = i;
                    columnExit = j;
                }
            }
        }

        steps = 0;
    }

    public void executeAction(Action action) {
        action.execute(this);
        steps++;
    }

    public void executeActionSimulation(Action action) {
        executeAction(action);
        fireUpdatedEnvironment();
    }


    public boolean canMoveUp() {
        // Para se poder mover para cima...
        // A linha do agente tem que ser diferente de 0, pois não pode ir para a linha -1
        // E a matrix na posição do agente uma linha acima tem que ser 0...
        // Pois se não for 0 e for outro número qualquer, indica que a célula é uma porta ou uma prateleira
        return lineAgent != 0 && matrix[lineAgent - 1][columnAgent] == 0;
    }

    public boolean canMoveRight() {
        // Para se poder mover para a direita...
        // A coluna do agente tem que ser diferente do tamanho da matrix, senão iria passar do tamanho da matrix
        // E a matrix na posição do agente uma coluna à direita tem que ser 0...
        // Pois se não for 0 e for outro número qualquer, indica que a célula é uma porta ou uma prateleira
        return columnAgent != matrix.length - 1 && matrix[lineAgent][columnAgent + 1] == 0;
    }

    public boolean canMoveDown() {
        // Para se poder mover para baixo...
        // A linha do agente tem que ser diferente do tamanho da matrix, senão iria passar do tamanho da matrix
        // E a matrix na posição do agente uma linha para baixo tem que ser 0...
        // Pois se não for 0 e for outro número qualquer, indica que a célula é uma porta ou uma prateleira
        return lineAgent != matrix.length - 1 && matrix[lineAgent + 1][columnAgent] == 0;
    }

    public boolean canMoveLeft() {
        // Para se poder mover para a esquerda...
        // A coluna do agente tem que ser diferente de 0, senão iria passar a ser -1
        // E a matrix na posição do agente uma coluna à esquerda tem que ser 0...
        // Pois se não for 0 e for outro número qualquer, indica que a célula é uma porta ou uma prateleira
        return columnAgent != 0 && matrix[lineAgent][columnAgent - 1] == 0;
    }

    public void moveUp() {
        // Coloca a célula onde o agente estava como EMPTY
        // E a célula onde o agente passa a estar como AGENT
        matrix[lineAgent--][columnAgent] = Properties.EMPTY;
        matrix[lineAgent][columnAgent] = Properties.AGENT;
    }

    public void moveRight() {
        // Coloca a célula onde o agente estava como EMPTY
        // E a célula onde o agente passa a estar como AGENT
        matrix[lineAgent][columnAgent++] = Properties.EMPTY;
        matrix[lineAgent][columnAgent] = Properties.AGENT;
    }

    public void moveDown() {
        // Coloca a célula onde o agente estava como EMPTY
        // E a célula onde o agente passa a estar como AGENT
        matrix[lineAgent++][columnAgent] = Properties.EMPTY;
        matrix[lineAgent][columnAgent] = Properties.AGENT;
    }

    public void moveLeft() {
        // Coloca a célula onde o agente estava como EMPTY
        // E a célula onde o agente passa a estar como AGENT
        matrix[lineAgent][columnAgent--] = Properties.EMPTY;
        matrix[lineAgent][columnAgent] = Properties.AGENT;
    }

    public void setCellAgent(int line, int column) {
        // Recebe a linha e coluna e coloca para colocar o agente nessas coordenadas
        if (line < matrix.length && column < matrix.length && line > -1 && column > -1) {
                lineAgent = line;
                columnAgent = column;
        }
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        if (line == lineExit && column == columnExit && (line != lineAgent || column != columnAgent))
            return Properties.COLOREXIT;

        switch (matrix[line][column]) {
            case Properties.AGENT:
                return Properties.COLORAGENT;
            case Properties.SHELF:
                return Properties.COLORSHELF;
            default:
                return Properties.COLOREMPTY;
        }
    }

    public int getLineAgent() {
        return lineAgent;
    }

    public int getColumnAgent() {
        return columnAgent;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WarehouseState)) {
            return false;
        }

        WarehouseState o = (WarehouseState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public WarehouseState clone() {
        return new WarehouseState(matrix);
    }

    public double computeTileDistance(WarehouseState state) {
        //calcula a distância em saltos até goalState
        return Math.abs((lineAgent + 1) - (state.getLineAgent()) + 1) + Math.abs((columnAgent) + 1) - (state.getColumnAgent() + 1);
    }



    // *** LISTENERS ***

    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

}
