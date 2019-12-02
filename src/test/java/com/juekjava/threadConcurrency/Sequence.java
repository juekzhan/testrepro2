package com.juekjava.threadConcurrency;

import java.util.concurrent.atomic.AtomicInteger;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Sequence  implements ISequeeue{
  @GuardedBy("this")
  private  int value = 10;
  
  private AtomicInteger integer = new AtomicInteger(0);
  
  public  int getNext() {
	  return value++;
  }
  
  public int getAtomicInt() {
	  return integer.getAndIncrement();
  }
}
