package msrcpsp.ga;

public class Population {
	private Individual[] population;
	private int pop_size;
	private double best = Double.MAX_VALUE;
	private double avarege = 0.0;
	private double worst = 0.0;
	
	
	
	public Population(int pop_size, int genesSize) {
		super();
		this.population = new Individual[pop_size];
		this.pop_size = pop_size;
		for(int i = 0; i < pop_size; i++)
			this.population[i]= new Individual(genesSize);
		
		setStatistics();
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
