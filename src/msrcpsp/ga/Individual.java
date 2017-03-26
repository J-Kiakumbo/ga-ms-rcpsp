package msrcpsp.ga;

import java.util.Random;

import msrcpsp.evaluation.BaseEvaluator;
import msrcpsp.evaluation.DurationEvaluator;
import msrcpsp.scheduling.Resource;
import msrcpsp.scheduling.Schedule;
import msrcpsp.scheduling.Task;

public class Individual {
	private int rating;
	private Schedule genes;
	private int duration;
	
	public Individual(int genesSize){
		Task[] tasks = new Task[genesSize]; 
		Resource[] resources = new Resource[genesSize];
		Random r = new Random();
		for(int i = 0; i < genesSize; i++){
			tasks[i] = GA.allTasks[r.nextInt(GA.allTasks.length)];
			resources[i] = GA.allResources[r.nextInt(GA.allResources.length)];
		}
		
		this.genes = new Schedule(tasks, resources);
		for(int i = 0; i < tasks.length; i++)
			this.genes.assign(tasks[i], resources[i]);
		BaseEvaluator evaluator = new DurationEvaluator(this.genes);
		this.duration = evaluator.getDuration();
	}
	
	public Task[] getTask(){
		return this.genes.getTasks();
	}
	
	public Resource[] getResource(){
		return this.genes.getResources();
	}
	
	public int getRating(){
		return this.rating;
	}
	public int getDuration(){
		return this.duration;
	}

}
