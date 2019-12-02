package com.xiangxueJVM.ch7.p1.bulidpattern;

public class Box {
	  //required必须参数
    protected   String Name;//礼盒名称
    protected   int Price;//礼盒价格
    //optional可选参数
    protected   int zz;//粽子
    protected   int xyd;//咸鸭蛋

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
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
}
