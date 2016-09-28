/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.ComponentOrientation;
import java.util.Map;
import java.util.TreeMap;
import model.ScheduleState;
import model.Courses;
import model.ClassRoom;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author LENOVO
 */
public class ScheduleView extends javax.swing.JFrame {
    
    public static final int SENIN = 0;
    public static final int SELASA = 1;
    public static final int RABU = 2;
    public static final int KAMIS = 3;
    public static final int JUMAT = 4;
    
    /** Map berisi text field untuk mempermudah output jadwal*/
    Map<Integer,Map<Integer,JList<String>>> ScheduleField;
    
    /**
     * Creates new form ScheduleView
     * Membuat map TextField
     */
    public ScheduleView() {
        initComponents();
        Sn7.setModel(new DefaultListModel());
        Sn8.setModel(new DefaultListModel());
        Sn9.setModel(new DefaultListModel());
        Sn10.setModel(new DefaultListModel());
        Sn11.setModel(new DefaultListModel());
        Sn12.setModel(new DefaultListModel());
        Sn13.setModel(new DefaultListModel());
        Sn14.setModel(new DefaultListModel());
        Sn15.setModel(new DefaultListModel());
        Sn16.setModel(new DefaultListModel());
        Sn17.setModel(new DefaultListModel());
        Sl7.setModel(new DefaultListModel());
        Sl8.setModel(new DefaultListModel());
        Sl9.setModel(new DefaultListModel());
        Sl10.setModel(new DefaultListModel());
        Sl11.setModel(new DefaultListModel());
        Sl12.setModel(new DefaultListModel());
        Sl13.setModel(new DefaultListModel());
        Sl14.setModel(new DefaultListModel());
        Sl15.setModel(new DefaultListModel());
        Sl16.setModel(new DefaultListModel());
        Sl17.setModel(new DefaultListModel());
        R7.setModel(new DefaultListModel());
        R8.setModel(new DefaultListModel());
        R9.setModel(new DefaultListModel());
        R10.setModel(new DefaultListModel());
        R11.setModel(new DefaultListModel());
        R12.setModel(new DefaultListModel());
        R13.setModel(new DefaultListModel());
        R14.setModel(new DefaultListModel());
        R15.setModel(new DefaultListModel());
        R16.setModel(new DefaultListModel());
        R17.setModel(new DefaultListModel());
        K7.setModel(new DefaultListModel());
        K8.setModel(new DefaultListModel());
        K9.setModel(new DefaultListModel());
        K10.setModel(new DefaultListModel());
        K11.setModel(new DefaultListModel());
        K12.setModel(new DefaultListModel());
        K13.setModel(new DefaultListModel());
        K14.setModel(new DefaultListModel());
        K15.setModel(new DefaultListModel());
        K16.setModel(new DefaultListModel());
        K17.setModel(new DefaultListModel());
        J7.setModel(new DefaultListModel());
        J8.setModel(new DefaultListModel());
        J9.setModel(new DefaultListModel());
        J10.setModel(new DefaultListModel());
        J11.setModel(new DefaultListModel());
        J12.setModel(new DefaultListModel());
        J13.setModel(new DefaultListModel());
        J14.setModel(new DefaultListModel());
        J15.setModel(new DefaultListModel());
        J16.setModel(new DefaultListModel());
        J17.setModel(new DefaultListModel());
        
        ScheduleField = new TreeMap<>();
	for(int day=SENIN; day<=JUMAT; day++) {
	    ScheduleField.put(day, new TreeMap<>());
	}
        ScheduleField.get(SENIN).put(7, Sn7);
        ScheduleField.get(SENIN).put(8, Sn8);
        ScheduleField.get(SENIN).put(9, Sn9);
        ScheduleField.get(SENIN).put(10, Sn10);
        ScheduleField.get(SENIN).put(11, Sn11);
        ScheduleField.get(SENIN).put(12, Sn12);
        ScheduleField.get(SENIN).put(13, Sn13);
        ScheduleField.get(SENIN).put(14, Sn14);
        ScheduleField.get(SENIN).put(15, Sn15);
        ScheduleField.get(SENIN).put(16, Sn16);
        ScheduleField.get(SENIN).put(17, Sn17);
        ScheduleField.get(SELASA).put(7, Sl7);
        ScheduleField.get(SELASA).put(8, Sl8);
        ScheduleField.get(SELASA).put(9, Sl9);
        ScheduleField.get(SELASA).put(10, Sl10);
        ScheduleField.get(SELASA).put(11, Sl11);
        ScheduleField.get(SELASA).put(12, Sl12);
        ScheduleField.get(SELASA).put(13, Sl13);
        ScheduleField.get(SELASA).put(14, Sl14);
        ScheduleField.get(SELASA).put(15, Sl15);
        ScheduleField.get(SELASA).put(16, Sl16);
        ScheduleField.get(SELASA).put(17, Sl17);
        ScheduleField.get(RABU).put(7, R7);
        ScheduleField.get(RABU).put(8, R8);
        ScheduleField.get(RABU).put(9, R9);
        ScheduleField.get(RABU).put(10, R10);
        ScheduleField.get(RABU).put(11, R11);
        ScheduleField.get(RABU).put(12, R12);
        ScheduleField.get(RABU).put(13, R13);
        ScheduleField.get(RABU).put(14, R13);
        ScheduleField.get(RABU).put(15, R15);
        ScheduleField.get(RABU).put(16, R16);
        ScheduleField.get(RABU).put(17, R17);
        ScheduleField.get(KAMIS).put(7, K7);
        ScheduleField.get(KAMIS).put(8, K8);
        ScheduleField.get(KAMIS).put(9, K9);
        ScheduleField.get(KAMIS).put(10, K10);
        ScheduleField.get(KAMIS).put(11, K11);
        ScheduleField.get(KAMIS).put(12, K12);
        ScheduleField.get(KAMIS).put(13, K13);
        ScheduleField.get(KAMIS).put(14, K14);
        ScheduleField.get(KAMIS).put(15, K15);
        ScheduleField.get(KAMIS).put(16, K16);
        ScheduleField.get(KAMIS).put(17, K17);
        ScheduleField.get(JUMAT).put(7, J7);
        ScheduleField.get(JUMAT).put(8, J8);
        ScheduleField.get(JUMAT).put(9, J9);
        ScheduleField.get(JUMAT).put(10, J10);
        ScheduleField.get(JUMAT).put(11, J11);
        ScheduleField.get(JUMAT).put(12, J12);
        ScheduleField.get(JUMAT).put(13, J13);
        ScheduleField.get(JUMAT).put(14, J14);
        ScheduleField.get(JUMAT).put(15, J15);
        ScheduleField.get(JUMAT).put(16, J16);
        ScheduleField.get(JUMAT).put(17, J17);
        
        //jadwal = s;
    }
    
