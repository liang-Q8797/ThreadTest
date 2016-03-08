package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test implements Runnable{
	static String str = "R+X+Y+Z";//一定是static类型的，为了资源共享（在累初始化的时候，加载该资源，有JVM Java虚拟机来管理）
	static String strr ="";
	public static void main(String[] args) throws Exception {
		Test test1= new Test();//只是实例
		Test test2 = new Test();
		Test test3 = new Test();
		Test test4 = new Test();
		new Thread(test1).start();
		new Thread(test2).start();
		new Thread(test3).start();
		new Thread(test4).start();
//		ExecutorService pool = Executors.newFixedThreadPool(2); 
//		Thread t1 = new Thread(test1);
//		Thread t2 = new Thread(test2);
//		pool.execute(t1);
//		pool.execute(t2);
//		Thread.sleep(2000);
//		System.out.println(strr.substring(1, strr.length()));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (str) { //对资源加锁
			String str1=null; 
			if(str.contains("+")){
				 str1 = str.split("\\+",2)[0];//将字符串按+号分割，分成两组 ，数组中第一个元素输出
				 str = str.split("\\+",2)[1]; //修改共享资源。
				 //System.out.println(str);
			}else{
				str1 = str; 
			}
			strr=strr+"+"+str1;
			System.out.println(str1);
		}
		
	}
}
