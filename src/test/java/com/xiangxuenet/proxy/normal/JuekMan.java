package com.xiangxuenet.proxy.normal;

import com.xiangxuenet.proxy.IGetServer;
import com.xiangxuenet.proxy.Receptionist;
//实现了接口服务类 ，代理别人。代理对象 ，就是代理前台小姐姐
public class JuekMan implements IGetServer{
    
	private IGetServer receptionist = new Receptionist();
	@Override
	public void choice(String desc) {
		System.out.println("juekMan找到了前台小姐姐......");
		receptionist.choice(desc);
		System.out.println("juek:能不能第二个半价？我喜欢小家碧玉，初恋的感觉");
	}

}
