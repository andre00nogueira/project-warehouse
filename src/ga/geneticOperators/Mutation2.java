package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.LinkedList;

public class Mutation2<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation2(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //procura todas as shelves que se encontram vazias
        LinkedList<Integer> emptyShelves = new LinkedList<>();
        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (ind.getGene(i) == 0) {
                emptyShelves.add(i);
            }
        }
        //seleciona aleatóriamente uma das prateleiras vazias
        int indEmpty = GeneticAlgorithm.random.nextInt(emptyShelves.size());
        int indProduct;
        //seleciona uma prateleira com produto aleatóriamente
        do {
            indProduct = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while (ind.getGene(indProduct) == 0);
        //troca a posição da prateleira vazia com um produto
        int aux = ind.getGene(indEmpty);
        ind.setGene(indEmpty, ind.getGene(indProduct));
        ind.setGene(indProduct, aux);
    }

    @Override
    public String toString() {
        return "Empty";
    }
}