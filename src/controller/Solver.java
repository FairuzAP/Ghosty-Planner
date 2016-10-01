package controller;

import model.ScheduleState;
import java.util.Random;

public class Solver {
    private ScheduleState problem;
    private ScheduleState solution;
    
    public final double e = 2.71828;

    //Constructor
    public Solver(ScheduleState s) {
	problem = new ScheduleState(s);
    }

    public ScheduleState getProblem() {
	return problem;
    }

    public ScheduleState getSolution() {
	return solution;
    }

    public void hillClimb(int maxstep) {
	ScheduleState current = new ScheduleState(problem);
	solution = new ScheduleState(problem);

	int step = 0;
	int courseID = 1;
	do {
	    
	    solution.reasignBestCourseFor(courseID);
	    
	    //check constraints
	    if (current.countConflicts() > solution.countConflicts()) {
		current = new ScheduleState(solution);
		courseID = 1;
		step++;
	    } else courseID++;
	    
	} while (solution.countConflicts()>0 && step<maxstep && courseID<=problem.getCourseMade());//STOPS when no better neighbors
    }

    public void simulatedAnnealing(double T, double ratioTemp, int pass, int hillclimb) {
	
	solution = new ScheduleState(problem);
	ScheduleState neighbor;
	
	int maxRand = 100000;
	double epsilon = 0.0000000001;	
	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());
	
	int debuggood=0;
	int debugbadhit=0;
	int debugpassbad=0;
	
	int counter = 0;
	do {
	    
	    // Temperature checking
	    if (T <= epsilon) {
		if(hillclimb > 0) {
		    problem = new ScheduleState(solution);
		    hillClimb(hillclimb);
		}
		break;
	    }
	    
	    // generate random neighbor
	    neighbor = new ScheduleState(solution.generateRandomChildState());
	    
	    // count deltaE
	    double deltaE = solution.countConflicts() - neighbor.countConflicts();
	    
	    if (deltaE > 0) { debuggood++;
	    
		solution = new ScheduleState(neighbor);
		
	    } else { debugbadhit++;
		
		// n is a random number between 0 to 10001
		double n = rand.nextInt(maxRand+1);
		
		// x is a boolean that returns "true" if n is lower than 10000*e^deltaE/T
		double tempa = Math.pow(e,deltaE/T);
		double tempb = n/maxRand;
		
		if (tempb <= tempa) { debugpassbad++;
		
		    solution = new ScheduleState(neighbor);
		    
		}
	    }
	    
	    counter++;
	    if(counter >= pass) {
		T *= ratioTemp;
		counter = 0;
	    }
	}
	while (solution.countConflicts() > 0); //STOPS when no conflicts
	
    }
    
    
    public void GeneticAlgorithm(int maxPop, int selectionLimit, int maxIter) {
	GeneticHelper H = new GeneticHelper(problem, maxPop, selectionLimit, maxIter);
	solution = new ScheduleState(H.evolution());
    }
    
}
