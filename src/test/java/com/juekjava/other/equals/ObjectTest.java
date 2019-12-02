package com.juekjava.other.equals;



public class ObjectTest {
	private String id;

	private String name;
	
	public ObjectTest(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public boolean equals(Object obj) {
		ObjectTest oldObj = (ObjectTest) obj;
		String oldId = oldObj.getId(); 
		if(this.id.equals(oldId)) {
			return true;
		}
		return false;
	}
    
	@Override
	public String toString() {
		return "id"+ this.getId() + "  name" + this.getName();
	}

	public static void main(String[] args) {
		//List<String> aa = new ArrayList<>();
		
//		aa.add("111");
//		aa.add("111");
//		aa.add("111");
//        System.out.println(aa.stream());		
		
		//Map<Long, List<OrderReturnLineCreateInfoIn>> groupLists = orderReturnLineCreateInfoIns.stream().collect(Collectors.groupingBy( OrderReturnLineCreateInfoIn::getOrderProdureId ) );//CollectionsUtil.group(orderReturnLineCreateInfoIns, "orderProdureId");
	     //String aa = null;
          //System.out.println(aa.startsWith("en"));
		
	}
}
