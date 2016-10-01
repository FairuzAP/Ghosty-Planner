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
    
    public static final int SENIN = 0;
    public static final int SELASA = 1;
    public static final int RABU = 2;
    public static final int KAMIS = 3;
    public static final int JUMAT = 4;
    
    /** An ID of x means it's the x'th classroom ever made in this state */
    public int ID;
    
    /** The name of the classroom ex.7606 */
    public String name;
    
    /** The time the classroom open (inclusive); 07:00 -> 7, 13.00 -> 13 */
    public int startHour;
    /** The time the classroom close (exclusive); 07:00 -> 7, 13.00 -> 13 */
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
    ClassRoom(int _ID, String n, int s, int e, Vector<Integer> o) {
	
	ID = _ID;
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
    /** CCTOR, use the same ID as the paramater */
    ClassRoom(ClassRoom c) {
	
	ID = c.ID;
	name = c.name;
	startHour = c.startHour;
	endHour = c.endHour;
	openDay = new Vector<>(c.openDay);
	
	BookingPlan = new TreeMap<>();
	for(int day : openDay) {
	    BookingPlan.put(day, new TreeMap<>());
	    for(int i=startHour;i<endHour;i++) {
		BookingPlan.get(day).put(i,c.BookingPlan.get(day).get(i));
	    }
	}
    }    
    
    public boolean isOpen(int day, int startHour, int duration) {
	boolean res = true;
	if(!BookingPlan.containsKey(day)) res = false;
	else {
	    for(int i=0;i<duration;i++) {
		if(!BookingPlan.get(day).containsKey(startHour+i)) res = false;
	    }
	}
	return res;
    }
    
    /** Increment the BookingPlan at that day, hour and duration */
    boolean orderClass(int day, int startHour, int duration) {
	if(!isOpen(day,startHour,duration)) return false;
	else {
	    for(int i=startHour;i<startHour+duration;i++) {
		BookingPlan.get(day).put(i, BookingPlan.get(day).get(i)+1);
	    }
	    return true;
	}
    }
    /** Decrement the BookingPlan at that day, hour and duration */
    boolean removeOrder(int day, int startHour, int duration) {  
	if(!isOpen(day,startHour,duration)) return false;
	else {
	    for(int i=startHour;i<startHour+duration;i++) {
		BookingPlan.get(day).put(i, BookingPlan.get(day).get(i)-1);
	    }
	    return true;
	}
    }
    
    /**
     * Fungsi untuk menghitung total conflict Handshake 
     * @param Kelas = Jumlah kelas pada jadwal itu
     */
    private int countConstraintConflict(int Kelas){
	if(Kelas<=0) return 0;
	else return Kelas*(Kelas-1)/2;
    }
    
    /**
     * Return the number of constraint conflict generated by the order block
     * on the parameter's time period
     */
    int getOrderConflict(int day, int startHour, int duration) {
	if(!isOpen(day,startHour,duration)) return 0;
	else {
	    int res = 0;
	    for(int i=startHour;i<startHour+duration;i++) {
		res+=countConstraintConflict(BookingPlan.get(day).get(i));
	    }
	    return res;
	}
    }
    
    /**
     * Return the first index of a continuous "duration" long empty timeslot in 
     * the paramater's "day" between startHour and endHour with less or equal
     * Return value berupa vektor Integer berukuran 2
     * Vektor.get(1) berupa hari, Vektor.get(2) berupa jumlah konflik
     */
    Vector<Integer> getBestTimeSlotDay(int day, int Start, int endHour, int duration) {
	
        Vector<Integer> RetVal=new Vector<>();
        int TimeStart = Start;
        boolean Found = false;
	
	// Looping mencari jadwal yang tepat
        while ((TimeStart+duration-1<endHour) && (!Found)){
            int KonflikCounter=0;
            for (int i=TimeStart;i<TimeStart+duration;i++){
                KonflikCounter=KonflikCounter+countConstraintConflict(BookingPlan.get(day).get(i)+1);
            }
            //KonflikCounter berisi jumlah total konflik yang terjadi
            if (KonflikCounter==0){
                //Kasus jika 0 Conflict
                RetVal.removeAllElements();
                RetVal.add(TimeStart);
                RetVal.add(0);
                Found=true;
            }
            else
            if (TimeStart==Start)
            {
                //Kasus jika merupakan iterasi pertama dan konflik!=0
                RetVal.removeAllElements();
                RetVal.add(TimeStart);
                RetVal.add(KonflikCounter);
                TimeStart++;
            }
            else
            {
                //Kasus iterasi selanjutnya jika konflik!=0
                if (RetVal.get(1)>KonflikCounter){
                    RetVal.removeAllElements();
                    RetVal.add(TimeStart);
                    RetVal.add(KonflikCounter);
                    TimeStart++;
                }
                else
                {
                    TimeStart++;
                }
            }
        }
        return RetVal;
    }
    
    
    /**
     * CONFLICTS DEFINITION NOT CLEAR, TEMPORARY
     * @return the number of time period when this class is used by more than 
     * one Courses
     */
    int countConflicts() {
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
    
    
    @Override
    public String toString() {
	StringBuilder res = new StringBuilder();
	res.append("\nID : ").append(ID).append("; ");
	res.append("name : ").append(name).append("\n");
	res.append("startHour : ").append(startHour).append("; ");
	res.append("endHour : ").append(endHour).append("\n");
	res.append("openDay : ").append(openDay.toString()).append("\n");
	for(int day : openDay) {
	    res.append("day ").append(day).append(" : ");
	    for(int i=startHour;i<endHour;i++) {
		res.append(BookingPlan.get(day).get(i)).append(" ");
	    }
	    res.append("\n");
	}
	return res.toString();
    }
    
}