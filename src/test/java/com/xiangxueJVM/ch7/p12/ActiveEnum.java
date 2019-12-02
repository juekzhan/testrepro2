package com.xiangxueJVM.ch7.p12;
/**
 * 枚举和行为绑定
 * 声明的一个枚举本质就是一个类，每个具体的枚举值就是这个枚举类的实例。
 * 1.使用常量容易在写代码时写错
 * 2.使用常量如果要使用描述时比较麻烦
 * 3.其他类使用常量时，类编译时会把常量值直接写到字节码中，如果常量值有变化，所有相关的类需要重新编译，否则会不可预料的错误
枚举高级
 */
public class ActiveEnum {
	//枚举的加、减、乘、除
	@SuppressWarnings("incomplete-switch")
		public enum NormalActive{
			PLUS,MINUS,MULTI,DIVIDS,DIFFER;
			//枚举中的方法
			double oper(double x,double y) {
				switch(this) {
				case PLUS:return x+y;
				case MINUS:return x-y;
				case MULTI:return x*y;
				case DIVIDS:return x/y;
				//case DIFFER: return (x+1)*y;
				}
				throw new UnsupportedOperationException();
			}
		}
	public static void main(String[] args) {
		System.out.println(NormalActive.PLUS.oper(0.1, 0.2));
		//NormalActive.DIFFER.oper(0.1, 0.2);
		//BetterActive.DIFFER.oper()
	}
	
	//更好的实现枚举的加、减、乘、除
		public enum BetterActive{
			PLUS{
				@Override
				double oper(double x, double y) {
					return x+y;
				}
				},
			MINUS{@Override
					double oper(double x, double y) {
				return x-y;
			}
			},
			MULTI{@Override
				double oper(double x, double y) {
				return x*y;
			}
			},
			DIVIDS{@Override
				double oper(double x, double y) {
				return x/y;
			}
			};

			abstract double oper(double x,double y) ;
		}
}
