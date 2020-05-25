package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class MutationInversion<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationInversion(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int cut1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int cut2;
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while (cut1 == cut2);
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }
        int mid = cut1 + ((cut2 + 1) - cut1) / 2;
        int last = cut2;
        for (int i = cut1; i < mid; i++) {
            int aux = ind.getGene(i);
            ind.setGene(i, ind.getGene(last));
            ind.setGene(last, aux);
            last--;
        }
    }

    @Override
    public String toString() {
        return "Inversion";
    }
}