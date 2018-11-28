package com.juekjava.designPattern.template;

import java.applet.Applet;
import java.awt.Graphics;

public class MyApplet extends Applet {
 String message;
 public void init() {
	 message = "Hello World ,I'm alive";
	 repaint();
 }
 
 public void start() {
	 message = "Now I'm starting up ....";
 }
 
 
 
 @Override
public void destroy() {
	// TODO Auto-generated method stub
	super.destroy();
}

public void paint(Graphics g) {
	 g.drawString(message, 5, 15);
 }
}
