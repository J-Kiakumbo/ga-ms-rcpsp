package msrcpsp.ga;

public class Population {
	private Individual[] population;
	private int pop_size;
	
	
	
	
	public Population(int pop_size, int n) {
		population = new Individual[pop_size];
		this.pop_size = pop_size;
		for(int i = 0; i < pop_size; i++)
			population[i]= new Individual();
		
		orderPopulation();
	}
	//cria uma população com indivíduos sem valor, será composto posteriormente
    public Population(int pop_size) {
        this.pop_size = pop_size;
        population = new Individual[pop_size];
        for (int i = 0; i < population.length; i++) {
            this.population[i] = null;
        }
    }
	
	
	public void orderPopulation(){
		boolean chenged =true;
		while(chenged){
			chenged=false;
			for( int i = 0; i < pop_size-1; i++){
				 
				if(population[i].getDuration() > population[i+1].getDuration()){
					Individual temp = population[i];
					population[i] = population[i+1];
					population[i+1]= temp;
					chenged=true;
					
				}
			}
		}
		
	}
	//coloca um indivíduo em uma certa posição da população
    public void setIndividual(Individual individual, int position) {
        population[position] = individual;
    }

    //coloca um indivíduo na próxima posição disponível da população
    public void setIndividual(Individual individual) {
        for (int i = 0; i < population.length; i++) {
            if (population[i] == null) {
                population[i] = individual;
                return;
            }
        }
    }
    
  //número de indivíduos existentes na população
    public int getNumIndividuals() {
        int num = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i] != null) {
                num++;
            }
        }
        return num;
    }



    public Individual getIndividual(int position) {
        return population[position];
    }
    
	public int getPop_size() {
		return pop_size;
	}
	
	

	
	
	

}
