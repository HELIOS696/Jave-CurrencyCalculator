/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class CurWindow extends JFrame{
    
    int win_hei = 500;
    int win_len = 200;
    
    private JLabel amerCurrency, bahCurrency;
    private JTextField amerField, bahField;
    
    private JButton convertBut, clearBut, addBut;
    
    public CurWindow()
    {
        this.setTitle("CurrencyConversion");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(win_hei, win_len);
        initWin();
        
        this.setVisible(true);
    }
    
    public void initWin()
    {
        int titleFontSize = 25;
        int labFontSize = 15;
        
        Font titleFont = new Font("Arial", Font.BOLD, titleFontSize);
        Font labFont   = new Font("Arial", Font.BOLD, labFontSize);
        
        //create a panel for it to be automatically centered
        JPanel northPan  = new JPanel();
        JLabel titleLab = new JLabel("Currency Conversion");
        
        //set the font
        titleLab.setFont(titleFont);
        titleLab.setForeground(Color.red);
        
        northPan.add(titleLab);
        this.add(northPan, BorderLayout.NORTH);
        
        JPanel centerPan = new JPanel(new GridLayout(2,2));
        amerCurrency = new JLabel("Enter US amount ($)  ");
        amerCurrency.setFont(labFont);
        amerCurrency.setHorizontalAlignment(JLabel.RIGHT);
        centerPan.add(amerCurrency);
        amerField = new JTextField();
        amerField.setFont(labFont);
        centerPan.add(amerField);
        
        bahCurrency = new JLabel("Bahrain amount (BHD)  ");
        bahCurrency.setFont(labFont);
        bahCurrency.setHorizontalAlignment(JLabel.RIGHT);
        centerPan.add(bahCurrency);
        bahField = new JTextField();
        bahField.setFont(labFont);
        centerPan.add(bahField);
        
        this.add(centerPan, BorderLayout.CENTER);
        
        JPanel southPan = new JPanel();
        convertBut = new JButton("Convert");
        convertBut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                boolean check = checkValid();
                if(check == false)
                {
                    clear();
                }
                convertCur();
            }
        });
        
        convertBut.setFont(labFont);

        clearBut = new JButton("Clear");
        clearBut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                clear();
            }
        });
        clearBut.setFont(labFont);
        
        southPan.add(convertBut);
        southPan.add(clearBut);
        this.add(southPan, BorderLayout.SOUTH);
    }
    
    public boolean checkValid()
    {
        String userAnswerString = amerField.getText();
        double userAnswerDouble;
        
        try
        {
            userAnswerDouble = Double.parseDouble(userAnswerString);
            if(userAnswerDouble < 0)
            {
                String errorMessage = "You have entered an invalid value";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", 1, null);
                return false;
            }
        }
        catch(NumberFormatException nfe)
        {
            String errorMessage = "You have entered an invalid value";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", 1, null);
            return false;
        }
        return true;
    }
    
    public void convertCur()
    {
        String userAnswerString = amerField.getText();
        double userAnswerDouble = Double.parseDouble(userAnswerString);
        double convertedAnswerDouble;
        String convertedAnswerString;
        
        convertedAnswerDouble = (userAnswerDouble*0.378);
        DecimalFormat decFor  = new DecimalFormat("0.00");
        convertedAnswerString = decFor.format(convertedAnswerDouble);
        
        bahField.setText(convertedAnswerString);
    } 
    
    public void clear()
    {
        String clearAnswer = "";
        amerField.setText(clearAnswer);
        bahField.setText(clearAnswer);
    }
    
    public static void main(String [] args)
    {
        new CurWindow();
    }
}
