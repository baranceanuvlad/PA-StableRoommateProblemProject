package UI;

import Threads.ClientThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private String message=null;
    public MainFrame(){}
    public void setMessage(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
    public void draw(){
        JFrame f=new JFrame();
        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        JButton solveBtn=new JButton("Generate Solution");
        solveBtn.setBounds(100,50,200,40);
        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("solve");
            }
        });

        JButton addBtn=new JButton("Add Student");
        addBtn.setBounds(100,100,200,40);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=JOptionPane.showInputDialog("Insert the student's first and last name: ");
                String id1=JOptionPane.showInputDialog("Insert the student's id: ");
                String preferences=JOptionPane.showInputDialog("Insert the student's preferences: ");
                setMessage("add "+name+" "+id1+" "+preferences);
            }
        });

        JButton modBtn=new JButton("Modify Student");
        modBtn.setBounds(100,150,200,40);
        modBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1=JOptionPane.showInputDialog("Insert the student's id: ");
                String preferences=JOptionPane.showInputDialog("Insert the student's new preferences: ");
                setMessage("modify "+id1+" "+preferences);
            }
        });

        JButton delBtn=new JButton("Delete Student");
        delBtn.setBounds(100,200,200,40);
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1=JOptionPane.showInputDialog("Insert the student's id: ");
                setMessage("delete "+id1);
            }
        });

        JButton addPrefBtn=new JButton("Add Preference");
        addPrefBtn.setBounds(100,250,200,40);
        addPrefBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1=JOptionPane.showInputDialog("Insert the first student's id: ");
                String id2=JOptionPane.showInputDialog("Insert the second student's id: ");
                setMessage("addp "+id1+" "+id2);
            }
        });

        JButton exitBtn=new JButton("exit");
        exitBtn.setBounds(150,300,100, 40);//x axis, y axis, width, height
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("exit");
            }
        });

        JButton exitServerBtn=new JButton("exit server");
        exitServerBtn.setBounds(150,350,100, 40);//x axis, y axis, width, height
        exitServerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("exitServer");
            }
        });
        f.add(addBtn);
        f.add(modBtn);
        f.add(delBtn);
        f.add(solveBtn);
        f.add(addPrefBtn);
        f.add(exitBtn);//adding button in JFrame
        f.add(exitServerBtn);

    }
}
