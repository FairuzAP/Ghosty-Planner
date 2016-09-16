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
	for(int day=SENIN; day<=JUMAT; day++) {
	    BookingPlan.put(day, new TreeMap<>());
	    for(int i=7;i<18;i++) {
		BookingPlan.get(day).put(i,1);
	    }
	}
	
	for(int day : openDay) {
	    for(int i=startHour;i<endHour;i++) {
		BookingPlan.get(day).put(i,0);
	    }
	}
    }    
    
    /**
     * Increment the BookingPlan for this class at that day and hour
     * If the class is closed at that time, do nothing and return false
     */
    public void orderClass(int day, int startHour, int duration) {
	for(int i=startHour;i<startHour+duration;i++) {
	    BookingPlan.get(day).put(i, BookingPlan.get(day).get(i)+1);
	}
    }
    
    /**
     * Decrement the BookingPlan for this class at that day and hour
     * If the class is closed at that time, do nothing and return false
     * If any timePeriod to be decrement is 0, revert and return false
     */
    public void removeOrder(int day, int startHour, int duration) {  
	for(int i=startHour;i<startHour+duration;i++) {
	    BookingPlan.get(day).put(i, BookingPlan.get(day).get(i)-1);
	}
    }
    
    
    /**
     * Fungsi untuk menghitung total conflict Handshake 
     * @param Kelas = Jumlah kelas pada jadwal itu
     */
    private int countConstraintConflict(int Kelas){
        return Kelas*(Kelas-1)/2;
    }
    
    /**
     * Return the first index of a continuous "duration" long empty timeslot in 
     * the paramater's "day" between startHour and endHour with less or equal
     * conflict to "maxConflict"
     * Jika tidak ditemukan jadwal, return -1
     */
    public int getBestTimeSlotDay(int day, int Start, int endHour, int duration, int maxConflict) {
	int StartVal = -1;
        int TimeStart = 7;
        boolean Found = true;
	
	//Looping mencari jadwal yang tepat
        while ((StartVal==-1) && (Found)){
	    
            //Kasus jika tidak ada slot yang cukup
            if ((TimeStart+duration-1) > endHour) Found=false;
            else {
                int JumKonflik=0;
		
                for (int i=TimeStart;i<TimeStart+duration;i++){
                    JumKonflik=JumKonflik+countConstraintConflict(BookingPlan.get(day).get(i));
                }
		
                if (JumKonflik>maxConflict) TimeStart++;
		else StartVal=TimeStart;
            }
        }
        return StartVal;
    }
    
    
    /**
     * CONFLICTS DEFINITION NOT CLEAR, TEMPORARY
     * @return the number of time period when this class is used by more than 
     * one Courses
     */
    public int countConflicts() {
	int res = 0;
	for(int day=SENIN; day<=JUMAT; day++) {
	    for(int i=7; i<18; i++) {
		if(BookingPlan.get(day).get(i) > 1) {
		    int n=BookingPlan.get(day).get(i);
		    res+=(n*(n-1)/2);
		}
	    }
	}
	return res;
    }
    
}
