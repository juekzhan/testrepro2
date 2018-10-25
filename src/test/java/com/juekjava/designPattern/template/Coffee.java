package com.juekjava.designPattern.template;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * 咖啡类
 * 
 * @author zsh12489
 *
 */
public class Coffee extends CaffeeineBeverage {

	@Override
	void brew() {
		System.out.println("Dripping Coffee through filter");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding Sugar anf Milk");
	}

	@Override
	boolean customerWantsCondoments() {
		String answer = getUserInput();
		if (answer.toLowerCase().startsWith("y")) {
			return true;
		}
		return false;
	}

	private String getUserInput() {
		String answer = null;

		System.out.println(" Would you like milk and sugar with your coffee (y/n)?");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			answer = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (answer == null) {
			return "no";
		}
		return answer;
	}

}
