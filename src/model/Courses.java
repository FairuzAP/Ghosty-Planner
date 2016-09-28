  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Vector;

/**
 *
 * @author USER
 */
public class Courses {
    
    /** An ID of x means it's the x'th Courses ever made in the process */
    public int ID;
    
    /** The name of the courses ex.IF3150 */
    public String name;
    
    /** The list of allowed ClassRoom ClassRoom ID the course may happen in */
    public Vector<Integer> allowedClass;
        
    /** The minimum time the course to start at ; 07:00 -> 7, 13.00 -> 13 */
    public int startHour;
    /** The maximum time the course to end at; 07:00 -> 7, 13.00 -> 13 */
    public int endHour;
    /** The duration of the class (in one sitting)*/
    public int duration;
    
    /** 
     * A Vector containing the day ID the course can take place
     * Monday -> 0, Friday -> 4
     */
    public Vector<Integer> openDay;
    
    /** The classRoom the course happen on, WILL OBEY allowedClass */
    private ClassRoom actualCourseClass;
    /** The day the course happen on, WILL OBEY openDay */
    private int actualCourseDay;
    /** The time the course happen on, WILL OBEY time domain */
    private int actualCourseTime;
    
    /**
     * Constructor, Every paramater must be VALID
     * 
     * @param s must less than e and more than 6
     * @param e must more than s and less than 18
     * @param o Must not empty
     */
    Courses(int _ID, String n, Vector<Integer> a, int s, int e, int d, Vector<Integer> o) {
	
	ID = _ID;
	name = n;
	
	allowedClass = new Vector<>(a);
	
	startHour = s;
	endHour = e;
	duration = d;
	openDay = new Vector<>(o);
	
	actualCourseClass = null;
	actualCourseDay = -1;
	actualCourseTime = -1;
    }
    
    /**
     * CCTOR, doesn't copy the three 'actual' fields; 
     * use the same ID as the paramater,
     */
    Courses(Courses c) {
	
	ID = c.ID;
	name = c.name;
	
	allowedClass = new Vector<>(c.allowedClass);
	
	startHour = c.startHour;
	endHour = c.endHour;
	duration = c.duration;
	openDay = new Vector<>(c.openDay);
	
	actualCourseClass = null;
	actualCourseDay = -1;
	actualCourseTime = -1;
    }
    
    
    /**
     * ignore wether or not the class can support it
     * @return Wether or not the class can happen at that Class during that Day 
     * and that Time; 
     */
    boolean isValid(ClassRoom Class, int Day, int Time) {
	if(Class!=null) { 
	    if(!allowedClass.contains(Class.ID)) return false;
	} 
	if(!openDay.contains(Day)) return false;
	else return !((Time < startHour)||(Time > endHour));
    }
    
    /**
     * DOES NOT UPDATE THE CLASS BOOKINGPLAN
     * Set the actual schedule for this class if allowed according to isValid,
     * return false and do nothing otherwise.
     */
    boolean setClass(ClassRoom Class, int Day, int Time) {
	if(isValid(Class, Day, Time)) {
	    actualCourseClass = Class;
	    actualCourseDay = Day;
	    actualCourseTime = Time;
	    return true;
	} else return false;
    }
    
    
    public ClassRoom getActualCourseClass() {
	return actualCourseClass;
    }
    public int getActualCourseDay() {
	return actualCourseDay;
    }
    public int getActualCourseTime() {
	return actualCourseTime;
    }
    public int countConflicts() {
	if(actualCourseClass!=null) 
	    return actualCourseClass.getOrderConflict(actualCourseDay, actualCourseTime, duration);
	else return 0;
    }
    
    
    @Override
    public String toString() {
	StringBuilder res = new StringBuilder();
	res.append("\nID : ").append(ID).append("; ");
	res.append("name : ").append(name).append("\n");
	res.append("allowedClass : ").append(allowedClass.toString()).append("\n");
	res.append("startHour : ").append(startHour).append("; ");
	res.append("endHour : ").append(endHour).append("; ");
	res.append("duration : ").append(duration).append("\n");
	res.append("openDay : ").append(openDay.toString()).append("\n");
	res.append("actualCourseClass : ");
	if(actualCourseClass!=null) res.append(actualCourseClass.ID);
	else res.append(-1);
	res.append("\n");
	res.append("actualCourseDay : ").append(actualCourseDay).append("\n");
	res.append("actualCourseTime : ").append(actualCourseTime).append("\n");
	return res.toString();
    }
    
}