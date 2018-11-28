package com.juekjava.threadConcurrency;

public class ServerImpl {
  ISequeeue iSequeeue ;
  
  
  
  
  public ISequeeue getiSequeeue() {
	return iSequeeue;
}




public void setiSequeeue(ISequeeue iSequeeue) {
	this.iSequeeue = iSequeeue;
}




public int diaoYong() {
	  return iSequeeue.getNext() ;
	  
  }

public int atomGetDiao() {
	return iSequeeue.getAtomicInt();
}
  
}
