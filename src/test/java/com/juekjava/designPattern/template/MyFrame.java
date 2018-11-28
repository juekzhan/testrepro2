package com.juekjava.designPattern.template;

import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MyFrame();
	}

	public MyFrame() throws HeadlessException {
		this.setTitle("模板模式");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		String msg = "I rule";
		g.drawString(msg, 100, 100);
	}
}
