package com.xiangxueJVM.ch7.p1;

public class BoxJB {
	//required必须参数
    private  final String Name;//礼盒名称
    private  final int Price;//礼盒价格
    //optional可选参数
    private   int zz;//粽子
    private   int xyd;//咸鸭蛋
    private   int ldg;//绿豆糕
    private   int yb;//月饼
    private   int jg;//坚果
    
    //必须的构造方法
    public BoxJB(String name, int price) {
        super();
        this.Name = name;
        this.Price = price;
    }

	public int getZz() {
		return zz;
	}

	public void setZz(int zz) {
		this.zz = zz;
	}

	public int getXyd() {
		return xyd;
	}

	public void setXyd(int xyd) {
		this.xyd = xyd;
	}

	public int getLdg() {
		return ldg;
	}

	public void setLdg(int ldg) {
		this.ldg = ldg;
	}

	public int getYb() {
		return yb;
	}

	public void setYb(int yb) {
		this.yb = yb;
	}

	public int getJg() {
		return jg;
	}

	public void setJg(int jg) {
		this.jg = jg;
	}

	public String getName() {
		return Name;
	}

	public int getPrice() {
		return Price;
	}
   
    public static void main(String[] args) {
    	 BoxJB box1 = new BoxJB("端午节礼盒1",100);
         box1.setZz(8);//8个粽子


         BoxJB box2 = new BoxJB("端午节礼盒2",150);
         box2.setZz(8);//8个粽子
         box2.setXyd(4);//4个咸鸭蛋

          //端午节礼盒、中秋礼盒、过年礼盒......
	}
}
