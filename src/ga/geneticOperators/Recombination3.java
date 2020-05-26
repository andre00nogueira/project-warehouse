package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

import java.util.LinkedList;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2;
    private LinkedList<Integer> cycle1, cycle2;

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];
        findCycles(ind1, ind2);
    }

    private void findCycles(I parent1, I parent2) {
        cycle1 = new LinkedList<>();
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

    @Override
    public String toString() {
        return "CX";
    }
}