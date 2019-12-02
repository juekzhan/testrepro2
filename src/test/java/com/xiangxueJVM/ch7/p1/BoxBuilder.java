package com.xiangxueJVM.ch7.p1;
@SuppressWarnings("unused")
public class BoxBuilder {
	 //required必须参数
   
	private  final String Name;//礼盒名称
    private  final int Price;//礼盒价格
    //optional可选参数
    private   int zz;//粽子
    private   int xyd;//咸鸭蛋
    private   int ldg;//绿豆糕
    private   int yb;//月饼
    private   int jg;//坚果
    public static class Builder{
        //required必须参数
        private  final String Name;//礼盒名称
        private  final int Price;//礼盒价格
        //optional可选参数
        private   int zz;//粽子
        private   int xyd;//咸鸭蛋
        private   int ldg;//绿豆糕
        private   int yb;//月饼
        private   int jg;//坚果
        //构造方法
        public Builder(String name, int price) {
            super();
            this.Name = name;
            this.Price = price;
        }
        //建造方法
        public BoxBuilder builder(){       //内部引用外部的构造方法
            return new BoxBuilder(this);
        }

        public Builder zz(int value){
            this.zz=value;
            return this;
        }
        public Builder xyd(int value){
            this.xyd=value;
            return this;
        }
        //......
    }
    
    private BoxBuilder (Builder builder){    //此构造方法必须为 封装的，只能内部构造调用
        Name = builder.Name;
        Price =builder.Price;
        zz =builder.zz;
        xyd =builder.xyd;
        ldg=builder.ldg;
        yb=builder.yb;
        jg=builder.jg;
    }
    @SuppressWarnings("unused")
	public static void main(String[] args) {
    	 BoxBuilder box1 = new Builder("中秋 节礼盒1",120)
                 .zz(8)
                 .xyd(4)
                 .builder();    // 为内部的构造方
	}
}
