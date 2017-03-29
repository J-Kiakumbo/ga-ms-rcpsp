package msrcpsp.ga;

import java.util.List;
import java.util.Random;

import msrcpsp.evaluation.BaseEvaluator;
import msrcpsp.evaluation.DurationEvaluator;
import msrcpsp.scheduling.Resource;
import msrcpsp.scheduling.Schedule;
import msrcpsp.scheduling.Task;
import msrcpsp.scheduling.greedy.Greedy;

public class Individual {
	private int rating;
	private Schedule genes;
	private int duration;
	
	public Individual(){
		Task[] tasks = GA.allTasks;
		Resource[] resources = GA.allResources;
		genes=new Schedule(tasks, resources);
		int[] upperBounds = genes.getUpperBounds(tasks.length);
		  Random random = new Random(System.currentTimeMillis());
		    List<Resource> capableResources;
		    for (int i = 0; i < tasks.length; ++i) {
		      capableResources = genes.getCapableResources(tasks[i]);
		      genes.assign(tasks[i], capableResources.get((int)(random.nextDouble() * upperBounds[i])));
		    }
		Greedy greedy = new Greedy(genes.getSuccesors());
		greedy.buildTimestamps(genes);
		BaseEvaluator evaluator = new DurationEvaluator(genes);
		
		this.duration = evaluator.getDuration();
	}
	
	// Criar um individuo com os genes definidos
	
	public Individual(Task[] tasks, Resource[] resources){
		Schedule genes = new Schedule(tasks,resources);
		for (int i = 0; i < genes.getResources().length; i++){
			genes.assign(tasks[i], resources[i]);
		}
		this.genes = genes;
		Greedy greedy = new Greedy(this.genes.getSuccesors());
		greedy.buildTimestamps(this.genes);
		BaseEvaluator evaluator = new DurationEvaluator(this.genes);
		System.out.println("czas Optmalna: " +evaluator.getDurationNormalized() );
		this.duration = evaluator.getDuration();
	}
	
	public Task[] getTask(){
		return this.genes.getTasks();
	}
	
	public Resource[] getResource(){
		return this.genes.getResources();
	}
	
	public Schedule getGenes() {
		return genes;
	}

	public int getRating(){
		return this.rating;
	}
	public int getDuration(){
		return this.duration;
	}

}
