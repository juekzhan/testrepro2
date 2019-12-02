package com.xiangxueJVM.ch7.p1;

@SuppressWarnings("unused")
public class Box {
	  //required必须参数
    private  final String Name;//礼盒名称
    private  final int Price;//礼盒价格
    //optional可选参数

	private  final int zz;//粽子
    private  final int xyd;//咸鸭蛋
    private  final int ldg;//绿豆糕
    private  final int yb;//月饼
    private  final int jg;//坚果
    //这个参数拓展

    //全参数构造方法
    public Box(String name, int price, int zz, int xyd, int ldg, int yb, int jg) {
        this.Name = name;
        this.Price = price;
        this.zz = zz;
        this.xyd = xyd;
        this.ldg = ldg;
        this.yb = yb;
        this.jg = jg;
    }
    //3个参数构造方法
    public Box(String name, int price, int zz) {
        this(name,price,price,0,0,0,0);
    }
    //4个参数构造方法
    public Box(String name, int price, int zz,int xyd) {
        this(name,price,price,xyd,0,0,0);
    }
    //5个或更多
    @SuppressWarnings("unused")
	public static void main(String[] args) {
       Box box1 = new Box("端午节礼盒1",100,8);

       Box box2 = new Box("端午节礼盒2",150,8,4);
        //中秋礼盒、过年礼盒......
    }
}
