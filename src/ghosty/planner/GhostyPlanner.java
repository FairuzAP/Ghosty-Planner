/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosty.planner;

import java.util.Vector;
import model.ClassRoom;

/**
 *
 * @author USER
 */
public class GhostyPlanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Vector<Integer> temp = new Vector();
	temp.add(0);temp.add(1);temp.add(2);temp.add(3);temp.add(4);
	ClassRoom c = new ClassRoom("7606", 7, 12, temp);
	System.out.print(c);
	
	c.orderClass(0, 12, 3);
	c.orderClass(2, 10, 4);
	c.orderClass(0, 11, 2);
	System.out.print(c);
	
	System.out.print(c.countConflicts());
	
	
    }
    
}
