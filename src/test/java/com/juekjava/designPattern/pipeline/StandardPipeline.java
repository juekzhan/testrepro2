package com.juekjava.designPattern.pipeline;
//基础管道类
public class StandardPipeline implements Pipeline {
   //阀门（非基础，定义一个first）
	
	protected Valve first = null;
	//基础阀门
	
	protected Valve basic = null;
	
	@Override
	public Valve getFirst() {
		return first;
	}

	@Override
	public Valve getBasic() {
		return basic;
	}

	@Override
	public void setBasic(Valve valve) {
		 this.basic = valve;
	}

	@Override
	public void addValve(Valve valve) {
		if(first == null) {  //如果当前管道 中没有阀门 就设置 第一个阀门 为 当前 阀门
			first  = valve;
			valve.setNext(basic);// 不管怎么样 中间都会加入一个基础管道
		}else {   //如果当前管道中有阀门，就当前为第一个current，
			Valve current = first;
			while(current != null) {
				if(current.getNext() == basic) {
					current.setNext(valve);   //设置一个当前的一个管道
					valve.setNext(basic);
				}
				current = current.getNext(); 
			}
		}
		
	}

}
