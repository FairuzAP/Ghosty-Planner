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
    
    /** Ctor, initialize array */
    public ScheduleState(ScheduleState s) {
	availableRoom = new Vector<>();
	courseList = new Vector<>();
	
	for(ClassRoom c : s.availableRoom) {
	    availableRoom.add(new ClassRoom(c));
	}
	for(Courses c : s.courseList) {
	    Courses temp = new Courses(c);
	    temp.setClass(c.getActualCourseClass(), c.getActualCourseDay(), c.getActualCourseTime());
	    courseList.add(temp);
	}
    }
    
    
    private boolean orderClass(int CourseID, int ClassID, int startDay, int startHour) {
	if(courseList.get(CourseID-1).setClass(availableRoom.get(ClassID-1), startDay, startHour)) {
	    availableRoom.get(ClassID-1).orderClass(startDay, startHour, courseList.get(CourseID-1).duration);
	    return true;
	} else return false;
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
    
    /**
     * Assign schedule to each classes randomly 
     */
    public void initialize() {
	for(Courses c : courseList) {
	    Random r = new Random();
	    
	    int classID = c.allowedClass.get(r.nextInt(c.allowedClass.size()));
	    int startDay = c.openDay.get(r.nextInt(c.openDay.size()));
	    int startHour = c.startHour + r.nextInt((c.endHour-c.startHour)-c.duration);
	    
	    orderClass(c.ID,classID,startDay,startHour);
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
     * MAY NEED OPTIMIZATION ?
     * @return another state by moving one courses schedule to a different time 
     * period randomly from this state
     */
    public ScheduleState generateRandomChildState() {
	
	return null;
    }
    
    
    /** @return The number of constraint conflict in classess */
    public int countConflicts() {
	int sum = 0;
	for(ClassRoom c : availableRoom) {
	    sum+=c.countConflicts();
	}
	return sum;
    }
}
