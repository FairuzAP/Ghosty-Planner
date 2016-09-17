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
    
    /** Ctor, initialize array */
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
    
    
    private boolean orderClass(int CourseID, int ClassID, int startDay, int startHour) {
	if(courseList.get(CourseID-1).setClass(availableRoom.get(ClassID-1), startDay, startHour)) {
	    availableRoom.get(ClassID-1).orderClass(startDay, startHour, courseList.get(CourseID-1).duration);
	    return true;
	} else return false;
    }
    
    /** @return true if the argument is valid and the class is added, false if not */
    public boolean addClass(String className, int startHour, int endHour, Vector<Integer> openDayList){
	if((className.isEmpty())||(startHour<6)||(endHour>18)||(startHour>endHour)||(openDayList.isEmpty())) 
	    return false;
	else {
	    availableRoom.add(new ClassRoom(className, startHour, endHour, openDayList));
	    return true;
	}
    }
    
    /** @return true if the argument is valid and the class is added, false if not */
    public boolean addCourse(String courseName, Vector<Integer> allowedClassID, int startHour, int endHour, int duration, Vector<Integer> openDayList){
	if((courseName.isEmpty())||(startHour<6)||(endHour>18)||(startHour>endHour)||(openDayList.isEmpty())||(duration<1)||(duration>11)) 
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
	Random r = new Random();
	r.setSeed(System.currentTimeMillis());
	for(Courses c : courseList) {
	    
	    int classID = c.allowedClass.get(r.nextInt(c.allowedClass.size()));
	    int startDay = c.openDay.get(r.nextInt(c.openDay.size()));
	    int startHour = c.startHour + r.nextInt(1+(c.endHour-c.startHour)-c.duration);
	    
	    orderClass(c.ID,classID,startDay,startHour);
	}
    }
    
    
    /**
     * Change this state to a best possible state by moving one courses schedule
     * to a different time period so that the conflict count decreases as much
     * as possible
     * @return The amount of conflict removed
     */
    public void generateBestChildState() {
        Random r = new Random();
	r.setSeed(System.currentTimeMillis());
	
	// Pick a course ro be re-assigned
	Courses c = this.courseList.get(r.nextInt(this.courseList.size()));
	
	// Remove it's old schedule from the class
	c.getActualCourseClass().removeOrder(c.getActualCourseDay(), c.getActualCourseTime(), c.duration);
        Vector<Vector<Integer>> ListJadwal=new Vector<Vector<Integer>>();
        Vector<Integer> ListDay=new Vector<>();
	
        //Loop untuk mencari jadwal terbaik setiap day yang terbuka
        for(int day:c.openDay){
            Vector<Integer> Temporary=new Vector<>();
            Temporary=c.getActualCourseClass().getBestTimeSlotDay(day, c.startHour, c.endHour, c.duration);
            ListJadwal.add(Temporary);
            ListDay.add(day);
        }
        
        // Re-assign it's schedule 
	int classID = c.ID;
	int startDay = ListDay.get(0);
	int startHour = ListJadwal.get(0).get(0);
        int Konflik = ListJadwal.get(0).get(1);
	
        //looping untuk membandingkan jadwal semua hari
        for (int i=1;i<ListJadwal.size();i++){
            // Jika hari lain konflik lebih sedikit
            if (Konflik>ListJadwal.get(i).get(1)){
                startDay = ListDay.get(i);
                startHour = ListJadwal.get(i).get(0);
                Konflik = ListJadwal.get(i).get(1);
            }
        }
	
        // Di sini, startDay,startHour,Konflik memiliki jumlah konflik tersedikit
        this.orderClass(c.ID,c.getActualCourseClass().ID,startDay,startHour);
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
	int classID = c.allowedClass.get(r.nextInt(c.allowedClass.size()));
	int startDay = c.openDay.get(r.nextInt(c.openDay.size()));
	int startHour = c.startHour + r.nextInt(1+(c.endHour-c.startHour)-c.duration);
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
