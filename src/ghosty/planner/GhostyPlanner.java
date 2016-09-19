/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosty.planner;

import java.util.Vector;
import model.ScheduleState;

/**
 *
 * @author USER
 */
public class GhostyPlanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	ScheduleState s = new ScheduleState();
	
	Vector<Integer> day1 = new Vector();
	day1.add(0);day1.add(1);day1.add(2);day1.add(3);day1.add(4);
	s.addClass("7606", 7, 12, day1); 
	day1.remove(3);
	s.addClass("7602", 9, 15, day1);
	day1.remove(0);
	s.addClass("9123", 7, 18, day1);
	
	Vector<Integer> day2 = new Vector();
	day2.add(0);day2.add(1);day2.add(2);day2.add(3);day2.add(4);
	Vector<Integer> class1 = new Vector();
	s.addCourse("IF2112", class1, 9, 14, 2, day2);
	s.addCourse("IF2113", class1, 9, 14, 2, day2);
	class1.add(2);
	s.addCourse("IF3412", class1, 7, 12, 3, day2);
	s.addCourse("IF3413", class1, 7, 12, 3, day2);
	day2.remove(0); class1.add(1);
	s.addCourse("IF1234", class1, 12, 18, 4, day2);
	s.addCourse("IF1235", class1, 12, 18, 4, day2);

	s.initialize();
	System.out.println(s);
	System.out.println(s.countConflicts());
	s.reasignBestCourseFor(5);
	System.out.println(s);
	System.out.println(s.countConflicts());
	
    }
    
}