    /** Input schedule ke TextField */
    void Insert(ScheduleState s)
    {
        String className, classRoom;
        int day, time;
        DefaultListModel textBox;
        for (int i=1;i<=s.getCourseMade();i++)
        {
            Courses actualCourse = s.getCourse(i);
            className = actualCourse.getActualCourseClass().name;
            classRoom = actualCourse.name;
            day = actualCourse.getActualCourseDay();
            time = actualCourse.getActualCourseTime();
            for (int j=0; j<actualCourse.duration; j++)
            {
                textBox = (DefaultListModel) ScheduleField.get(day).get(time+j).getModel();
                textBox.addElement(className + " - " + classRoom);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        conflictCount = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Sn7 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        Sl7 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        R7 = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        K7 = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        J7 = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        Sn8 = new javax.swing.JList<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        Sl8 = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        R8 = new javax.swing.JList<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        K8 = new javax.swing.JList<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        J8 = new javax.swing.JList<>();
        jScrollPane13 = new javax.swing.JScrollPane();
        Sn9 = new javax.swing.JList<>();
        jScrollPane14 = new javax.swing.JScrollPane();
        Sl9 = new javax.swing.JList<>();
        jScrollPane15 = new javax.swing.JScrollPane();
        R9 = new javax.swing.JList<>();
        jScrollPane16 = new javax.swing.JScrollPane();
        K9 = new javax.swing.JList<>();
        jScrollPane17 = new javax.swing.JScrollPane();
        J9 = new javax.swing.JList<>();
        jScrollPane18 = new javax.swing.JScrollPane();
        Sn10 = new javax.swing.JList<>();
        jScrollPane19 = new javax.swing.JScrollPane();
        Sl10 = new javax.swing.JList<>();
        jScrollPane20 = new javax.swing.JScrollPane();
        R10 = new javax.swing.JList<>();
        jScrollPane21 = new javax.swing.JScrollPane();
        K10 = new javax.swing.JList<>();
        jScrollPane22 = new javax.swing.JScrollPane();
        J10 = new javax.swing.JList<>();
        jScrollPane23 = new javax.swing.JScrollPane();
        Sn11 = new javax.swing.JList<>();
        jScrollPane24 = new javax.swing.JScrollPane();
        Sl11 = new javax.swing.JList<>();
        jScrollPane25 = new javax.swing.JScrollPane();
        R11 = new javax.swing.JList<>();
        jScrollPane26 = new javax.swing.JScrollPane();
        K11 = new javax.swing.JList<>();
        jScrollPane27 = new javax.swing.JScrollPane();
        J11 = new javax.swing.JList<>();
        jScrollPane28 = new javax.swing.JScrollPane();
        Sn12 = new javax.swing.JList<>();
        jScrollPane29 = new javax.swing.JScrollPane();
        Sl12 = new javax.swing.JList<>();
        jScrollPane30 = new javax.swing.JScrollPane();
        R12 = new javax.swing.JList<>();
        jScrollPane31 = new javax.swing.JScrollPane();
        K12 = new javax.swing.JList<>();
        jScrollPane32 = new javax.swing.JScrollPane();
        J12 = new javax.swing.JList<>();
        jScrollPane33 = new javax.swing.JScrollPane();
        Sn13 = new javax.swing.JList<>();
        jScrollPane34 = new javax.swing.JScrollPane();
        Sl13 = new javax.swing.JList<>();
        jScrollPane35 = new javax.swing.JScrollPane();
        R13 = new javax.swing.JList<>();
        jScrollPane36 = new javax.swing.JScrollPane();
        K13 = new javax.swing.JList<>();
        jScrollPane37 = new javax.swing.JScrollPane();
        J13 = new javax.swing.JList<>();
        jScrollPane38 = new javax.swing.JScrollPane();
        Sn14 = new javax.swing.JList<>();
        jScrollPane39 = new javax.swing.JScrollPane();
        Sl14 = new javax.swing.JList<>();
        jScrollPane40 = new javax.swing.JScrollPane();
        R14 = new javax.swing.JList<>();
        jScrollPane41 = new javax.swing.JScrollPane();
        K14 = new javax.swing.JList<>();
        jScrollPane42 = new javax.swing.JScrollPane();
        J14 = new javax.swing.JList<>();
        jScrollPane43 = new javax.swing.JScrollPane();
        Sn15 = new javax.swing.JList<>();
        jScrollPane44 = new javax.swing.JScrollPane();
        Sl15 = new javax.swing.JList<>();
        jScrollPane45 = new javax.swing.JScrollPane();
        R15 = new javax.swing.JList<>();
        jScrollPane46 = new javax.swing.JScrollPane();
        K15 = new javax.swing.JList<>();
        jScrollPane47 = new javax.swing.JScrollPane();
        J15 = new javax.swing.JList<>();
        jScrollPane48 = new javax.swing.JScrollPane();
        Sn16 = new javax.swing.JList<>();
        jScrollPane49 = new javax.swing.JScrollPane();
        Sl16 = new javax.swing.JList<>();
        jScrollPane50 = new javax.swing.JScrollPane();
        R16 = new javax.swing.JList<>();
        jScrollPane51 = new javax.swing.JScrollPane();
        K16 = new javax.swing.JList<>();
        jScrollPane52 = new javax.swing.JScrollPane();
        J16 = new javax.swing.JList<>();
        jScrollPane53 = new javax.swing.JScrollPane();
        Sn17 = new javax.swing.JList<>();
        jScrollPane54 = new javax.swing.JScrollPane();
        Sl17 = new javax.swing.JList<>();
        jScrollPane55 = new javax.swing.JScrollPane();
        R17 = new javax.swing.JList<>();
        jScrollPane56 = new javax.swing.JScrollPane();
        K17 = new javax.swing.JList<>();
        jScrollPane57 = new javax.swing.JScrollPane();
        J17 = new javax.swing.JList<>();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Senin");

        jLabel2.setText("Selasa");

        jLabel3.setText("Rabu");

        jLabel4.setText("Kamis");

        jLabel5.setText("Jumat");

        jLabel6.setText("07.00");

        jLabel7.setText("08.00");

        jLabel8.setText("09.00");

        jLabel9.setText("10.00");

        jLabel10.setText("11.00");

        jLabel11.setText("12.00");

        jLabel12.setText("13.00");

        jLabel13.setText("14.00");

        jLabel14.setText("15.00");

        jLabel15.setText("16.00");

        jLabel16.setText("17.00");

        jLabel17.setText("Total Conflict:");

        conflictCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conflictCountActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(Sn7);

        jScrollPane4.setViewportView(Sl7);

        jScrollPane5.setViewportView(R7);

        jScrollPane6.setViewportView(K7);

        jScrollPane7.setViewportView(J7);

        jScrollPane8.setViewportView(Sn8);

        jScrollPane9.setViewportView(Sl8);

        jScrollPane10.setViewportView(R8);

        jScrollPane11.setViewportView(K8);

        jScrollPane12.setViewportView(J8);

        jScrollPane13.setViewportView(Sn9);

        jScrollPane14.setViewportView(Sl9);

        jScrollPane15.setViewportView(R9);

        jScrollPane16.setViewportView(K9);

        jScrollPane17.setViewportView(J9);

        jScrollPane18.setViewportView(Sn10);

        jScrollPane19.setViewportView(Sl10);

        jScrollPane20.setViewportView(R10);

        jScrollPane21.setViewportView(K10);

        jScrollPane22.setViewportView(J10);

        jScrollPane23.setViewportView(Sn11);

        jScrollPane24.setViewportView(Sl11);

        jScrollPane25.setViewportView(R11);

        jScrollPane26.setViewportView(K11);

        jScrollPane27.setViewportView(J11);

        jScrollPane28.setViewportView(Sn12);

        jScrollPane29.setViewportView(Sl12);

        jScrollPane30.setViewportView(R12);

        jScrollPane31.setViewportView(K12);

        jScrollPane32.setViewportView(J12);

        jScrollPane33.setViewportView(Sn13);

        jScrollPane34.setViewportView(Sl13);

        jScrollPane35.setViewportView(R13);

        jScrollPane36.setViewportView(K13);

        jScrollPane37.setViewportView(J13);

        jScrollPane38.setViewportView(Sn14);

        jScrollPane39.setViewportView(Sl14);

        jScrollPane40.setViewportView(R14);

        jScrollPane41.setViewportView(K14);

        jScrollPane42.setViewportView(J14);

        jScrollPane43.setViewportView(Sn15);

        jScrollPane44.setViewportView(Sl15);

        jScrollPane45.setViewportView(R15);

        jScrollPane46.setViewportView(K15);

        jScrollPane47.setViewportView(J15);

        jScrollPane48.setViewportView(Sn16);

        jScrollPane49.setViewportView(Sl16);

        jScrollPane50.setViewportView(R16);

        jScrollPane51.setViewportView(K16);

        jScrollPane52.setViewportView(J16);

        jScrollPane53.setViewportView(Sn17);

        jScrollPane54.setViewportView(Sl17);

        jScrollPane55.setViewportView(R17);

        jScrollPane56.setViewportView(K17);

        jScrollPane57.setViewportView(J17);

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(conflictCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane48, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(74, 74, 74)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane55, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane46, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane47, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane57, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)
                                .addGap(108, 108, 108)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(39, 39, 39)))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(conflictCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void conflictCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conflictCountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conflictCountActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_backActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScheduleView().setVisible(true);
            }
        });
        //Insert(jadwal);      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> J10;
    private javax.swing.JList<String> J11;
    private javax.swing.JList<String> J12;
    private javax.swing.JList<String> J13;
    private javax.swing.JList<String> J14;
    private javax.swing.JList<String> J15;
    private javax.swing.JList<String> J16;
    private javax.swing.JList<String> J17;
    private javax.swing.JList<String> J7;
    private javax.swing.JList<String> J8;
    private javax.swing.JList<String> J9;
    private javax.swing.JList<String> K10;
    private javax.swing.JList<String> K11;
    private javax.swing.JList<String> K12;
    private javax.swing.JList<String> K13;
    private javax.swing.JList<String> K14;
    private javax.swing.JList<String> K15;
    private javax.swing.JList<String> K16;
    private javax.swing.JList<String> K17;
    private javax.swing.JList<String> K7;
    private javax.swing.JList<String> K8;
    private javax.swing.JList<String> K9;
    private javax.swing.JList<String> R10;
    private javax.swing.JList<String> R11;
    private javax.swing.JList<String> R12;
    private javax.swing.JList<String> R13;
    private javax.swing.JList<String> R14;
    private javax.swing.JList<String> R15;
    private javax.swing.JList<String> R16;
    private javax.swing.JList<String> R17;
    private javax.swing.JList<String> R7;
    private javax.swing.JList<String> R8;
    private javax.swing.JList<String> R9;
    private javax.swing.JList<String> Sl10;
    private javax.swing.JList<String> Sl11;
    private javax.swing.JList<String> Sl12;
    private javax.swing.JList<String> Sl13;
    private javax.swing.JList<String> Sl14;
    private javax.swing.JList<String> Sl15;
    private javax.swing.JList<String> Sl16;
    private javax.swing.JList<String> Sl17;
    private javax.swing.JList<String> Sl7;
    private javax.swing.JList<String> Sl8;
    private javax.swing.JList<String> Sl9;
    private javax.swing.JList<String> Sn10;
    private javax.swing.JList<String> Sn11;
    private javax.swing.JList<String> Sn12;
    private javax.swing.JList<String> Sn13;
    private javax.swing.JList<String> Sn14;
    private javax.swing.JList<String> Sn15;
    private javax.swing.JList<String> Sn16;
    private javax.swing.JList<String> Sn17;
    private javax.swing.JList<String> Sn7;
    private javax.swing.JList<String> Sn8;
    private javax.swing.JList<String> Sn9;
    private javax.swing.JButton back;
    private javax.swing.JTextField conflictCount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JScrollPane jScrollPane47;
    private javax.swing.JScrollPane jScrollPane48;
    private javax.swing.JScrollPane jScrollPane49;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane50;
    private javax.swing.JScrollPane jScrollPane51;
    private javax.swing.JScrollPane jScrollPane52;
    private javax.swing.JScrollPane jScrollPane53;
    private javax.swing.JScrollPane jScrollPane54;
    private javax.swing.JScrollPane jScrollPane55;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane57;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
