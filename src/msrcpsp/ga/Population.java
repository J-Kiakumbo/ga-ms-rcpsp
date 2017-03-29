package msrcpsp.ga;

public class Population {
	private Individual[] population;
	private int pop_size;
	private double best = Double.MAX_VALUE;
	private double avarege = 0.0;
	private double worst = 0.0;
	
	
	
	public Population(int pop_size) {
		this.population = new Individual[pop_size];
		this.pop_size = pop_size;
		for(int i = 0; i < pop_size; i++)
			this.population[i]= new Individual();
		
		orderPopulation();
		setStatistics();
	}
	//cria uma população com indivíduos sem valor, será composto posteriormente
    public Population(int pop_size, boolean t ) {
        this.pop_size = pop_size;
        this.population = new Individual[pop_size];
        for (int i = 0; i < this.population.length; i++) {
            this.population[i] = null;
        }
    }
	
	private void setStatistics(){
		for(int i = 0; i < this.pop_size; i++){
			if(this.best > this.population[i].getDuration()){
				this.best = this.population[i].getDuration();
			}
			
			if(this.worst < this.population[i].getDuration()){
				this.worst = this.population[i].getDuration();
			}
			 this.avarege += this.population[i].getDuration();	
		}
		
		this.avarege /=this.pop_size;
	}
	
	public void orderPopulation(){
		boolean chenged =true;
		while(chenged){
			chenged=false;
		for( int i = 0; i < this.pop_size-1; i++){
			Individual temp = null;
			if(this.population[i].getDuration() > this.population[i+1].getDuration()){
				temp = this.population[i];
				this.population[i] = this.population[i+1];
				this.population[i+1]= temp;
				chenged=true;
				
			}
		}
		}
		
	}
	//coloca um indivíduo em uma certa posição da população
    public void setIndividual(Individual individual, int position) {
        this.population[position] = individual;
    }

    //coloca um indivíduo na próxima posição disponível da população
    public void setIndividual(Individual individual) {
        for (int i = 0; i < this.population.length; i++) {
            if (this.population[i] == null) {
                this.population[i] = individual;
                return;
            }
        }
    }
    
  //número de indivíduos existentes na população
    public int getNumIndividuals() {
        int num = 0;
        for (int i = 0; i < this.population.length; i++) {
            if (this.population[i] != null) {
                num++;
            }
        }
        return num;
    }



    public Individual getIndivdual(int position) {
        return this.population[position];
    }

	public int getPop_size() {
		return pop_size;
	}
	
	
	public double getBest() {
		return best;
	}
	
	public double getAvarege() {
		return avarege;
	}
	
	public double getWorst() {
		return worst;
	}
	
	
	

}
