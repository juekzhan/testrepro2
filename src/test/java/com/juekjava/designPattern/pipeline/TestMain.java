package com.juekjava.designPattern.pipeline;

public class TestMain {

	public static void main(String[] args) {
        String request = "这个是 一个Servlet请求";
        //new 出一个管道
        StandardPipeline pipeline = new StandardPipeline();
        //三个阀门一个基础，2个定制
        StandarValve standarValve = new StandarValve();
        FirstValve firstValve  = new FirstValve();
        SecondValve secondValve = new SecondValve();
        
        pipeline.setBasic(standarValve);
        
        pipeline.addValve(firstValve);
        pipeline.addValve(secondValve);
        
        //调用对象管道中的第一个阀门
        pipeline.getFirst().invoke(request);
	}

}
