package com.xiangxueJVM.ch7.p9;
//可变参数要谨慎使用
public class VarArags {
	//累加方法：可变参数 0~很多
		static int sum(int... args) {
			int sum = 0;
			if(args.length==0){
				//异常处理
			}
			for (int arg : args)
				sum += arg;
			return sum;
		}
		
		//累加方法：可变参数 1~很多
		static int sum1(int first ,int... args) {
			int sum = 0;
			//if(first)
			if(args.length==0){
				//异常处理
			}
			for (int arg : args)
				sum += arg;
			return sum;
		}
    

		public static void main(String[] args) {
			System.out.println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
			sum();
		}
}
