package gui;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class screenMain extends JFrame implements ActionListener{

	//Creates instance of container
	Container screenContainer;
	//Creates instance of JButton
	JButton logOffBtn, messageBtn, friendBtn;
	JButton[] friends;
	//Integer to store number of friends
	int numOfFriends = 6;
	
	//Creates colour variable
	Color backgroundColour = Color.decode("#2B2D42"); 
	Color textColour = Color.decode("#FDFFFC");

	screenMain(){

		/*
		INITIALISES MAIN
		*/
		//Sets container to full width and height of the frame
		screenContainer = this.getContentPane();
		//Sets container background colour
		screenContainer.setBackground(backgroundColour);
		//Uses no layout manager
		screenContainer.setLayout(null);

		/*
		CREATES MAIN BUTTONS
		*/
		logOffBtn = makeButton(0,638,300,40,"Log Off");
		screenContainer.add(logOffBtn);
		messageBtn = makeButton(0,600,150,40,"Message");
		screenContainer.add(messageBtn);
		friendBtn = makeButton(150,600,150,40,"Friends");
		screenContainer.add(friendBtn);

		/*
		CREATES SCROLLABLE LIST
		*/

		/*
		INITIALISES FRIENDS LIST
		*/
		friends = new JButton[numOfFriends];
		int y = 100;
		for(int i = 0; i < numOfFriends; i++){
			friends[i] = makeButton(0,y,300,40,"SLAG");
			screenContainer.add(friends[i]);
			y = y + 40;
		}



		
	}

	public JButton makeButton(int x, int y, int width, int height, String text){

		//Creates instance of button
		JButton button = new JButton(text);
		//Sets x axis, y axis, width, and height of button
		button.setBounds(x,y,width,height);
		//Adds action listener to button
		button.addActionListener(this);
		//Sets button border
		button.setBorder(new LineBorder(Color.WHITE, 2));
		//Sets button text colour and font
		button.setForeground(textColour);
		button.setFont(new Font("Futura", Font.PLAIN, 20));
		//Passes created button back
		return button;
	}
	
  	public void actionPerformed(ActionEvent e) {
  		if(e.getSource() == logOffBtn){
  			screenContainer.setBackground(Color.BLUE);
  		}
  		else if(e.getSource() == messageBtn){
  			screenContainer.setBackground(Color.RED);
  		}
  		
  	}

  	public void messageList(){}
  	public void friendlist(){}
  	
    
}

