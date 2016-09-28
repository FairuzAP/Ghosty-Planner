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

    public void simulatedAnnealing(double initTemp, double ratioTemp, int pass) {
	
	solution = new ScheduleState(problem);
	ScheduleState neighbor;
	
	double T = initTemp;
	double deltaE;
	int maxRand = 10000;
	int counter = 0;
	
	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());
	
	int goodhit=0;
	int badhit=0;
	int passbadhit=0;
	
	do {
	    //Temperature checking
	    if (T <= 0.000001) {
		problem = new ScheduleState(solution);
		hillClimb(1000000);
		break;
	    }
	    
	    //generate random neighbor
	    neighbor = new ScheduleState(solution.generateRandomChildState());
	    
	    //count deltaE
	    deltaE = solution.countConflicts() - neighbor.countConflicts();
	    
	    if (deltaE > 0) {
		goodhit++;
		solution = new ScheduleState(neighbor);
	    } else {
		badhit++;
		//n is a random number between 0 to 10001
		double n = rand.nextInt(maxRand+1);
		
		//x is a boolean that returns "true" if n is lower than 10000*e^deltaE/T
		double tempa = Math.pow(e,deltaE/T);
		double tempb = n/maxRand;
		if (tempb >= tempa) {
		    solution = new ScheduleState(neighbor);
		    passbadhit++;
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

}
