package com.ssj.test.enumpac;

public class EnumTests {
	
	public static void main(String[] args) {
		
		Colors col1=Colors.RED;
		/*for(Colors col:Colors.values()){//遍历枚举
			System.out.println(col.name()+col.ordinal());
		}*/
		
		/*String red="RED";
		System.out.println(Colors.valueOf(red));//类型转换*/
		
		/*Colors1 col=Colors1.RED;
		System.out.println(col.getName());
		col.setName("red1");
		System.out.println(col.getName());*/
		
		Colors1 co2=Colors1.GREEN;
		System.out.println(co2.isBool());
		
	}
}

enum Colors{
	RED(),BLUE;
}

enum Colors1{
	RED(0,"red"),
	BLUE(1,"blue"),
	GREEN(2,"green",true);
	
	private int order;
	private String name;
	private boolean bool;
	
	private Colors1(int order,String name){//构造器只能是私有的，以保证其只在内部使用
		this.order=order;
		this.name=name;
	}
	
	private Colors1(int order,String name,boolean bool){
		this.order=order;
		this.name=name;
		this.bool=bool;
	}

	public int getOrder() {
		return order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
}

enum TrafficLamp{//带有抽象方法的枚举
	RED(30) {	public TrafficLamp nextLamp() {return GREEN;}//TrafficLamp的内部子类实现
	},
	YELLOW(5){	public TrafficLamp nextLamp() {return RED;}
	},
	GREEN(20){	public TrafficLamp nextLamp() {return YELLOW;}
	};
	public abstract TrafficLamp nextLamp();
	
	int duration;
	private TrafficLamp(){}
	private TrafficLamp(int duration){
		this.duration=duration;
	}
}




















