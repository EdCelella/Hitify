package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class frame{

	public static void main(String[] args){

		//creating instance of JFrame 
		screenMain f = new screenMain();

		//Sets title 
		f.setTitle("SHITIFY");

		//Makes the frame visible
		f.setVisible(true);

		//Sets x axis, y axis, width, and height of the frame
		f.setBounds(100,50,1200,700);

		//Closes the frame when the exit button is pressed
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	} 
    
}
