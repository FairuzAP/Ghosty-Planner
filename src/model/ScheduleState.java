/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class ScheduleState {
    
    /** All the room that is available in the schedule */
    private final List<ClassRoom> availableRoom;
    
    /** All the course that must be scheduled */
    private final List<Courses> courseList;
    
    
    /** Ctor, initialize array */
    public ScheduleState() {
	availableRoom = new Vector<>();
	courseList = new Vector<>();
    }
    
    /** @return true if the argument is valid and the class is added, false if not */
    public boolean addClass(String className, int startHour, int endHour, Vector<Integer> openDayList){
	if((className.isEmpty())||(startHour<6)||(endHour>17)||(startHour>endHour)||(openDayList.isEmpty())) 
	    return false;
	else {
	    availableRoom.add(new ClassRoom(className, startHour, endHour, openDayList));
	    return true;
	}
    }
    
    /** @return true if the argument is valid and the class is added, false if not */
    public boolean addCourse(String courseName, Vector<Integer> allowedClassID, int startHour, int endHour, int duration, Vector<Integer> openDayList){
	if((courseName.isEmpty())||(startHour<6)||(endHour>17)||(startHour>endHour)||(openDayList.isEmpty())||(duration<1)||(duration>11)) 
	    return false;
	else {
	    
	    boolean validClassID = true;
	    for(int i : allowedClassID) {
		if(i>ClassRoom.classMade) {
		    validClassID = false;
		    break;
		}
	    }
	    
	    if(!validClassID) return false;
	    else {
		courseList.add(new Courses(courseName, allowedClassID, startHour, endHour, duration, openDayList));
		return true;
	    }
	}
    }
    
    /** @return The number of constraint conflict in classess */
    public int countConflicts() {
	int sum = 0;
	for(ClassRoom c : availableRoom) {
	    sum+=c.countConflicts();
	}
	return sum;
    }
    
    /**
     * Assign schedule to each classes randomly 
     */
    public void initialize() {
	for(Courses c : courseList) {
	    Random r = new Random();
	    
	    int classID = c.allowedClass.get(r.nextInt(c.allowedClass.size()));
	    ClassRoom sClass = availableRoom.get(classID-1);
	    int startDay = r.nextInt(5);
	    int startHour = 7 + r.nextInt(11-c.duration);
	    
	    c.setClass(sClass, startDay, startHour);
	    sClass.orderClass(startDay, startHour, c.duration);
	}
    }
    
    /**
     * Change this state to a best posible state by moving one courses schedule
     * to a different time period so that the conflict count decreases as much
     * as possible
     * @return The amount of conflict removed
     */
    public int generateBestChildState() {
	return -1;
    }
    
    /**
     * @return another state by moving one courses schedule to a different time 
     * period randomly from this state
     */
    public ScheduleState generateRandomChildState() {
	return null;
    }
    
}
