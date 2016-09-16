/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class ClassRoom {
    
    private static final int SENIN = 0;
    private static final int SELASA = 1;
    private static final int RABU = 2;
    private static final int KAMIS = 3;
    private static final int JUMAT = 4;
    
    /** An ID of x means it's the x'th classroom ever made in the process */
    public int ID;
    public static int classMade = 0;
    
    /** The name of the classroom ex.7606 */
    public String name;
    
    /** 
     * The time the classroom open; 07:00 -> 7, 13.00 -> 13
     */
    public int startHour;
    /** 
     * The time the classroom close ; 07:00 -> 7, 13.00 -> 13
     */
    public int endHour;
    
    /** 
     * A Vector containing the day ID the classroom open; 
     * Monday -> 0, Friday -> 4
     */
    public Vector<Integer> openDay;
    
    /**
     * A two dimentional matrix to represent the class usage in each hour of 
     * the day. 
     * 
     * The first Map pairs a day with the second map
     * The second Map pair each hour index with how many Courses has booked 
     * this class on that hour on that day ex:
     * 
     * (7,1)->On 7:00 there is the class is booked by one Courses.
     */
    private Map<Integer,Map<Integer,Integer>> BookingPlan;

    
    /**
     * Constructor,
     * Prepare the BookingPlan for every time period the class open
     * Every paramater must be VALID
     * 
     * @param s must less than e and more than 6
     * @param e must more than s and less than 18
     * @param o Must not empty
     */
    public ClassRoom(String n, int s, int e, Vector<Integer> o) {
	
	ID = ++classMade;
	name = n;
	startHour = s;
	endHour = e;
	openDay = o;
	
	BookingPlan = new TreeMap<>();
	for(int day : openDay) {
	    BookingPlan.put(day, new TreeMap<>());
	    for(int i=startHour;i<endHour;i++) {
		BookingPlan.get(day).put(i,0);
	    }
	}
    }
    
    /** @return Wether or not the class is open at that day and hour */
    public boolean isOpen(int day, int hour) {
	if(BookingPlan.get(day)==null) return false;
	else return (BookingPlan.get(day).get(hour)!=null);
    }
    
    
    /**
     * Increment the BookingPlan for this class at that day and hour
     * If the class is closed at that time, do nothing and return false
     */
    public boolean orderClass(int day, int startHour, int duration) {
	if((isOpen(day,startHour))&&(isOpen(day,startHour+duration))) {
	    for(int i=startHour;i<startHour+duration;i++) {
		BookingPlan.get(day).put(i, BookingPlan.get(day).get(i)+1);
	    }
	    return true;
	} else return false;
    }
    
    /**
     * Decrement the BookingPlan for this class at that day and hour
     * If the class is closed at that time, do nothing and return false
     * If any timePeriod to be decrement is 0, revert and return false
     */
    public boolean removeOrder(int day, int startHour, int duration) {
	if((isOpen(day,startHour))&&(isOpen(day,startHour+duration))) {
	    
	    boolean check=true;
	    for(int i=startHour;i<startHour+duration;i++) {
		if(BookingPlan.get(day).get(i)==0) {
		    check=false;
		    break;
		}
	    }
	    
	    if(check) {
		for(int i=startHour;i<startHour+duration;i++) {
		    BookingPlan.get(day).put(i, BookingPlan.get(day).get(i)-1);
		}
		return true;
	    } else return false;
	} else return false;
    }
    
    
    /**
     * CONFLICTS DEFINITION NOT CLEAR, TEMPORARY
     * @return the number of time period when this class is used by more than 
     * one Courses
     */
    public int countConflicts() {
	int res = 0;
	for(int day : openDay) {
	    for(int i=startHour;i<endHour;i++) {
		if(BookingPlan.get(day).get(i) > 1) {
		    int n=BookingPlan.get(day).get(i);
		    res+=(n*(n-1)/2);
		}
	    }
	}
	return res;
    }
    
}
