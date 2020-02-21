package com.xiangxuenet.nettyhttp.server;

import java.util.Random;

public class RespConstant {
 private static final String[] NEWS = {"智能之士,不学不成,不问不知",
		 "不学自知，不问自晓，古今行事，未之有也",
		 "夫可知之事，惟精思之， 虽大无难； 不可知之事， 厉心学问， 虽小无易",
		 "一日不读书，胸臆无佳想。一月不读书，耳目失精爽",
		 "吾日三省吾身：为人谋而不忠乎",
		 "学而不思则罔，思而不学则殆",
		 "朝闻道，夕死可矣",
		 "为学之道，必本于思。思则得之，不思则不得也",
		 "致知之途有二：曰学，曰思。学非有碍于思，而学愈博则思愈远；思正有功于学，而思之困则学必勤",
		 "学如不及，犹恐失之"};
 private static final Random R = new Random();
 
 public static String getNews() {
	 return NEWS[R.nextInt(NEWS.length)];
 }
}
