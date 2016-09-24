package controller;

import model.ScheduleState;
import java.util.Random;
import java.lang.Math;
import java.util.Vector;

public class Solver {
	private ScheduleState problem;
	private ScheduleState solution;
	
	public final double e = 2.71828;
	
	//Constructor
	public Solver(ScheduleState s) {
		problem = new ScheduleState(s);
		solution = new ScheduleState();
	}
	
	public ScheduleState getProblem() {
		return problem;
	}
	
	public ScheduleState getSolution() {
		return solution;
	}
	
	public void hillClimb() {
		ScheduleState current = new ScheduleState(problem);
		ScheduleState neighbor = new ScheduleState(current);
		
		do {
			//search for better neighbor
			for (int i = 1; i <= neighbor.getCourseMade(); i++) {
				neighbor.reasignBestCourseFor(i);
			}
			
			//check constraints
			if (current.countConflicts() > neighbor.countConflicts()) {
				current = new ScheduleState(neighbor);
			}
			//STOPS when no better neighbors
		} while (current.countConflicts() > neighbor.countConflicts());
		
		solution = new ScheduleState(current);
	}
	
	public void simulatedAnnealing() {
		ScheduleState current = new ScheduleState(problem);
		ScheduleState neighbor = new ScheduleState();
		int T;
		int deltaE;
		boolean x = false;
		int initConstraint = current.countConflicts();
		
		do {
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			T = rand.nextInt(100);
			
			//Temperature checking
			if (T == 0) break;
			
			//generate random neighbor
			neighbor = new ScheduleState(current.generateRandomChildState());
			
			//count deltaE
			deltaE = current.countConflicts() - neighbor.countConflicts();
			if (deltaE > 0) {
				current = new ScheduleState(neighbor);
			} else {
				Random r = new Random();
				r.setSeed(System.currentTimeMillis());
				
				//n is a random number between 0 to 10001
				int n = r.nextInt(10000)+1;
				
				//x is a boolean that returns "true" if n is lower than 10000*e^deltaE/T
				x = (n - (int) (10000 * Math.pow(e,deltaE/T)) > 0);
				if (x) {
					current = new ScheduleState(neighbor);
				}
			}
		}
		while (/* all neighbors are lower */x || current.countConflicts()<=initConstraint);
		
		solution = new ScheduleState(current);
	}
	
	/*public void genetic() {
		int a, i, randomNumber, conflictSum, temp;
		Vector<Integer> conflictList = null;
		Random randomizer = new Random();
		Vector<ScheduleState> child = null;
		ScheduleState x, y = null;
		Vector<ScheduleState> population = {								//bikin populasi, coba benerin
			new ScheduleState(),
			new ScheduleState(),
			new ScheduleState(),
			new ScheduleState(),
		};
		
		do {
			//make new population
			Vector<ScheduleState> new_population = null;					//bikin populasi (baru), coba benerin
			
			conflictSum = 0;
			temp = 0;
			for (a = 0; a < population.size(); a++) { 						//ngitung total constraint
				temp = population[a].countConflicts();
				conflictSum += temp;
				conflictList.add(temp);
			}
				
			for (a = 0; a < population.size(); a++) {
				//pilih 2 random individu x & y dengan randomisasi
				randomNumber = randomizer.nextInt(randomLimit);				//coba diperpendek
				
				if (randomNumber < (conflictList[0] * randomLimit)) {
					x = population[0];
				} else if (randomNumber < (conflictList[1] * randomLimit)) {
					x = population[1];
				} else if (randomNumber < (conflictList[2] * randomLimit)) {
					x = population[2];
				} else 
					x = population[3];
				}
				
				randomNumber = randomizer.nextInt(randomLimit);
				
				if (randomNumber < (conflictList[0] * randomLimit)) {
					y = population[0];
				} else if (randomNumber < (conflictList[1] * randomLimit)) {
					y = population[1];
				} else if (randomNumber < (conflictList[2] * randomLimit)) {
					y = population[2];
				} else 
					y = population[3];
				}
				
				
				//child <- Reproduce(x,y)
				child = Reproduce (x,y);
				
				// mutasi
				i = 0;
				repeat {
					if (randomizer.nextInt(randomLimit) > ((child[i].countConflicts() / conflictSum) * randomLimit)) {
						Mutate(child[i]);
					}
					
					i++;
				} while (i < 2);
				
				// add child to new population
				new_population.add(child[0]);
				new_population.add(child[1]);
				
				child = null;
			}
			
			//destroy population											//finalisasi iterasi
			population = null;
			
			//population <- new population
			population = new_population;
			
			//destroy all new population
			new_population = null;
		} while (/* batas jumlah konstrain semua individu dalam populasi);
		
	}
	
	private Vector<ScheduleState> reproduce(ScheduleState x, ScheduleState y) {
		Vector<ScheduleState> offspring = {new ScheduleState(), new ScheduleState()};
		
		//ASUMSI urutan course dan room sama
		randomNumber = randomize.nextInt(randomLimit);
		
		int i = 0;
		do {
			i++;
		} while ((i * 1000) < (randomNumber/x.getCourseMade().size()));
		
		while (i < x.getCourseMade().size()) {
			//tuker2 nilai			
			offspring[0].reOrderClass(, , , );
			offspring[1].reOrderClass(, , , );								//butuh getter
			
			i++;
		}
		
		return offspring;
	}
	
	private void mutate (ScheduleState mutatee) {
		Random randomize = new Random();
		int randomNumber = randomize.nextInt(randomLimit);
		
		int i = 0;
		do {
			i++;
		} while ((i * 1000) < (randomNumber/mutatee.getCourseMade());
		
		mutatee.reasignBestCourseFor((mutatee.getCourseMade())[i].name);	//agak ragu dengan akses vektornya
	}*/
}
