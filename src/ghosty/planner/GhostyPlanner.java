/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosty.planner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import model.ScheduleState;
import controller.Solver;
import view.*;

/**
 *
 * @author USER
 */
public class GhostyPlanner {
    public static void FileRead(ScheduleState s, String filepath) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
            Vector<String> DaftarRuang=new Vector<>();

            boolean isRuangan=false; //Boolean untuk menentukan apakah yg diproses ruangan/jadwal
            BufferedReader FileRead = new BufferedReader(new FileReader(filepath));
            try {
            String line;

            while ((line=FileRead.readLine())!=null){
                if (line.equals("Ruangan")){
                    System.out.println("Ini bacanya Ruangan say");
                    isRuangan=true;
                }
                else
                if (line.equals("Jadwal"))
                {
                    System.out.println("Ini bacanya Jadwal say");
                    isRuangan=false;
                }
                else
                    if (line.equals("")){
                        System.out.println("KOSONG GAN");
                    }
                else
                {
                    if (isRuangan){
                        //integer penanda proser pasing Ruangan
                        //1=nama ruangan
                        //2=jam awal, 3=jam akhir
                        //4=daftar hari buka
                        int ParsingPart=1; 
                        String NamaRuang="";
                        int JamAwal=0;
                        int JamAkhir=0;
                        Vector<Integer> HariBuka=new Vector<>();
                        //Proses parsing ruangan
                        //Loop hingga akhir baris
                        for (int i=0;i<line.length();i++){
                            if (ParsingPart==1){
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                }
                                else
                                {
                                    //Concat char ke string membentuk nama
                                    NamaRuang=NamaRuang+line.charAt(i);
                                }
                            }
                            else
                            if (ParsingPart==2){
                                boolean stop=false;
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                }
                                else
                                //possibly not safe
                                //i dinaikkan sebanyak 2 kali karena jam kelas
                                //yang perlu diliat hanyalah 2 digit pertama
                                //dengan i+2, kita langsung ketemu ;
                                if (line.charAt(i)=='.')
                                {
                                    i=i+2;
                                }
                                else
                                {
                                    JamAwal=JamAwal*10+Character.getNumericValue(line.charAt(i));
                                }
                            }
                            else
                            if (ParsingPart==3){
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                }
                                else
                                //possibly not safe
                                //i dinaikkan sebanyak 2 kali karena jam kelas
                                //yang perlu diliat hanyalah 2 digit pertama
                                //dengan i+2, kita langsung ketemu ;
                                if (line.charAt(i)=='.')
                                {
                                    i=i+2;
                                }
                                else
                                {
                                    JamAkhir=JamAkhir*10+Character.getNumericValue(line.charAt(i));
                                }
                            }
                            else
                            //ParsingPart=4
                            {
                                if (Character.isDigit(line.charAt(i))){
                                    HariBuka.add(Character.getNumericValue(line.charAt(i))-1);
                                }
                            }
                        }

                        //Tinggal diubah ke nama ScheduleState yang sesuai
                        DaftarRuang.add(NamaRuang);
                        s.addClass(NamaRuang,JamAwal,JamAkhir,HariBuka);
                    }


