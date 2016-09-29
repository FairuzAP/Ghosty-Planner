/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Vector;
import model.ScheduleState;

/**
 *
 * @author USER
 */
class GeneticHelper {

    int maxPopulation;
    int selectionNumber;
    int maxIteration;
    
    int currIteration;
    
    private static class ScheduleStatusComparator implements Comparator<ScheduleState> {
	@Override
	public int compare(ScheduleState x, ScheduleState y) {
	    if (x.countConflicts() < y.countConflicts())
	    {
		return -1;
	    }
	    if (x.countConflicts() > y.countConflicts())
	    {
		return 1;
	    }
	    return 0;
	}
    }
    private final ScheduleStatusComparator MyComp = new ScheduleStatusComparator();
    private PriorityQueue<ScheduleState> Repository = new PriorityQueue<>(1,MyComp);
    
    ScheduleState problem;
    
    
    GeneticHelper(ScheduleState p, int mP, int s, int mI) {
	maxPopulation = mP;
	selectionNumber = s;
	maxIteration = mI;
	problem = new ScheduleState(p);
	currIteration = 0;
	
	for(int i=0; i<maxPopulation; i++) {
	    ScheduleState temp = new ScheduleState(problem);
	    temp.massReorder();
	    Repository.add(temp);
	}
    }
    
    
    private void naturalSelection() {
	PriorityQueue<ScheduleState> temp = new PriorityQueue<>(1,MyComp);
	for(int i=0; i<selectionNumber; i++) {
	    temp.add(Repository.remove());
	}
	Repository = temp;
    }
    
    
    private Vector<ScheduleState> Reproduce(ScheduleState x, ScheduleState y) {
	
	Vector<ScheduleState> offspring = new Vector<ScheduleState>();
	offspring.add(new ScheduleState(x));
	offspring.add(new ScheduleState(y));
	
	// ASUMSI urutan course dan room sama
	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());
	int i = rand.nextInt(x.getCourseMade()) + 1;

	while (i < x.getCourseMade()) {
	    offspring.elementAt(0).reOrderClass(i+1, y.getCourse(i).getActualCourseClass().ID, y.getCourse(i).getActualCourseDay(), y.getCourse(i).getActualCourseTime());
	    offspring.elementAt(1).reOrderClass(i+1, x.getCourse(i).getActualCourseClass().ID, x.getCourse(i).getActualCourseDay(), x.getCourse(i).getActualCourseTime());

	    i++;
	}

	return offspring;
    }
    
    private void rePopulate() {
	ScheduleState[] tempPop = Repository.toArray(new ScheduleState[0]);
	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());
	
	while(Repository.size() < maxPopulation) {
	    int i = rand.nextInt(selectionNumber);
	    int j = rand.nextInt(selectionNumber);
	    Repository.addAll(Reproduce(tempPop[i],tempPop[j]));
	}
    }
    
    
    private void massMutation() {
	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());
	
	PriorityQueue<ScheduleState> temp = new PriorityQueue<>(1,MyComp);
	int i = 0;
	for(; i<selectionNumber; i++) {
	    temp.add(Repository.remove());
	}
	for(; i<maxPopulation; i++) {
	    /* 
	    ScheduleState tempState = Repository.remove();
	    tempState.reasignBestCourseFor(rand.nextInt(tempState.getCourseMade())+1);
	    temp.add(tempState);
	    */ 
	    temp.add(Repository.remove().generateRandomChildState());
	}
	Repository = temp;
    }
    
    
    public ScheduleState evolution() {
	
	while(currIteration < maxIteration) {
	    naturalSelection();
	    rePopulate();
	    massMutation();
	    currIteration++;
	    if(Repository.peek().countConflicts() == 0) break;
	}
	return Repository.remove();
    }

}
