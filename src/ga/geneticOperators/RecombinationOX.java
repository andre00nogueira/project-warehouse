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

        for (int i = cut1; i < cut2; i++) {
            child1[i] = segment1[i-cut1];
        }

        int i = 0;
        do {
            int j = 0;
            while (checkDuplicates(child1, i)){
                j++;
                child1[i] = ind2.getGene(i+j);
            }
            i++;
        }while (i < cut1);

        System.out.println(child1);

        i = cut2;
        do{
            int j = 0;
            child1[i] = ind2.getGene(i);
            while (checkDuplicates(child1, i)){
                child1[i] = ind2.getGene(j);
                j++;
            }
            i++;
        }while (i < ind2.getNumGenes());

        System.out.println(child1);



        for (i = cut1; i < cut2; i++) {
            child2[i] = segment1[i-cut1];
        }

        i = 0;
        do {
            int j = 0;
            while (checkDuplicates(child2, i)){
                j++;
                child2[i] = ind2.getGene(i+j);
            }
            i++;
        }while (i < cut1);

        System.out.println(child2);

        i = cut2;
        do{
            int j = 0;
            child2[i] = ind2.getGene(i);
            while (checkDuplicates(child2, i)){
                child2[i] = ind2.getGene(j);
                j++;
            }
            i++;
        }while (i < ind2.getNumGenes());



    }


    private boolean checkDuplicates(int[] offspring, int indexOfElement) {
        for (int index = 0; index < offspring.length; index++) {
            if ((offspring[index] == offspring[indexOfElement]) &&
                    (indexOfElement != index)) {
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
    public String toString(){
        return "OX";
    }    
}