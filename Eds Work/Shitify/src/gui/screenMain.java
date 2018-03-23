package gui;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class screenMain extends JFrame implements ActionListener{

	//Creates instance of container
	Container screenContainer;

	//Creates instance of JPanel
	JPanel menu;

	//Creates instance of JButton
	JButton logOffBtn, messageBtn, friendBtn;
	JButton btn1, btn2, btn3;
	JButton[] friends;

	//Creates instance of JScrollFrame
	JScrollPane menuScrollPane;

	//Integer to store number of friends
	int numOfFriends = 17;
	
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
		INITIALISES MENU PANEL
		*/
		menu = new JPanel();
  		menu.setBounds(0,100,300,500);
  		menu.add(makeScrollFrame());
  		screenContainer.add(menu); 
		
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

	
	public JScrollPane makeScrollFrame(){


		JPanel test = new JPanel();

		test.setLayout(new BoxLayout(test, BoxLayout.Y_AXIS));
		//test.setPreferredSize(new Dimension(300, 500));

		int listYStart = 100;
		numOfFriends = 17;
		friends = new JButton[numOfFriends];
		for(int i = 0; i < numOfFriends; i++){
			friends[i] = makeButton(0,listYStart,300,40,"TEST TEST TEST");
			screenContainer.add(friends[i]);
			listYStart = listYStart + 40;
			test.add(friends[i]);
		}


		JScrollPane scrollPane = new JScrollPane(test);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		return scrollPane;

	}
	
	
  	public void actionPerformed(ActionEvent e) {
  		if(e.getSource() == logOffBtn){
  			screenContainer.setBackground(Color.BLUE);
  		}
  		else if(e.getSource() == messageBtn){
  			//scrollPane = messageList();
  		}
  		else if(e.getSource() == friendBtn){
  			//scrollPane = friendList();
  		}
  		
  	}

  	/*
  	public void messageList(){

  		int numOfFriends = 4;

  		friends = new JButton[numOfFriends];

  		panel.setLayout(new GridLayout(x, y));

		int listYStart = 100;
		for(int i = 0; i < numOfFriends; i++){
			friends[i] = makeButton(0,listYStart,300,40,"SLAG");
			screenContainer.add(friends[i]);
			listYStart = listYStart + 40;
			panel.add(friends[i]);
		}

		scrollPane = new JScrollPane(panel);

		int numOfFriends = 8;

  	}

  	public void friendlist(){}
  	*/
    
}

