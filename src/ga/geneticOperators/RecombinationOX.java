package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class RecombinationOX<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2, segment1, segment2;

    private int cut1;
    private int cut2;

    public RecombinationOX(double probability) {
        super(probability);
    }


    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];

        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        } while (cut1 == cut2);
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        create_Segments(cut1, cut2, ind1, ind2);

        crossOver(child1, ind2, segment1);
        crossOver(child2, ind1, segment2);

    }


    public void crossOver(int[] offspring, I ind, int[] segment) {
        for (int i = cut1; i < cut2; i++) {
            offspring[i] = segment[i - cut1];
        }

        int[] segmentAux = new int[ind.getNumGenes() - (cut2 - cut1)];
        int j = 0;

        // Copiar para um array os elementos do pai2 que não estão no filho
        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (!check_Duplicates(offspring, ind.getGene(i))) {
                segmentAux[j] = ind.getGene(i);
                j++;
            }
        }

        // Copiar do pai 2, do 0 até ao cut1
        int k = 0;
        for (int i = 0; i < cut1; i++) {
            offspring[i] = segmentAux[i];
            k = i;
        }

        // Copiar do pai 2, do cut2 até ao tamanho do filho
        for (int i = cut2; i < offspring.length; i++) {
            offspring[i] = segmentAux[k];
        }

    }

    private boolean check_Duplicates(int[] offspring, int gene) {
        for (int index = 0; index < offspring.length; index++) {
            if (offspring[index] == gene){
                return true;
            }
        }
        return false;
    }
    

    private void create_Segments(int cutPoint1, int cutPoint2, I ind1, I ind2) {
        int capacity_ofSegments = (cutPoint2 - cutPoint1) + 1;
        segment1 = new int[capacity_ofSegments];
        segment2 = new int[capacity_ofSegments];
        int segment1and2Index = 0;
        for (int index = 0; index < ind1.getNumGenes(); index++) {
            if ((index >= cutPoint1) && (index <= cutPoint2)) {
                int x = ind1.getGene(index);
                int y = ind2.getGene(index);
                segment1[segment1and2Index] = x;
                segment2[segment1and2Index] = y;
                segment1and2Index++;
            }
        }
    }

    @Override
    public String toString() {
        return "OX";
    }
}