                    else
                    // Jika Jadwal
                    {
                        //integer penanda proser pasing Ruangan
                        //1=nama jadwal
                        //2=nama ruangan
                        //3=jam awal, 4=jam akhir
                        //5=daftar hari buka
                        int ParsingPart=1; 
                        String NamaJadwal="";
                        StringBuilder NamaRuang=new StringBuilder();
                        int JamAwal=0;
                        int JamAkhir=0;
                        int duration=0;
                        Vector<Integer> KelasBuka=new Vector<>();
                        Vector<Integer> HariBuka=new Vector<>();
                        for (int i=0;i<line.length();i++){
                            if (ParsingPart==1){
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                }
                                else
                                {
                                    //Concat char ke string membentuk nama
                                    NamaJadwal=NamaJadwal+line.charAt(i);
                                }
                            }
                            else
                            if (ParsingPart==2){
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                    if (NamaRuang.toString().equals("-")){
                                        //kasus tidak ada syarat ruangan

                                    }
                                    else
                                    //Kasus jika ada syarat ruangan
                                    {
                                        int IDruang;
                                        //Asumsi ID mulai dari satu
                                        //Hilangkan +1 jika mulai dari nol
                                        IDruang=DaftarRuang.indexOf(NamaRuang.toString())+1;
                                        KelasBuka.add(IDruang);
                                    }
                                }
                                else
                                //Asumsi jika bisa bbrp kelas dipisah pakai koma (,)
                                if (line.charAt(i)==','){
                                    int IDruang;
                                    //Asumsi ID mulai dari satu
                                    //Hilangkan +1 jika mulai dari nol
                                    IDruang=DaftarRuang.indexOf(NamaRuang.toString())+1;
                                    KelasBuka.add(IDruang);
                                }
                                else
                                {
                                    //Concat char ke string membentuk nama
                                    NamaRuang=NamaRuang.append(line.charAt(i));
                                }
                            }
                            else
                            if (ParsingPart==3){
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                }
                                else
                                //possibly not safe
                                //i dinaikkan sebanyak 2 kali karena jam kelas
                                //yang perlu diliat hanyalah 2 digit pertama
                                //dengan i+2, kita langsung ketemu ;
                                if (line.charAt(i)=='.')
                                {
                                    i=i+2;
                                }
                                else
                                {
                                    JamAwal=JamAwal*10+Character.getNumericValue(line.charAt(i));
                                }
                            }
                            else
                            if (ParsingPart==4){
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                }
                                else
                                //possibly not safe
                                //i dinaikkan sebanyak 2 kali karena jam kelas
                                //yang perlu diliat hanyalah 2 digit pertama
                                //dengan i+2, kita langsung ketemu ;
                                if (line.charAt(i)=='.')
                                {
                                    i=i+2;
                                }
                                else
                                {
                                    JamAkhir=JamAkhir*10+Character.getNumericValue(line.charAt(i));
                                }
                            }
                            else
                            if (ParsingPart==5){
                                if (line.charAt(i)==';'){
                                    ParsingPart++;
                                }
                                else
                                {
                                    duration=Character.getNumericValue(line.charAt(i));
                                }
                            }
                            else
                            {
                                if (Character.isDigit(line.charAt(i))){

                                    HariBuka.add(Character.getNumericValue(line.charAt(i))-1);

                                }
                            }
                        }

                        s.addCourse(NamaJadwal,KelasBuka,JamAwal,JamAkhir,duration,HariBuka);
                    }

                }
            }
        } finally {
        FileRead.close();
        }
    }
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
	s.addCourse("IF2114", class1, 9, 14, 2, day2);
	class1.add(2);
	s.addCourse("IF3412", class1, 7, 12, 3, day2);
	s.addCourse("IF3413", class1, 7, 12, 3, day2);
	s.addCourse("IF3413", class1, 7, 12, 3, day2);
	day2.remove(0); class1.add(1);
	s.addCourse("IF1234", class1, 12, 18, 4, day2);
	s.addCourse("IF1235", class1, 12, 18, 4, day2);
	s.addCourse("IF1235", class1, 12, 18, 4, day2);

	s.initialize();
        System.out.println("Initialization done");
        MainMenu M = new MainMenu();
        M.getJadwal(s);
        M.setVisible(true);
        //ScheduleView V = new ScheduleView(s);
        //MainMenu M = new MainMenu();
        /*
	System.out.println(s);
	System.out.println(s.countConflicts());
	Solver sol1 = new Solver(s);

	System.out.println(sol1.getProblem());
	System.out.println(sol1.getProblem().countConflicts());
	sol1.simulatedAnnealing(1000,0.99,10);
	//sol1.hillClimb(1000);

	System.out.println("//////////SOLUTION//////////");
	System.out.println(sol1.getSolution());
	System.out.println(sol1.getSolution().countConflicts());*/
	
    }
    
}
