package com.ssj.test.designMode;

public class Singleton {
		
		/*private volatile static Singleton singleton;
		private Singleton(){};
		
		public static Singleton getInstance(){
				if(singleton==null){
						synchronized (Singleton.class) {
								if(singleton==null){
										singleton=new Singleton();
								}
						}
					
				}
				return singleton;
		}*/
	
		private static class SingletonHolder{
				private static final Singleton Instance=new Singleton();
		}
		
		public static Singleton getInstance(){
				return SingletonHolder.Instance;
		}
	
	
	
	
	
	
	
	
	
	
}
