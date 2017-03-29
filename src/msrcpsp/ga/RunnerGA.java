package msrcpsp.ga;


import msrcpsp.scheduling.Schedule;



public class RunnerGA {
	
	 
	  
  private static int geracao = 0;


	public static Schedule run( Schedule schedule, int pop_size, double pm, double px, int nrGeneration,int turnSize) {

        //Define a solução
       GA.setAllTasks(schedule.getTasks());
        //Define os caracteres existentes
       GA.setAllResources(schedule.getResources());
        //taxa de crossover
        GA.setCrossoverRate(px);;
        //taxa de mutação 
        GA.setMutationRate(pm);;
        GA.setTurnSize(turnSize);
        //elitismo
        boolean eltismo = true;
        //tamanho da população
        int pop_Size = pop_size;
        //numero máximo de gerações
        int tN = nrGeneration;
        Population populationSol = null;
        double dur=Double.MAX_VALUE;

        //define o número de genes do indivíduo baseado na solução
        //int numGenes = Algorytm.getTask().length;

        //cria a primeira população aleatérioa
        Population population = new Population(pop_Size);

  
        

       // System.out.println("Iniciando... Aptidão da solução: "+Algorytm.getSolution().length);
      
            //cria nova populacao
            
            
            while (geracao < nrGeneration) {
            	
            	
            
                geracao++;

                //cria nova populacao
                population = GA.newPopulation(population, eltismo);
                
                System.out.println("\nGeração " + geracao + " | Best: " +population.getBest() + " Average: " +population.getAvarege()+"Worst: "+population.getWorst());
                
                if(dur > population.getBest()){
                	populationSol =population;
                	dur = population.getBest();
                }
               
               if (geracao == nrGeneration) {
                  // System.out.println("Número Maximo de Gerações | " + " ");
            	   System.out.println("A duracao do escolhido eh: " + populationSol.getIndivdual(0).getDuration());
            	   return populationSol.getIndivdual(0).getGenes();

            }
        
    }
			return null;
	}
	}
    
    
    
    
    
        
        
