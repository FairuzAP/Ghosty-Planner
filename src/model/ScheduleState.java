/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class ScheduleState {
    
    /** All the room that is available in the schedule */
    private final Vector<ClassRoom> availableRoom;
    
    /** All the course that must be scheduled */
    private final Vector<Courses> courseList;
    
    
    /** Ctor, initialize array */
    public ScheduleState() {
	availableRoom = new Vector<>();
	courseList = new Vector<>();
    }
    
    /** CCTOR make an exact copy with its own objects */
    public ScheduleState(ScheduleState s) {
	availableRoom = new Vector<>();
	courseList = new Vector<>();
	
	for(ClassRoom c : s.availableRoom) {
	    availableRoom.add(new ClassRoom(c));
	}
	for(Courses c : s.courseList) {
	    Courses temp = new Courses(c);
	    ClassRoom cl = availableRoom.get(c.getActualCourseClass().ID-1);
	    temp.setClass(cl, c.getActualCourseDay(), c.getActualCourseTime());
	    courseList.add(temp);
	}
    }
    
    /** Reset the content of every vector */
    public void resetSchedule() {
	availableRoom.removeAllElements();
	courseList.removeAllElements();
    } 
    
    
    private boolean orderClass(int CourseID, int ClassID, int startDay, int startHour) {
	if(!availableRoom.get(ClassID-1).isOpen(startDay, startHour, courseList.get(CourseID-1).duration)) return false;
	
	boolean res = courseList.get(CourseID-1).setClass(availableRoom.get(ClassID-1), startDay, startHour);
	if(res) {
	    availableRoom.get(ClassID-1).orderClass(startDay, startHour, courseList.get(CourseID-1).duration);
	    return true;
	} else return false;
	
    }
    
    /** @return true if the argument is valid and the class is added, false if not */
    public boolean addClass(String className, int startHour, int endHour, Vector<Integer> openDayList){
	if((className.isEmpty())||(startHour<6)||(endHour>18)||(startHour>endHour)||(openDayList.isEmpty())) 
	    return false;
	else {
	    availableRoom.add(new ClassRoom(availableRoom.size()+1, className, startHour, endHour, openDayList));
	    return true;
	}
    }
    
    /** @return true if the argument is valid and the class is added, false if not */
    public boolean addCourse(String courseName, Vector<Integer> allowedClassID, int startHour, int endHour, int duration, Vector<Integer> openDayList){
	if((courseName.isEmpty())||(startHour<6)||(endHour>18)||(startHour>endHour)||(openDayList.isEmpty())||(duration<1)||(duration>(endHour-startHour))) 
	    return false;
	else {
	    
	    boolean validClassID = true;
	    for(int i : allowedClassID) {
		if(i>availableRoom.size()) {
		    validClassID = false;
		    break;
		}
	    }
	    
	    if(!validClassID) return false;
	    else {
		Vector<Integer> temp = new Vector<>(allowedClassID);
		if(temp.isEmpty()) {
		    for(int i=1; i<=availableRoom.size(); i++) temp.add(i);
		}
		courseList.add(new Courses(courseList.size()+1, courseName, temp, startHour, endHour, duration, openDayList));
		return true;
	    }
	}
    }
    
    /**
     * Assign schedule to each classes randomly 
     */
    public void initialize() {
	Random r = new Random();
	r.setSeed(System.currentTimeMillis());
	for(Courses c : courseList) {
	    int classID,minEnd,maxStart;
	    Vector<Integer> commonOpen;
	    do {
	    
		classID = c.allowedClass.get(r.nextInt(c.allowedClass.size()));
		ClassRoom selected = availableRoom.get(classID-1);

		maxStart = Math.max(c.startHour, selected.startHour);
		minEnd = Math.min(c.endHour, selected.endHour);
		commonOpen = new Vector<>(c.openDay);
		commonOpen.retainAll(selected.openDay);
	    
	    } while ((minEnd-maxStart < c.duration)||(commonOpen.isEmpty()));
	    
	    int startDay = commonOpen.get(r.nextInt(commonOpen.size()));
	    int startHour = maxStart + r.nextInt(1+(minEnd-maxStart)-c.duration);
	    
	    boolean bad = orderClass(c.ID,classID,startDay,startHour);
	    if(!bad) {
		int i = 1;
	    }
	}
    }
    
    /**
     * Reasign EVERY courses randomly
     */
    public void massReorder() {
	Random r = new Random();
	r.setSeed(System.currentTimeMillis());
	for(Courses c : courseList) {
	    int classID,minEnd,maxStart;
	    Vector<Integer> commonOpen;
	    do {
	    
		classID = c.allowedClass.get(r.nextInt(c.allowedClass.size()));
		ClassRoom selected = availableRoom.get(classID-1);

		maxStart = Math.max(c.startHour, selected.startHour);
		minEnd = Math.min(c.endHour, selected.endHour);
		commonOpen = new Vector<>(c.openDay);
		commonOpen.retainAll(selected.openDay);
	    
	    } while ((minEnd-maxStart < c.duration)||(commonOpen.isEmpty()));
	    
	    int startDay = commonOpen.get(r.nextInt(commonOpen.size()));
	    int startHour = maxStart + r.nextInt(1+(minEnd-maxStart)-c.duration);
	    
	    reOrderClass(c.ID,classID,startDay,startHour);
	}
    }
    
    /**
     * Reassign the Course with CourseID to a bestt possible timeslot; the one 
     * that couse the least Conflicts
     */
    public void reasignBestCourseFor(int CourseID) {
	// Pick a course ro be re-assigned
	Courses c = courseList.get(CourseID-1);
	
	// Remove it's old schedule from the class
	c.getActualCourseClass().removeOrder(c.getActualCourseDay(), c.getActualCourseTime(), c.duration);
	
	// The vector to contain the best possible schedule placement for this
	// course. [0]=time,[1]=conflicts number,[2]=day,[3]=classID
	Vector<Integer> bestSchedule = null;
	
	for(int classID : c.allowedClass) {
	    
	    ClassRoom CClass = availableRoom.get(classID-1);
	    
	    int maxStart = Math.max(c.startHour, CClass.startHour);
	    int minEnd = Math.min(c.endHour, CClass.endHour);
	    Vector<Integer> commonOpen = new Vector<>(c.openDay);
	    commonOpen.retainAll(CClass.openDay);
	    
	    if ((minEnd-maxStart < c.duration)||(commonOpen.isEmpty())) {}
	    
	    // The vector to contain the best possible schedule placement for this
	    // course in CClass. [0]=time,[1]=conflicts number,[2]=day
	    Vector<Integer> bestScheduleForCClass = null;
	    
	    for(int day : commonOpen) {
		Vector<Integer> temp = CClass.getBestTimeSlotDay(day, maxStart, minEnd, c.duration);
		if(bestScheduleForCClass == null) {
		    bestScheduleForCClass = new Vector<>();
		    bestScheduleForCClass.add(temp.get(0));
		    bestScheduleForCClass.add(temp.get(1));
		    bestScheduleForCClass.add(day);
		} else if(bestScheduleForCClass.get(1) > temp.get(1)) {
		    bestScheduleForCClass.set(0,temp.get(0));
		    bestScheduleForCClass.set(1,temp.get(1));
		    bestScheduleForCClass.set(2,day);
		}
		
		if(bestScheduleForCClass.get(1)==0) break;
	    }
	    
	    if(bestSchedule == null) {
		bestSchedule = new Vector<>();
		bestSchedule.add(bestScheduleForCClass.get(0));
		bestSchedule.add(bestScheduleForCClass.get(1));
		bestSchedule.add(bestScheduleForCClass.get(2));
		bestSchedule.add(CClass.ID);
	    } else if(bestSchedule.get(1) > bestScheduleForCClass.get(1)) {
		bestSchedule.set(0,bestScheduleForCClass.get(0));
		bestSchedule.set(1,bestScheduleForCClass.get(1));
		bestSchedule.set(2,bestScheduleForCClass.get(2));
		bestSchedule.set(3,CClass.ID);
	    }
	    
	    if(bestSchedule.get(1)==0) break;
	}
	
	orderClass(c.ID, bestSchedule.get(3), bestSchedule.get(2), bestSchedule.get(0));
    }
    
    /**
     * MAY NEED OPTIMIZATION ?
     * @return another state by moving one courses schedule to a different time 
     * period randomly from this state
     */
    public ScheduleState generateRandomChildState() {
	Random r = new Random();
	r.setSeed(System.currentTimeMillis());
	ScheduleState s = new ScheduleState(this);
	
	// Pick a course ro be re-assigned
	Courses c = s.courseList.get(r.nextInt(s.courseList.size()));
	
	// Remove it's old schedule from the class
	c.getActualCourseClass().removeOrder(c.getActualCourseDay(), c.getActualCourseTime(), c.duration);
	
	// Re-assign it's schedule randomly
	int classID,minEnd,maxStart;
	Vector<Integer> commonOpen;
	do {

	    classID = c.allowedClass.get(r.nextInt(c.allowedClass.size()));
	    ClassRoom selected = availableRoom.get(classID-1);

	    maxStart = Math.max(c.startHour, selected.startHour);
	    minEnd = Math.min(c.endHour, selected.endHour);
	    commonOpen = new Vector<>(c.openDay);
	    commonOpen.retainAll(selected.openDay);

	} while ((minEnd-maxStart < c.duration)||(commonOpen.isEmpty()));

	int startDay = commonOpen.get(r.nextInt(commonOpen.size()));
	int startHour = maxStart + r.nextInt(1+(minEnd-maxStart)-c.duration);
	s.orderClass(c.ID,classID,startDay,startHour);
	
	return s;
    }
    
    
    /** @return The number of constraint conflict in classess */
    public int countConflicts() {
	int sum = 0;
	for(ClassRoom c : availableRoom) {
	    sum+=c.countConflicts();
	}
	return sum;
    }
    
    public Courses getCourse(int CourseID) {
	return courseList.get(CourseID-1);
    }
    public ClassRoom getClass(int ClassID) {
	return availableRoom.get(ClassID-1);
    }
    
    public int getClassMade() {return availableRoom.size();}
    public int getCourseMade() {return courseList.size();}
    
    /** Change the schedule of this class form the old one to the paramater */
    public boolean reOrderClass(int CourseID, int ClassID, int startDay, int startHour) {
	if(!availableRoom.get(ClassID-1).isOpen(startDay, startHour, courseList.get(CourseID-1).duration)) return false;
	
	boolean res = courseList.get(CourseID-1).isValid(availableRoom.get(ClassID-1), startDay, startHour);
	if(res) {
	    Courses c = courseList.get(CourseID-1);
	    c.getActualCourseClass().removeOrder(c.getActualCourseDay(), c.getActualCourseTime(), c.duration);
	    orderClass(CourseID, ClassID, startDay, startHour);
	    return true;
	} else return false;
    }
    
    
    @Override
    public String toString() {
	StringBuilder res = new StringBuilder();
	res.append("Class List :\n");
	res.append(availableRoom.toString());
	res.append("\nCourse List :\n");
	res.append(courseList.toString());
	res.append("\n");
	return res.toString();
    }
}
