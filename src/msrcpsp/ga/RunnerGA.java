package msrcpsp.ga;


import org.jfree.data.xy.XYSeries;

import graphics.Graphic;
import msrcpsp.scheduling.Schedule;



public class RunnerGA {
	
	 
	  
  private static int geracao = 0;


	public static Schedule run( Schedule schedule, int pop_size, double px, double pm, int nrGeneration,int turnSize) {

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
        Population population = new Population(pop_Size,tN);

        final XYSeries best2 = new XYSeries( "Best" );
        final XYSeries average2 = new XYSeries( "Average" );
        final XYSeries wrost2 = new XYSeries( "Wrost" );
       
        

       // System.out.println("Iniciando... Aptidão da solução: "+Algorytm.getSolution().length);
      
            //cria nova populacao
            
            
            while (geracao < nrGeneration) {
            	
                geracao++;
                
                double best = Double.MAX_VALUE;
            	double average = 0.0;
            	double worst = 0.0;
                
                //cria nova populacao
                population = GA.newPopulation(population, eltismo);
                
                for(int i = 0; i <pop_size; i++){
        			if(best > population.getIndividual(i).getDuration()){
        				best = population.getIndividual(i).getDuration();
        			}
        			
        			if(worst < population.getIndividual(i).getDuration()){
        				worst = population.getIndividual(i).getDuration();
        			}
        			 average += population.getIndividual(i).getDuration();	
        		}
        		
        		average /=pop_size;
                
                System.out.println("\nGeração " + geracao + " | Best: " +best + " Average: " +average+"Worst: "+worst);
                best2.add( geracao , best);
                average2.add( geracao , average );
                wrost2.add( geracao ,worst );
                
                if(dur > best){
                	populationSol =population;
                	dur = best;
                }
               
               if (geracao == nrGeneration) {
                  // System.out.println("Número Maximo de Gerações | " + " ");
            	   System.out.println("A duracao do escolhido eh: " + populationSol.getIndividual(0).getDuration());
            	   try {
					Graphic.drowGraphic(best2, average2, wrost2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	   return populationSol.getIndividual(0).getGenes();

            }
        
    }
			return null;
	}
	}
    
    
    
    
    
        
        
