package com.xiangxueJVM.ch7.p1.bulidpattern;
//*类说明：导演者
public class DyBox {
	 public Box buildDWBox(BoxBuilder bb){
	        bb.buildName("端午节礼盒1");
	        bb.buildPrice(150);
	        bb.buildzz(8);
	        bb.buildxyd(4);
	        return bb.createBox();
	    }
	 
	 public static void main(String[] args) {
		   DyBox dwBox = new DyBox();
	        System.out.println("我需要一个端午礼盒");
	        dwBox.buildDWBox(new DwBuilder());
	}
}
