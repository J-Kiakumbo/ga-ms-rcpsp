package msrcpsp.ga;

import java.util.List;
import java.util.Random;

import msrcpsp.scheduling.Resource;
import msrcpsp.scheduling.Task;
import msrcpsp.scheduling.greedy.Greedy;

public class GA {
	public static Task[] allTasks;
	public static Resource[] allResources;
	public static double crossoverRate;
	public static double mutationRate;
	public static int turnSize;
	
	public static Population newPopulation(Population population, boolean elitismo) {
        Random r = new Random();
        //nova população do mesmo tamanho da antiga
        Population newPopulation = new Population(population.getPop_size());

        //se tiver elitismo, mantém o melhor indivíduo da geração atual
        if (elitismo) {
            newPopulation.setIndividual(population.getIndivdual(0));
        }

        //insere novos indivíduos na nova população, até atingir o tamanho máximo
        while (newPopulation.getNumIndividuals() < newPopulation.getPop_size()) {
            //seleciona os 2 pais por torneio
            Individual[] parent = selectTorneio(population,turnSize);
            

            Individual[] sons = new Individual[2];

            //verifica a taxa de crossover, se sim realiza o crossover, se não, mantém os pais selecionados para a próxima geração
            if (r.nextDouble() <= crossoverRate) {
                sons = crossover(parent[1], parent[0]);
                if(r.nextDouble() <= mutationRate){
                	sons[0]=doMutation(sons[0]);
                	sons[1]=doMutation(sons[1]);
                }
            } else {
                sons[0] = new Individual(parent[0].getTask(),parent[0].getResource());
                sons[1] = new Individual(parent[1].getTask(),parent[1].getResource());
                if(r.nextDouble() <= mutationRate){
                	sons[0]=doMutation(sons[0]);
                	sons[1]=doMutation(sons[1]);
                }
            }
            
            
            //adiciona os filhos na nova geração
            newPopulation.setIndividual(sons[0]);
            newPopulation.setIndividual(sons[1]);
        }

        //ordena a nova população
        newPopulation.orderPopulation();
        return newPopulation;
    }

    public static Individual[] crossover(Individual individual1, Individual individual2) {
        Random r = new Random();

        //sorteia o ponto de corte
        int pointOfCut1 = r.nextInt((individual1.getGenes().getResources().length/2) -2) + 1;
       // int pointOfCut2 = r.nextInt((individual1.getGenes().getResources().length/2) -2) + individual1.getGenes().getResources().length/2;

        Individual[] sons = new Individual[2];
        Task[] tasks = individual1.getTask();
        //pega os genes dos pais
        Resource[] geneParent1 = individual1.getGenes().getResources();
        Resource[] geneParent2 = individual2.getGenes().getResources();

        Resource[] geneSon1 = new Resource[geneParent1.length];
        Resource[] geneSon2 =new Resource[geneParent2.length];

        //realiza o corte,
        for(int i= 0;i < pointOfCut1;i++)
        	geneSon1[i] = geneParent1[i];
  
        
        for(int i= pointOfCut1;i < geneParent1.length;i++)
        	geneSon1[i] = geneParent2[i];
        
        for(int i= 0;i < pointOfCut1;i++)
        	geneSon2[i] = geneParent2[i];
  
        
        for(int i= pointOfCut1;i < geneParent2.length;i++)
        	geneSon2[i] = geneParent1[i];
        
       

        //cria o novo indivíduo com os genes dos pais
        sons[0] = new Individual(tasks,geneSon1);
        sons[1] = new Individual(tasks,geneSon2);

        return sons;
    }
    
       public static Individual doMutation(Individual individual){
    	   
    	   int[] upperBounds = individual.getGenes().getUpperBounds(individual.getTask().length);
 		  Random random = new Random(System.currentTimeMillis());
 		    List<Resource> capableResources;
 		    for (int i = 0; i < individual.getTask().length; ++i) {
 		      capableResources = individual.getGenes().getCapableResources(individual.getTask()[i]);
 		      individual.getGenes().assign(individual.getTask()[i], capableResources.get((int)(random.nextDouble() * upperBounds[i])));
 		    }
 		    
 		Greedy greedy = new Greedy(individual.getGenes().getSuccesors());
 		greedy.buildTimestamps(individual.getGenes());
    	   
    	   return individual;
       }

    public static Individual[] selectTorneio(Population populacao,int n) {
        Random r = new Random();
        Population IntermidiaPopulation = new Population(n,true);

        //seleciona 3 indivíduos aleatóriamente na população
        for(int i = 0 ; i < n;i++)
        IntermidiaPopulation.setIndividual(populacao.getIndivdual(r.nextInt(populacao.getPop_size())));
        

        //ordena a população
        IntermidiaPopulation.orderPopulation();

        Individual[] parent = new Individual[2];

        //seleciona os 2 melhores deste população
        parent[0] = IntermidiaPopulation.getIndivdual(0);
        parent[1] = IntermidiaPopulation.getIndivdual(1);

        return parent;
    }

	public static Task[] getAllTasks() {
		return allTasks;
	}

	public static void setAllTasks(Task[] allTasks) {
		GA.allTasks = allTasks;
	}

	public static Resource[] getAllResources() {
		return allResources;
	}

	public static void setAllResources(Resource[] allResources) {
		GA.allResources = allResources;
	}

	public static double getCrossoverRate() {
		return crossoverRate;
	}

	public static void setCrossoverRate(double crossoverRate) {
		GA.crossoverRate = crossoverRate;
	}

	public static double getMutationRate() {
		return mutationRate;
	}

	public static void setMutationRate(double mutationRate) {
		GA.mutationRate = mutationRate;
	}

	public static int getTurnSize() {
		return turnSize;
	}

	public static void setTurnSize(int turnSize) {
		GA.turnSize = turnSize;
	}
	
    
    
}
