package ASimulatorSystem1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class Signup extends JFrame implements ActionListener {

    private static Signup instance; 

    private JLabel l1, l2;
    private JTextField t1, t2, t3, t4, t5, t6, t7;
    private JRadioButton r1, r2, r3, r4, r5;
    private JButton b;
    private JDateChooser dateChooser;

    private Signup() {
        initializeUI(); 
    }

    // Singleton getInstance method
    public static Signup getInstance() {
        if (instance == null) {
            instance = new Signup();
        }
        return instance;
    }

    private void initializeUI() {
        setTitle("NEW ACCOUNT APPLICATION FORM");

        // Create ImageIcon for logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        // Add logo to the label
        JLabel l11 = new JLabel(i3);
        l11.setBounds(20, 0, 100, 100);
        add(l11);

        // Initialize form number label
        l1 = new JLabel("APPLICATION FORM NO. " + generateFormNumber());
        l1.setFont(new Font("Raleway", Font.BOLD, 38));
        l1.setBounds(140, 20, 600, 40);
        add(l1);

        // Initialize other UI components
        l2 = new JLabel("Page 1: Personal Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(290, 80, 600, 30);
        add(l2);

        // Initialize text fields
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 14));
        t1.setBounds(300, 140, 400, 30);
        add(t1);

        // Initialize radio buttons
        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);
        r1.setBounds(300, 290, 60, 30);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);
        r2.setBounds(300, 290, 60, 30);
        add(r2);

        r3 = new JRadioButton("Married");
        r3.setFont(new Font("Raleway", Font.BOLD, 14));
        r3.setBackground(Color.WHITE);
        r3.setBounds(300, 290, 60, 30);
        add(r3);
        
        r4 = new JRadioButton("Unmarried");
        r4.setFont(new Font("Raleway", Font.BOLD, 14));
        r4.setBackground(Color.WHITE);
        r4.setBounds(300, 290, 60, 30);
        add(r4);
        
        r5 = new JRadioButton("Other");
        r5.setFont(new Font("Raleway", Font.BOLD, 14));
        r5.setBackground(Color.WHITE);
        r5.setBounds(300, 290, 60, 30);
        add(r5);

        // Initialize buttons
        b = new JButton("Next");
        b.setFont(new Font("Raleway", Font.BOLD, 14));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setBounds(620, 660, 80, 30);
        add(b);

        // Add action listeners
        b.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(500, 120);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b) {
            signUp();
        }
    }

    private void signUp() {
        String formno = generateFormNumber(); 
        // Get data from form fields
        String name = t1.getText();
        String fname = t2.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = getSelectedGender(); 
        String email = t3.getText();
        String marital = getSelectedMaritalStatus(); 
        String address = t4.getText();
        String city = t5.getText();
        String pincode = t6.getText();
        String state = t7.getText();

        if (validateFields()) { 
            try {
                String query = "INSERT INTO signup VALUES('" + formno + "','" + name + "','" + fname + "','" + dob
                        + "','" + gender + "','" + email + "','" + marital + "','" + address + "','" + city + "','"
                        + pincode + "','" + state + "')";
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fill all the required fields");
        }
    }

    private String generateFormNumber() {
        Random ran = new Random();
        long first4 = (ran.nextLong() % 9000L) + 1000L;
        return "" + Math.abs(first4);
    }

    private String getSelectedGender() {
        if (r1.isSelected()) {
            return "Male";
        } else if (r2.isSelected()) {
            return "Female";
        }
        return null;
    }

    private String getSelectedMaritalStatus() {
        if (r3.isSelected()) {
            return "Married";
        } else if (r4.isSelected()) {
            return "Unmarried";
        } else if (r5.isSelected()) {
            return "Other";
        }
        return null;
    }

    private boolean validateFields() {
        return !t1.getText().isEmpty() && !t2.getText().isEmpty() && !t3.getText().isEmpty()
                && !((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().isEmpty()
                && getSelectedGender() != null && getSelectedMaritalStatus() != null
                && !t4.getText().isEmpty() && !t5.getText().isEmpty() && !t6.getText().isEmpty()
                && !t7.getText().isEmpty();
    }

    public static void main(String[] args) {
        Signup.getInstance().setVisible(true);
    }
}