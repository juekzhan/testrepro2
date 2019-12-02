package com.xiangxueJVM.ch5.memleak;

import java.util.HashSet;

// Hash值改变导致的内存泄漏
public class Node {
	 private int x;
	 private int y;
	 public Node(int x, int y) {
	        super();
	        this.x = x;
	        this.y = y;
	    }
	    //重写HashCode的方法
	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + x;
	        result = prime * result + y;
	        return result;
	    }
	    //改变y的值：同时改变hashcode
	    public void setY(int y) {
	        this.y = y;
	    }

	    public static void main(String[] args) {
	        HashSet<Node> hashSet = new HashSet<Node>();
	        Node nod1 = new Node(1, 3);
	        Node nod2 = new Node(3, 5);
	        hashSet.add(nod1);
	        hashSet.add(nod2);
	        nod2.setY(7);//nod2的Hash值改变
	        hashSet.remove(nod2);//删掉nod2节点
	        System.out.println(hashSet.size());
	    }

}
