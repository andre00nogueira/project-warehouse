package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

import java.util.LinkedList;

public class RecombinationCX<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2;
    private LinkedList<Integer> cycle2;

    public RecombinationCX(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];
        findCycles(ind1, ind2);
        if (cycle2.isEmpty()) {
            return;
        }
        crossOver(child1, child2, ind1, ind2);
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }
    }

    private void findCycles(I parent1, I parent2) {
        LinkedList<Integer> cycle1 = new LinkedList<>();
        int first = parent1.getGene(0);
        cycle1.add(first);
        int index = 0;
        int valueParent2 = parent2.getGene(index);
        while (first != valueParent2) {
            cycle1.add(valueParent2);
            index = parent1.getIndexof(valueParent2);
            valueParent2 = parent2.getGene(index);
        }
        cycle2 = new LinkedList<>();
        int oldFirst = first;
        for (int i = 1; i < parent1.getNumGenes(); i++) {
            if (!cycle1.contains(parent1.getGene(i))) {
                first = parent1.getGene(i);
                index = i;
                break;
            }
        }
        if (oldFirst == first) {
            return;
        }
        cycle2.add(first);
        valueParent2 = parent2.getGene(index);
        while (first != valueParent2) {
            cycle2.add(valueParent2);
            index = parent1.getIndexof(valueParent2);
            valueParent2 = parent2.getGene(index);
        }
    }

    public void crossOver(int[] offspring1, int[] offspring2, I ind1, I ind2) {
        for (int i = 0; i < offspring1.length; i++) {
            if (cycle2.contains(ind1.getGene(i))) {
                offspring1[i] = ind2.getGene(i);
                offspring2[i] = ind1.getGene(i);
            } else {
                offspring1[i] = ind1.getGene(i);
                offspring2[i] = ind2.getGene(i);
            }
        }
    }

    @Override
    public String toString() {
        return "CX";
    }
}