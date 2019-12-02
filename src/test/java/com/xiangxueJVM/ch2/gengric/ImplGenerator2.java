package com.xiangxueJVM.ch2.gengric;

public class ImplGenerator2 implements Generator<String> {
     
	@Override
	public String next() {
		return "juek";
	}
    
	public static void main(String[] args) {
		ImplGenerator2 generator2 = new ImplGenerator2();
		System.out.println(generator2.next());
	}
}
