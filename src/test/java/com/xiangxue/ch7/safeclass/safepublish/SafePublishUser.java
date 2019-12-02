package com.xiangxue.ch7.safeclass.safepublish;

import com.xiangxue.ch7.safeclass.UserVo;

/**
 * 
 * @ClassName: SafePublishUser
 * @Package :com.xiangxue.ch7.safeclass.safepublish
 * @Description: 对对象进行包装安全发布  继承
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午2:56:14
 */
public class SafePublishUser {
	private final UserVo user;

	public UserVo getUser() {
		return user;
	}
   
	
	public SafePublishUser(UserVo user) {
		this.user = new SynUser(user);
	}
	

    /**
     * 
     * @ClassName: SynUser  
     * @Package :com.xiangxue.ch7.safeclass.safepublish
     * @Description: 装饰模式 
     * @Author: shuling.zhan
     * @Email: shuling.zhan@baozun.com
     * @Date: 2019年7月19日 下午3:12:15
     */
	private static class SynUser extends UserVo{
		private final UserVo userVo;
		private Object lock = new Object();
		
		public SynUser(UserVo userVo) {
			this.userVo = userVo;
		}

		@Override
		public int getAge() {
			synchronized (lock) {
				return userVo.getAge();
			}
		}

		@Override
		public void setAge(int age) {
			synchronized (lock) {
				userVo.setAge(age);
			}
		}
	}
	
}
