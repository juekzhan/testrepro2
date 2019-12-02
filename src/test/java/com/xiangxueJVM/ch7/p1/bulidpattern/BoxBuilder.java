package com.xiangxueJVM.ch7.p1.bulidpattern;

public interface BoxBuilder {
	  void buildName(String Name);
      void buildPrice(int Price);
      void buildzz(int zz);
      void buildxyd(int xyd);
      Box createBox();
}
