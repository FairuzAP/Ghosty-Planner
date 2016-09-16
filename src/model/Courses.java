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
    public static int courseMade = 0;
    
    /** The name of the courses ex.IF3150 */
    public String name;
    
    /** 
     * The list of allowed ClassRoom ClassRoom ID the course may happen in
     */
    public Vector<Integer> allowedClass;
        
    /** 
     * The minimum time the course to start at ; 07:00 -> 7, 13.00 -> 13
     */
    public int startHour;
    /** 
     * The maximum time the course to end at; 07:00 -> 7, 13.00 -> 13
     */
    public int endHour;
    /** 
     * The duration of the class (in one sitting)
     */
    public int duration;
    
    /** 
     * A Vector containing the day ID the course can take place
     * Monday -> 1, Friday -> 5
     */
    public Vector<Integer> openDay;
    
    /** 
     * The classRoom the course happen on, WILL OBEY allowedClass
     */
    private ClassRoom actualCourseClass;
    /** 
     * The day the course happen on, WILL OBEY openDay
     */
    private int actualCourseDay;
    /** 
     * The time the course happen on, WILL OBEY startHour, duration, and endHour
     */
    private int actualCourseTime;
    
    /**
     * Constructor, Every paramater must be VALID
     * 
     * @param s must less than e and more than 6
     * @param a if empty, then all class is allowed
     * @param e must more than s and less than 18
     * @param o Must not empty
     */
    public Courses(String n, Vector<Integer> a, int s, int e, int d, Vector<Integer> o) {
	
	ID = ++courseMade;
	name = n;
	
	if(a.isEmpty()) {
	    allowedClass = new Vector<>();
	    for(int i=1; i<=ClassRoom.classMade; i++) allowedClass.add(i);
	} else allowedClass = a;
	
	startHour = s;
	endHour = e;
	duration = d;
	openDay = o;
	
	actualCourseClass = null;
	actualCourseDay = -1;
	actualCourseTime = -1;
    }
    
    /**
     * @return Wether or not the class can happen at that Class during that Day 
     * and that Time; ignore wether or not the class can support it
     */
    public boolean isValid(ClassRoom Class, int Day, int Time) {
	if(!allowedClass.contains(Class.ID)) return false;
	else if(!openDay.contains(Day)) return false;
	else return !((Time < startHour)||(Time > endHour));
    }
    
    /**
     * DOES NOT UPDATE THE CLASS BOOKINGPLAN
     * Set the actual schedule for this class if allowed according to isValid,
     * return false and do nothing otherwise.
     */
    public boolean setClass(ClassRoom Class, int Day, int Time) {
	if(isValid(Class, Day, Time)) {
	    actualCourseClass = Class;
	    actualCourseDay = Day;
	    actualCourseTime = Time;
	    return true;
	} else return false;
    }
    
    // Getter "actualFields"
    // countConflicts()
    
}
