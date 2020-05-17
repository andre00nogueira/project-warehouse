package warehouse;

import ga.IntVectorIndividual;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class WarehouseIndividual extends IntVectorIndividual<WarehouseProblemForGA, WarehouseIndividual> {

    public static final int NAO_ENCONTRADO = -1;
    private int pathCost;

    public WarehouseIndividual(WarehouseProblemForGA problem, int size) {
        super(problem, size);
        pathCost = 0;
        //criar o genoma para o individuo
        int[] shelvesWithProduct = new Random().ints(0, problem.getShelves().size()).distinct().limit(problem.getNumProducts()).toArray();
        for (int i = 0; i < problem.getNumProducts(); i++) {
            setGene(shelvesWithProduct[i],i+1);
        }
    }

    public WarehouseIndividual(WarehouseIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        ArrayList<Request> requests = new ArrayList<>(problem.getRequests());
        for (Request request : requests) {
            //Para cada Request
            int[] requestUnico = request.getRequest();
            for (Cell shelve : problem.getShelves()) {

            }
            //vê as distâncias entre cada um dos produtos a ser recolhidos e adiciona ao pathCost
            for (int i = 0; i < requestUnico.length - 1; i++) {
                for (int j = i + 1; j < requestUnico.length; j++) {
                    int first = getShelfPos(genome, requestUnico[i]);
                    int second = getShelfPos(genome, requestUnico[j]);
                    Cell firstCell = problem.getShelves().get(first);
                    Cell secondCell = problem.getShelves().get(second);
                    pathCost += problem.getPair(firstCell, secondCell).getValue();
                }
            }
            //adiciona ainda a distância da porta ao primeiro e do ultimo á porta
            pathCost += problem.getPair(problem.getShelves().get(getShelfPos(genome, requestUnico[0])), problem.getDoor()).getValue();
            pathCost += problem.getPair(problem.getDoor(), problem.getShelves().get(getShelfPos(genome, requestUnico[requestUnico.length - 1]))).getValue();
        }
        return 1/pathCost;
    }

    public static int getShelfPos(int[] genome, int value) {
        //procura o value no genoma
        for (int i=0; i<genome.length ;i++) {
            if (genome[i] == value) {
                return i;
            }
        }
        //caso não encontre devolve a posição -1
        return NAO_ENCONTRADO;
    }

    //Return the product Id if the shelf in cell [line, column] has some product and 0 otherwise
    public int getProductInShelf(int line, int column) {
        //percorre todas as shelves
        LinkedList<Cell> shelves = new LinkedList<>(problem.getShelves());
        for (int i = 0; i < shelves.size(); i++) {
            //caso exista a shelf na [line, column] verifica se o seu produto é maior que 0 e devolve o int
            if (shelves.get(i).getLine() == line && shelves.get(i).getColumn() == column) {
                return genome[i] > 0 ? genome[i] : 0;
            }
        }
        //caso a shelf não exista devolve não encontrado
        return NAO_ENCONTRADO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i < genome.length; i++) {
            sb.append(genome[i]).append(" ");
            //this method might require changes
        }

        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(WarehouseIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public WarehouseIndividual clone() {
        return new WarehouseIndividual(this);

    }
}
