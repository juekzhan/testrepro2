package com.xiangxueJVM.ch7.p1.bulidpattern;
//*类说明：具体建造者,端午节礼盒的建造者
public class DwBuilder implements BoxBuilder {

	private Box box;

	public DwBuilder() {
		this.box = new Box();
	}

	@Override
	public void buildName(String Name) {
		box.setName(Name);
	}

	@Override
	public void buildPrice(int Price) {
		box.setPrice(Price);
	}

	@Override
	public void buildzz(int zz) {
		box.setZz(zz);

	}

	@Override
	public void buildxyd(int xyd) {
		box.setXyd(xyd);

	}

	@Override
	public Box createBox() {
		return box;
	}

}
